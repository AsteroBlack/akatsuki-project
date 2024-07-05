import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalSeuilAlerteComponent } from './modal-seuil-alerte/modal-seuil-alerte.component';

@Component({
  selector: 'app-seuil-alerte',
  templateUrl: './seuil-alerte.component.html',
  styleUrls: ['./seuil-alerte.component.scss']
})
export class SeuilAlerteComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  SeuilArrayColumn = [
    'num',
    'seuil',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    seuilAlerte: '',
    libelle: '',
  };
  listeSeuilAlerte: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getSeuilAlerte();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getSeuilAlerte();
  }

  rechercher(){
    this.page.index = 0
    this.getSeuilAlerte();
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getSeuilAlerte() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'parametrage/getByCriteria';
    const data = {
      index: this.page.index,
      size: this.page.size,
      serviceLibelle: `Consultation de la liste des seuils d'alertes`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
        seuilAlerte: this.searchData.seuilAlerte ?? '',
        // code: this.searchData.code ?? ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeIpManager(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeSeuilAlerte = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les seuil d\'alertes');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalSeuilAlerteComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      this.getSeuilAlerte();
      // this.animal = result;
    });
  }

  askBeforeDelete(item: any) {
    Swal.fire({
      title: '<div style="color:#fff">Attention!</div>',
      html: `<div style="color:#fff">Vous êtes sur le point de supprimer cet element (${item.seuilAlerte}). Continuer?</div>`,
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
    const methode = 'parametrage/delete';
    const data = {

      datas: [{
        id: param && param.id ? param.id : '',
      }]
    };
    console.log('data', data);
    this.restApi.executeIpManager(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getSeuilAlerte();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testMenu(action:string){
    return this.userService.checkMenuExiste('GESTION_DES_IP','SEUIL_ALERTE',action)
  }
}
