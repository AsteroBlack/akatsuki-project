import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalParamOffreComponent } from './modal-param-offre/modal-param-offre.component';
import { Router } from '@angular/router';
import { CommunicationService } from 'src/app/shared/services/communication.service';

@Component({
  selector: 'app-param-offre',
  templateUrl: './param-offre.component.html',
  styleUrls: ['./param-offre.component.scss']
})
export class ParamOffreComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  ParamsOffreArrayColumn = [
    'num',
    // 'code',
    'libelle',
    'brandIndex',
    'serviceClass',
    'debit',
    'typeFormule',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    code: '',
    libelle: '',
    typeFormuleId: '',
    serviceClass: ''
  };
  listeParamsOffre: any[] = [];
  listeFormules: any[] = [];
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
    this.getParamsOffre();
    this.getTypeFormule();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getParamsOffre();
  }

  rechercher(){
    this.page.index = 0
    this.getParamsOffre();
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getTypeFormule(param?: any) {
    const methode = 'paramTypeFormule/getByCriteria';
    const data = {
      data: {

      }
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeFormules = res.items ? res.items: [];

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getParamsOffre() {
    // const methode = 'costomer-back-end-api-1.0/paramOffre/getByCriteria';
    // const methode = 'api-customer-care-fixe/getOffreCriteria';
    const methode = 'paramOffre/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des params architecture`,
      index: this.page.index,
      size: this.page.size,
      data: {
        libelle: this.searchData.libelle ?? '',
        serviceClass: this.searchData.serviceClass ?? '',
        typeFormuleId: this.searchData.typeFormuleId ?? ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeParamsOffre = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les params architectures');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalParamOffreComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log('The dialog was closed', result);
      this.getParamsOffre();
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
    const methode = 'paramOffre/delete';
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
        this.getParamsOffre();
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
