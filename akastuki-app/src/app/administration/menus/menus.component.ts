import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalMenusComponent } from './modal-menus/modal-menus.component';

@Component({
  selector: 'app-menus',
  templateUrl: './menus.component.html',
  styleUrls: ['./menus.component.scss']
})
export class MenusComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  listeModules: any = [];
  menusArrayColumn = [
    'num',
    'code',
    'libelle',
    'module',
    // 'parentMenusLibelle',
    'options'
  ];
  searchData: any = {
    libelle: '',
    code: '',
    moduleId: '',
  };
  listeMenus: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getMenus();
    this.getModule();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.getMenus();
  }

  rechercher(){
    this.page.index = 0
    this.getMenus();
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }


  getModule(param?: any) {
    const methode = 'module/getByCriteria';
    const data = {

      data: {

      }
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeModules = res.items ? res.items: [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getMenus() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'menus/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des Menus`,
      ndCode: '',
      customerCode: '',
      index:this.page.index,
      size:this.page.size,
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
        libelle: this.searchData.libelle ?? '',
        code: this.searchData.code ?? '',
        moduleId: this.searchData.moduleId ?? ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', JSON.stringify(data));
    this.restApi.execute(methode, data).subscribe((res: any) => {
      console.log('res menus', res);
      if (res && !res.hasError) {
        this.listeMenus = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les rôles');
      }
    }, (err) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalMenusComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      this.getMenus();
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
    const methode = 'menus/delete';
    const data = {

      datas: [{
        id: param && param.id ? param.id : '',
      }]
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res:any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getMenus();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testMenu(action:string){
    return this.userService.checkMenuExiste('ADMINISTRATION','MENU',action)
  }
}
