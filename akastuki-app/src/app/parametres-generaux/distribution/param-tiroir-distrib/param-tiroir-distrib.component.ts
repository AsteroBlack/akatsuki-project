import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalParamTiroirDistribComponent } from './modal-param-tiroir-distrib/modal-param-tiroir-distrib.component';
import { Router } from '@angular/router';
import { CommunicationService } from 'src/app/shared/services/communication.service';

@Component({
  selector: 'app-param-tiroir-distrib',
  templateUrl: './param-tiroir-distrib.component.html',
  styleUrls: ['./param-tiroir-distrib.component.scss']
})
export class ParamTiroirDistribComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  ParamsTiroirDistribArrayColumn = [
    'num',
    'libelle',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    code: '',
    libelle: '',
  };
  listeParamsTiroirDistrib: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
    private router:Router,
    private communicationService:CommunicationService
  ) {
    let mainMenu =  this.router.url.split('/')[this.router.url.split('/').length-2]
    this.communicationService.sendData(mainMenu)
   }

  ngOnInit(): void {
    this.getParamsTiroirDistrib();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getParamsTiroirDistrib();
  }

  rechercher(){
    this.getParamsTiroirDistrib();
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getParamsTiroirDistrib() {
    // const methode = 'costomer-back-end-api-1.0/paramTiroirDistrib/getByCriteria';
    // const methode = 'api-customer-care-fixe/getTiroirDistribCriteria';
    const methode = 'paramTiroirDistrib/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des params tiroir distrib`,
      index: this.page.index,
      size: this.page.size,
      data: {
        libelle: this.searchData.libelle ?? '',
        code: this.searchData.code ?? ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeParamsTiroirDistrib = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les params tiroir distrib');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalParamTiroirDistribComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log('The dialog was closed', result);
      this.getParamsTiroirDistrib();
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
    const methode = 'paramTiroirDistrib/delete';
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
        this.getParamsTiroirDistrib();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testMenu(action:any){
    return this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX',action)
  }


}
