import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalQuartierComponent } from './modal-quartier/modal-quartier.component';
import { Router } from '@angular/router';
import { SubMenus } from '../parametres-submenus';


@Component({
  selector: 'app-quartier',
  templateUrl: './quartier.component.html',
  styleUrls: ['./quartier.component.scss'],
  providers:[SubMenus],
})
export class QuartierComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  QuartierArrayColumn = [
    'num',
    'libelle',
    'secteurLibelle',
    // 'isUsed',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    libelle: '',
    secteurId: ''
  };
  listeQuartier: any[] = [];
  listeSecteur: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
    private router:Router,
    private subMenus:SubMenus
  ) {
    let mainMenu =  this.router.url.split('/')[this.router.url.split('/').length-2]
    // this.communicationService.sendData(mainMenu)
    console.log('listMenu.filter((lm:any)=>!lm.hidden)',subMenus.listMenu.filter((lm:any)=>!lm.hidden));

    if(subMenus.listMenu.filter((lm:any)=>!lm.hidden)[0]){
    let menu = subMenus.listMenu.filter((lm:any)=>!lm.hidden)[0].key
    this.router.navigate([mainMenu+'/'+menu.toLowerCase()])
    }
    else{
    this.logout()
    }
  }

  logout(){
    window.localStorage.removeItem('userData');
    this.router.navigate(['./auth']);
  }

  ngOnInit(): void {
    this.getQuartier();
    this.getSecteur();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getQuartier();
  }

  rechercher(){
    this.page.index = 0
    this.getQuartier();
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getSecteur() {
    const methode = 'secteur/getByCriteria';
    const data = {
      data: {

      }
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeSecteur = res.items ? res.items: [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getQuartier() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'quartier/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des quartiers`,
      index: this.page.index,
      size: this.page.size,
      data: {
        libelle: this.searchData.libelle ?? '',
        secteurId: this.searchData.secteurId ?? ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeQuartier = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les quartiers');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalQuartierComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log('The dialog was closed', result);
      this.getQuartier();
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
    const methode = 'quartier/delete';
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
        this.getQuartier();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testMenu(action:string){
    return this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES',action)
  }

}
