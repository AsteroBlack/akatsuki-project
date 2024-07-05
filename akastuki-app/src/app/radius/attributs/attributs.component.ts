import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
// import { ModalAttributsComponent } from 'src/app/ip-manager/Attributs/modal-Attributs/modal-Attributs.component';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalAttributsComponent } from './modal-attributs/modal-attributs.component';

@Component({
  selector: 'app-attributs',
  templateUrl: './attributs.component.html',
  styleUrls: ['./attributs.component.scss']
})
export class AttributsComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  AttributsArrayColumn = [
    'num',
    'attribut',
    'type',
    'value',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    value: '',
    attribut: '',
    type: '',
  };
  listeLocalisation: any[] = [];
  listeAttributs: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getAttributs();
    // this.getLocalisation();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getAttributs();
  }

  rechercher(){
    this.page.index = 0
    this.getAttributs();
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getLocalisation(param?: any) {
    const methode = 'localisation/getByCriteria';
    const data = {

      data: {

      }
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeLocalisation = res.items ? res.items: [];

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getAttributs() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'attribut/getByCriteria';
    const data = {
      index: this.page.index,
      size: this.page.size,
      serviceLibelle: `Consultation de la liste des attributs`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
        attribut: this.searchData.attribut ?? '',
        value: this.searchData.value ?? '',
        type: this.searchData.type ?? ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeAttributs = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les attributs');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalAttributsComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log('The dialog was closed', result);
      this.getAttributs();
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
    const methode = 'attribut/delete';
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
        this.getAttributs();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testMenu(action:string){
    return this.userService.checkMenuExiste('RADIUS_FTTH','ATTRIBUT',action)
  }
}
