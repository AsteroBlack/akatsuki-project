import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalRolesComponent } from './modal-roles/modal-roles.component';

export interface RoleInter{
  code: string,
  libelle: string,
  // actionId: string
}

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.scss']
})
export class RolesComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  rolesArrayColumn = [
    'num',
    'code',
    'libelle',
    'options'
  ];
  searchData: RoleInter = {
    libelle: '',
    code: '',
    // actionId: ''
  };
  listeRoles: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getRoles();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.getRoles();
  }

  rechercher(){
    this.page.index = 0
    this.getRoles();
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getRoles() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'role/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des rôles`,
      ndCode: '',
      customerCode: '',
      index:this.page.index,
      size:this.page.size,
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
        libelle: this.searchData.libelle ?? '',
        code: this.searchData.code ?? '',
        // act: this.searchData.libelle ?? '',
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeRoles = res && res.items ? res.items : [];
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
    const dialogRef = this.dialog.open(ModalRolesComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      this.getRoles();
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
    const methode = 'role/delete';
    const data = {
      //
      datas: [{
        id: param && param.id ? param.id : '',
      }]
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res:any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getRoles();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testMenu(action:string){
    return this.userService.checkMenuExiste('ADMINISTRATION','ROLE',action)
  }
}
