import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalCommunesComponent } from './modal-communes/modal-communes.component';

@Component({
  selector: 'app-communes',
  templateUrl: './communes.component.html',
  styleUrls: ['./communes.component.scss']
})
export class CommunesComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  CommunesArrayColumn = [
    'num',
    'libelle',
    'villeId',
    'status',
    'isUsed',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    code: '',
    libelle: '',
  };
  listeCommunes: any[] = [];
  listeVille: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getCommunes();
    this.getVille();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getCommunes();
  }

  rechercher(){
    this.page.index = 0
    this.getCommunes(this.searchData);
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getVille(param?: any) {
    const methode = 'ville/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des villes`,
      data: {
      },
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeVille = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les params états');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getCommunes(param?: any) {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'commune/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des communes`,
      index: this.page.index,
      size: this.page.size,
      data: {
        libelle: param && param.libelle ? param.libelle : '',
        code: param && param.code ? param.code : ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeCommunes = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les communes');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalCommunesComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log('The dialog was closed', result);
      this.getCommunes();
      // this.animal = result;
    });
  }


  askBeforeDelete(item: any) {
    Swal.fire({
      title: '<div style="color:#fff">Attention!</div>',
      html: '<div style="color:#fff">Vous êtes sur le point de supprimer cet element. Continuer?</div>',
      showCancelButton: true,
      confirmButtonColor: '#dc3545',
      confirmButtonText: `Supprimer`,
      cancelButtonText: `Annuler`,
      background: '#000',
      
    }).then((result: any) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        // Swal.fire('Saved!', '', 'success')
        this.deleteAction(item);
      } else if (result.isDenied) {
        // Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

  deleteAction(param?: any) {
    const methode = 'commune/delete';
    const data = {
      
      datas: [{
        id: param && param.id ? param.id : '',
      }]
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getCommunes();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }



}
