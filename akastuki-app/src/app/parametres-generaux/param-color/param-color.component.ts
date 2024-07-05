import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalParamColorComponent } from './modal-param-color/modal-param-color.component';
import { Router } from '@angular/router';
import { CommunicationService } from 'src/app/shared/services/communication.service';
import { SubMenus } from '../parametres-generaux-submenus';

@Component({
  selector: 'app-param-color',
  templateUrl: './param-color.component.html',
  styleUrls: ['./param-color.component.scss'],
  providers: [SubMenus],
})
export class ParamColorComponent implements OnInit {

  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  ParamsColorArrayColumn = [
    'num',
    'libelle',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    code: '',
    libelle: '',
  };
  listeParamsColor: any[] = [];
  activeMenu: any;
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
    console.log('list submenus of parametres generaux in colors',subMenus.listMenu);
    console.log('subMenus.listMenu.filter((lm:any)=>!lm.hidden)',subMenus.listMenu.filter((lm:any)=>!lm.hidden));
    
    if(subMenus.listMenu.filter((lm:any)=>!lm.hidden)[0]){
      let menu = subMenus.listMenu.filter((lm:any)=>!lm.hidden)[0].key
      this.router.navigate([mainMenu+'/'+menu.toLowerCase()])
      }
      else{
      this.logout()
      }

  }
  

  ngOnInit(): void {
    this.getParamsColor();
  }

  logout(){
    window.localStorage.removeItem('userData');
    this.router.navigate(['./auth']);
  }


  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getParamsColor();
  }

  rechercher(){
    this.page.index = 0
    this.getParamsColor(this.searchData);
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getParamsColor(param?: any) {
    // const methode = 'costomer-back-end-api-1.0/paramColor/getByCriteria';
    // const methode = 'api-customer-care-fixe/getColorCriteria';
    const methode = 'paramColor/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des params color`,
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
        this.listeParamsColor = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les params color');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalParamColorComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log('The dialog was closed', result);
      this.getParamsColor();
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
    const methode = 'paramColor/delete';
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
        this.getParamsColor();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }
  testMenu(action:string){
    return this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX',action)
  }


}
