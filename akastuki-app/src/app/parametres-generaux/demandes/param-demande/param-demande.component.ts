import { Component, OnInit } from '@angular/core';

import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { CommunicationService } from 'src/app/shared/services/communication.service';
import { ModalParamDemandeComponent } from './modal-param-demande/modal-param-demande.component';
import { SubMenus } from '../demandes-submenus';
@Component({
  selector: 'app-param-demande',
  templateUrl: './param-demande.component.html',
  styleUrls: ['./param-demande.component.scss'],
  providers:[SubMenus],
})
export class ParamDemandeComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  ArrayColumn = [
    'num',
    'nom',
    'prenom',
    'login',
    'paramTypeClientLibelle',
    'ip',
    'mask',
    'paramOffreLibelle',
    'sn',
    'nd',
    'mac',
    'commentaire',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    code: '',
    libelle: '',
    modelId: '',
  };
  listData: any[] = [];
  listeModele: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
    private router:Router,
    private communicationService:CommunicationService,
    private subMenus:SubMenus
  ) {
    let mainMenu =  this.router.url.split('/')[this.router.url.split('/').length-2]
    this.communicationService.sendData(mainMenu)

    if(subMenus.listMenu.filter((lm:any)=>!lm.hidden)[0]){
      let menu = subMenus.listMenu.filter((lm:any)=>!lm.hidden)[0].key
      console.log('mainMenu olt',mainMenu);
      console.log('menu',menu);

      this.router.navigate(['parametres-generaux/'+mainMenu+'/'+menu.toLowerCase()])
      }
      else{
        console.log('login out');

      this.logout()
      }
  }
  logout(){
    window.localStorage.removeItem('userData');
    this.router.navigate(['./auth']);
  }
  ngOnInit(): void {
    this.getDemande();
    this.getModele();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getDemande();
  }

  rechercher(){
    this.page.index = 0
    this.getDemande();
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getModele(param?: any) {
    const methode = 'modeleCarte/getByCriteria';
    const data = {
      data: {

      }
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeModele = res.items ? res.items: [];

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getDemande() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'demande/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des olt`,
      index: this.page.index,
      size: this.page.size,
      data: {
        nom: this.searchData.nom ?? '',
        prenom: this.searchData.prenom ?? ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listData = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les OLT');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalParamDemandeComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log('The dialog was closed', result);
      this.getDemande();
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
    const methode = 'demande/delete';
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
        this.getDemande();
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
