import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalUtilisateursComponent } from './modal-utilisateurs/modal-utilisateurs.component';

export interface roleI{
  id: string;
}
export interface userI  {
  matricule: string;
  nom: string;
  prenom: string;
  login: string;
  email: string;
  password: string;
  contact: string;
  datasRole: roleI[];
}

@Component({
  selector: 'app-utilisateurs',
  templateUrl: './utilisateurs.component.html',
  styleUrls: ['./utilisateurs.component.scss']
})
export class UtilisateursComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  usersArrayColumn = [
    'num',
    'matricule',
    'nom',
    'prenom',
    'email',
    'login',
    'contact',
    'role',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    nom: '',
    prenom: '',
    login: '',
    matricule: '',
    email: '',
    contact: '',
  };
  listeUtilisateurs: any[] = [];
  userInfo: UserService;
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) {
    this.userInfo = this.userService
  }

  ngOnInit(): void {
    this.getUtilisateurs();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.getUtilisateurs();
  }

  rechercher(){
    this.page.index = 0
    this.getUtilisateurs();
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getUtilisateurs() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'user/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des utilisateurs`,
      ndCode: '',
      customerCode: '',
      index:this.page.index,
      size:this.page.size,
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
        nom: this.searchData.nom ?? '',
        matricule: this.searchData.matricule ?? '',
        prenom: this.searchData.prenom ?? '',
        login: this.searchData.login ?? '',
        contact: this.searchData.contact ?? '',
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeUtilisateurs = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.page.total = 0;
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les utilisateurs');
      }
    }, (err) => {
      this.page.total = 0;
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalUtilisateursComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      this.getUtilisateurs();
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
    const methode = 'user/delete';
    const data = {

      datas: [{
        id: param && param.id ? param.id : '',
      }]
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getUtilisateurs();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testUser(currentUser:any){
    // console.log('current user',currentUser.matricule);

    return  !(currentUser.matricule == this.userInfo.getUser().matricule)
  }
  testMenu(action:string){
    return this.userService.checkMenuExiste('ADMINISTRATION','UTILISATEUR',action)
  }
}
