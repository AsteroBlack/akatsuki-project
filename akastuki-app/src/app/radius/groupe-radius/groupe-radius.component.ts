import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../../shared/services/rest-api-client.service';
import { UserService } from '../../shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalGroupeRadiusComponent } from './modal-groupe-radius/modal-groupe-radius.component';

@Component({
  selector: 'app-groupe-radius',
  templateUrl: './groupe-radius.component.html',
  styleUrls: ['./groupe-radius.component.scss']
})
export class GroupeRadiusComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  groupeRadiusArrayColumn = [
    'num',
    'groupname',
    'attribute',
    'op',
    'value',
    'type',
    // 'options',
    // 'datasRole'
  ];
  searchData: any = {
    code: '',
    groupe: '',
  };
  listeGroupeRadius: any[] = [];
  listeGroupeRadiusCopy: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getGroupeRadius();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getGroupeRadius();
    // this.listeGroupeRadiusCopy=  this.listeGroupeRadius.slice((event.pageIndex - 1) * event.pageSize, event.pageIndex * event.pageSize);
  }

  rechercher(){
    this.page.index = 0
      this.getGroupeRadius();

    // this.getGroupeRadius(this.searchData);
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getGroupeRadius() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'radgroup/getAttributByGroupenameBackoffice';
    const data = {
      index: this.page.index,
      size: this.page.size,
      serviceLibelle: `Consultation de la liste des provisionning`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      dataProvisioning:{
        groupename:this.searchData.groupename ?? '',
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data to getGroupeRadius', JSON.stringify(data));
    // this.restApi.getSystem(methode).subscribe((res: any) => {
      this.restApi.executeRadiusFtth(methode, data).subscribe((res: any) => {
      console.log('res get', res);
      if (res && !res.hasError) {
        this.listeGroupeRadius = res && res.items ? res.items : [];
        this.listeGroupeRadiusCopy = [...this.listeGroupeRadius]
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les Provisionning');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    console.log('datas to modif',param);

    const dialogRef = this.dialog.open(ModalGroupeRadiusComponent, {
      width: '800px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log('The dialog was closed', result);
      this.getGroupeRadius();
      // this.animal = result;
    });
  }

  askBeforeDelete(item: any) {
    console.log('to be delete',item);

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
    console.log('to delete',param);

    const methode = 'radgroup/deleteRadGroupAttribute';
    const data = {

      datasRadGroup:[
        {
          groupename: param.key
        }
      ]

    };
    console.log('data', data);
    this.restApi.executeSystem(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getGroupeRadius();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testMenu(action:string){
    return this.userService.checkMenuExiste('RADIUS_FTTH','GROUPE_RADIUS',action)
  }

}
