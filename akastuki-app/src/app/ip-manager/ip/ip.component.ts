import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { elementAt } from 'rxjs/operators';
import { RestApiClientService } from '../../shared/services/rest-api-client.service';
import { UserService } from '../../shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalIpComponent } from './modal-ip/modal-ip.component';
import { ModalGenerateAdresseComponent } from './modal-generate-adresse/modal-generate-adresse.component';
import { ModalSetupIpComponent } from './modal-setup-ip/modal-setup-ip.component';
import { ModalDetailPoolComponent } from './modal-detail-pool/modal-detail-pool.component';
import { ModalIPAddressComponent } from './modal-ip-adresse/modal-ip-address.component';
import { ModalIpBlocageComponent } from './modal-ip-blocage/modal-ip-blocage.component';
// import { ModalZoneComponent } from '../zone/modal-zone/modal-zone.component';
// import { ModalIpComponent } from './modal-ip/modal-ip.component';

@Component({
  selector: 'app-ip',
  templateUrl: './ip.component.html',
  styleUrls: ['./ip.component.scss']
})
export class IpComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  ipArrayColumn = [
    'num',
    'ipAddress',
    'mask',
    // 'cidrMask',
     'gateway',
     'beginAddr',
    'endAddr',
    'broadcast',
    'NbreAdresseDispo',
    'options',
    // 'datasRole'
  ];
  searchData = {
    code: '',
    libelle: '',
    nb: ''
  };
  listeIp: any[] = [];
  datasAllIp: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getIp();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getIp();
    // const startItem = (event.pageIndex-1) * this.page.size;
    // const endItem = event.pageIndex * this.page.size;
    // console.log('size change', endItem);
    // this.listeIp = this.datasAllIp.slice(startItem, endItem);
  }

  rechercher(){
    this.page.index = 0
    console.log('filter',this.listeIp.filter((elt)=> elt.libelle == this.searchData.libelle));
    // this.listeIp = this.listeIp.filter((elt)=> elt.libelle == this.searchData.libelle)
    if(this.searchData.nb.trim().length>0){
      this.dialog.open(ModalIPAddressComponent, {
        width: '600px',
        disableClose: true,
        data: this.searchData.nb
      });
    }
    else{
      this.getIp();
    }
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  ajout(item: any){
    console.log('ajouter', item);
    const dialogRef = this.dialog.open(ModalGenerateAdresseComponent, {
      width: '600px',
      disableClose: true,
      data: item
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed', result);
      this.getIp();
      // this.animal = result;
    });
  }

  edit(item: any){
    console.log('edit', item);
    const dialogRef = this.dialog.open(ModalSetupIpComponent, {
      width: '600px',
      disableClose: true,
      data: item
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed', result);
      this.getIp();
      // this.animal = result;
    });
  }

  getIp() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    // const methode = 'client/getByCriteria';
    const methode = 'client/getByCriteria';
    // const methode = 'client/getAllWithIpBands';
    const data = {
      index: this.page.index,
      size: this.page.size,
      serviceLibelle: `Consultation de la liste des clients`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
        libelle: this.searchData.libelle ?? '',
        // code: this.searchData.code ?? ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data getIp', JSON.stringify(data));
    this.restApi.executeIpManager(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        const allClient=  res && res.items ? res.items.map((elt: any)=> {return {...elt, opened: false,used:elt.used?elt.used:0}}) : []
        this.listeIp = allClient;
        // this.datasAllIp = allClient;
        // this.listeIp = allClient ? allClient.slice((this.page.index) * this.page.size,
        //  (this.page.index) * this.page.size + this.page.size) : [];
        console.log('liste ip', this.listeIp);
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les Zone');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any, idIp?:any){
    const dialogRef = this.dialog.open(ModalIpComponent, {
      width: '600px',
      disableClose: true,
      data: [param,idIp] ,
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed', result);
      this.getIp();
      // this.animal = result;
    });
  }

  openModalIpBlocage(param?: any, idIp?: any){
    const dialogRef = this.dialog.open(ModalIpBlocageComponent, {
      width: '600px',
      disableClose: true,
      data: [param,idIp] ,
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed', result);
      this.getIp();
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
    const methode = 'ip/delete';
    const data = {

      datas: [{
        id: param && param.id ? param.id : '',
      }]
    };
    console.log('data', data);
    this.restApi.executeIpManager(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getIp();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  ipPoolDetails(elt: any){
    const dialogRef = this.dialog.open(ModalDetailPoolComponent, {
      width: '600px',
      disableClose: true,
      data: elt
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed', result);
      this.getIp();
      // this.animal = result;
    });
  }

  testMenu(action:string){
    return this.userService.checkMenuExiste('GESTION_DES_IP','IP',action)
  }

}
