import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalDetailProvisionningComponent } from './modal-detail-provisionning/modal-detail-provisionning.component';
import { ModalProvisionningComponent } from './modal-provisionning/modal-provisionning.component';
import { ModalRallogementComponent } from './modal-rallogement/modal-rallogement.component';
import { CommunicationService } from 'src/app/shared/services/communication.service';
declare var $: any;
import * as moment from 'moment';
import * as FileSaver from 'file-saver';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { FormControl, FormGroup } from '@angular/forms';
declare var saveAs: any;


@Component({
  selector: 'app-provisionning',
  templateUrl: './provisionning.component.html',
  styleUrls: ['./provisionning.component.scss']
})
export class ProvisionningComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  provisionningColumn = [
    'num',
    'username',
    'groupename',
    'type',
    // 'mac',
    'suspended',
    'dateExpiration',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    mac: '',
    groupename: '',
    username: '',
    coeff: '',
    sn: '',
    nd: '',
    ip: ''
  };
  listeProvisionning: any[] = [];
  listeAttributs: any[] = [];
  listeModele: any[] = [];
  optionsList: any[] = [ 'Prepaid', 'postpaid'
    // { id: 'prepaid', name: 'Prepaid' },
    // { id: 'postpaid', name: 'postpaid' }
  ]
  dateRange: FormGroup;
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
    private communicationService:CommunicationService
  ) {
    this.dateRange = new FormGroup({
      startDate: new FormControl(moment().format('DD/MM/YYYY')),
      endDate: new FormControl(moment().format('DD/MM/YYYY'))
    });
    this.communicationService.getData().subscribe(
      res=>{
        // this.searchData.username = res
        console.log('message communication',res.items);
        this.searchData.mac = res.items.mac
        this.searchData.nd = res.items.nd

        this.getProvisionning()

      }

  )
   }

  ngOnInit(): void {
    this.getAttribut();
    this.getModele();
    // this.getProvisionning();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getProvisionning();
  }

  rechercher(){
    this.page.index = 0
    // this.getMacAdresse(this.searchData);
    this.getProvisionning(this.searchData);
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }


  getAttribut(param?: any) {
    const methode = 'attribut/getByCriteria';
    const data = {

      data: {

      }
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeAttributs = res.items ? res.items: [];

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  showDetail(param:any){
    const dialogRef = this.dialog.open(ModalDetailProvisionningComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log('The dialog was closed', result);
      this.getProvisionning();
      // this.animal = result;
    });
  }

  getModele(param?: any) {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'modele/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des modeles`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeModele = res && res.items ? res.items : [];
        // this.searchData.coeff = this.listeModele.length>0 && this.listeModele[0].coefficientModele.coefMac
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les Modeles');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getMacAdresse(param?: any) {
    const methode = 'radius/getMacAdresse';
    const data = {

      data: {
       username: this.searchData.username,
       coeff: this.searchData.coeff,
       serialNumber: this.searchData.sn,
       nd: this.searchData.nd,
       ipAddress: this.searchData.ip,

      }
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        // this.formModal.controls.username.patchValue(res.items ? res.items[0].mac: '');
        // this.listeAttributs = res.items ? res.items: [];
        this.getProvisionning({username: res.items[0].mac})
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    console.log('event', type, event);
    // this.events.push(`${type}: ${event.value}`);
  }

  export(param?: any) {
    console.log('range date', moment(this.dateRange.value.startDate).format('DD/MM/YYYY'))
    const methode = 'provisioning/exportUsername';
    const data = {
      //  startDate: param.start,
      //  endDate: param.end,
        startDate: moment(this.dateRange.value.startDate).format('DD/MM/YYYY'),
        endDate: moment(this.dateRange.value.endDate).format('DD/MM/YYYY')
    };
    console.log('data', data);
    this.restApi.executeWithFileRadius(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && res.byteLength > 0) {
        // const file = new Blob([res], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
        // FileSaver.saveAs(file, `provisionning_username.xlsx`);
        const file = new Blob([res], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
        FileSaver.saveAs(file, `provisionning_username.xlsx`);
      } else {
        this.toastService.error('Opération échouée', 'Impossible de télécharger car aucun élément trouvé');
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getProvisionning(param?: any) {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'coefficientModele/logicToSearch';
    const data = {
      index: this.page.index,
      size: this.page.size,
      serviceLibelle: `Consultation de la liste des provisionnings`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
        //  username: this.searchData.username,
         mac:this.searchData.mac || undefined,
         nd:this.searchData.nd || undefined

        // coeff: this.searchData.coeff,
        // serialNumber: this.searchData.sn,
        // nd: this.searchData.nd,
        // ipAddress: this.searchData.ip,
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', JSON.stringify(data));
    this.restApi.execute(methode, data).subscribe((res: any) => {
      console.log('res radius', res);
      if (res && !res.hasError) {
        this.listeProvisionning = res && res.items ? res.items : [];
        if(this.listeProvisionning && this.listeProvisionning[0]){
          this.listeProvisionning[0].groupename = this.listeProvisionning[0].dataGroup.value
          this.listeProvisionning[0].dateExpiration = this.listeProvisionning[0].type=='prepaid'?this.listeProvisionning[0].dateExpiration:''
        }


        console.log('listeProvisionning',this.listeProvisionning);

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
    if(param){
      param.mac=this.searchData.mac;
      param.nd = this.searchData.nd
    }
    const dialogRef = this.dialog.open(ModalProvisionningComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log('The dialog was closed', result);
      // this.getProvisionning();
      // this.animal = result;
    });
  }


  openExpiration(item: any) {
    const dialogRef = this.dialog.open(ModalRallogementComponent, {
      width: '500px',
      disableClose: true,
      data: item
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log('The dialog was closed', result);
      // this.getProvisionning();
      // this.animal = result;
    });
  }

  reactiver(param?: any) {
    const methode = 'provisioning/activate';
    const  data = {

      datasProvisioning: [

        {
          username: param.username,
          type: param.type,
          datasParameter: param.type=='prepaid'?[
              {
                key: "Expiration",
                value: moment().add(1, 'days').format("DD/MM/YYYY"),
                type: "check"
              }
          ]:undefined
      }

    ]
    }
    console.log('data', data);
    this.restApi.executeSystem(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getProvisionning({username: param.username});
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  suspendre(param?: any) {
    const methode = 'provisioning/activate';
    const data = {

      datasProvisioning: [

        {
          username: param.username,
          type: param.type,
          datasParameter: [
              {
                key: "Expiration",
                value: moment().subtract(1, 'days').format("DD/MM/YYYY"),
                type: "check"
              }
          ]
      }

    ]
    };
    console.log('data suspendre', JSON.stringify(data));
    this.restApi.executeSystem(methode, data).subscribe((res: any) => {
      console.log('res suspendre', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getProvisionning({username: param.username});
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  askBeforeDelete(item: any, type?: string) {
    console.log('action ', item);
    Swal.fire({
      title: '<div style="color:#fff">Attention!</div>',
      html: `<div style="color:#fff">Vous êtes sur le point de ${type} ce client. Continuer?</div>`,
      showCancelButton: true,
      confirmButtonColor: '#dc3545',
      confirmButtonText: `<div style="text-transform: capitalize;">${type}</div>`,
      cancelButtonText: `Annuler`,
      background: '#000',

    }).then((result: any) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        // Swal.fire('Saved!', '', 'success')
        switch(type){
          case 'supprimer':
            this.deleteAction(item);
            break;
          case 'suspendre':
             this.suspendre(item);
             break;
          case 'reactiver':
            this.reactiver(item);
            break;
        }
      } else if (result.isDenied) {
        // Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

  askBeforeSuspension(item: any) {
    console.log('action ', item);
    Swal.fire({
      title: '<div style="color:#fff">Attention!</div>',
      html: `<div style="color:#fff">Vous êtes sur le point de suspendre ce client. Continuer?</div>`,
      showCancelButton: true,
      confirmButtonColor: '#dc3545',
      confirmButtonText: `Suspendre`,
      cancelButtonText: `Annuler`,
      background: '#000',
      // // input: 'text',
      // input: 'select',
      // inputOptions: {
      //   'postpaid': 'postpaid',
      //   'prepaid': 'prepaid',
      // },

    }).then((result: any) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        console.log('value',result.value);
        // Swal.fire('Saved!', '', 'success')
        this.suspendre(item);
        // this.suspendre({...item, type: result.value});
      } else if (result.isDenied) {
        // Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

  deleteAction(param?: any) {
    const methode = 'provisioning/delete';
    const data = {

      datas: [{
        id: param && param.id ? param.id : '',
      }]
    };
    console.log('data', data);
    this.restApi.executeSystem(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        // this.getProvisionning();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testMenu(action:string){
    return this.userService.checkMenuExiste('RADIUS_FTTH','PROVISIONNING',action)
  }
}
