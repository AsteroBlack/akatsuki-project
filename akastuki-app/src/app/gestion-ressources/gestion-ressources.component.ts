import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { RestApiClientService } from '../shared/services/rest-api-client.service';
import { UserService } from '../shared/services/user.service';
import { ModalRessourcesComponent } from './modal-ressources/modal-ressources.component';
import { ToastrService } from 'ngx-toastr';
import { ModalUploadRessourceComponent } from './modal-upload-ressource/modal-upload-ressource.component';
import { PageEvent } from '@angular/material/paginator';
import * as FileSaver from 'file-saver';
import { ModalAttributionComponent } from './modal-attribution/modal-attribution.component';
import { SelectionModel } from '@angular/cdk/collections';
export interface PeriodicElement {
  nom: string;
  num: number;
  baie: number;
  carte: number;
  chassis: number;
  port: number;
  ont: number;
  TypeOlt: string;
  techno: string;
  typeSro: string;
  sro: string;
  typeTiroir: string;
  tiroir: string;
  plot: string;
  etat: string;
  client: string;
  nd: string;
  sn: string;
  mac: string;
}
interface Food {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-gestion-ressources',
  templateUrl: './gestion-ressources.component.html',
  styleUrls: ['./gestion-ressources.component.scss']
})
export class GestionRessourcesComponent implements OnInit {

  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  foods: Food[] = [
    {value: 'steak-0', viewValue: 'Steak'},
    {value: 'pizza-1', viewValue: 'Pizza'},
    {value: 'tacos-2', viewValue: 'Tacos'}
  ];
  // total: number = 0;
  tableFooterColumns: string[] = ['total'];
  listeRessourcesDisruptive: any = []
  displayedColumns: string[] = [
    'selection',
    // 'num',
    'oltTypeLibelle',
    'technoLibelle',
    'modelCarteLibelle',
    'oltLibelle',
    // 'clientMacClient',
    'chassis',
    'carte',
    'port',
    'ont',
    'typeCarteLibelle',
    'plaqueLibelle',
    'secteutLibelle',
    'quartierLibelle',
    //
    'pdTypeLibelle',
    'cluster',
    'pdNomTiroir',
    'pdPlot',
    'pdCoupleur',
    'pdEtatTranspLibelle',
    'siteLibelle',
    'pbPlot',
    'pbNomTiroir',
    'fibreDistribution',
    'paramTypePbSiteLibelle',
    'paramEtatLibelle',
    'pbNom',
    'pbCoupleur',
    'pbCassette',
    'sortieCoupleur',
    'paramColorLibelle',
    'clientIdClient',
    'clientNomClient',
    'clientSnClient',

    'clientMacClient',
    'clientNdClient',
    'clientLoginClient',
    'clientIdDossierClientLib',
    'clientIpClient',
    'clientMaskClient',
    'clientVlanClient',
    'clientOffreCodeClient',
    'ClientOffreLibClient',
    'clientTypeClientCode',

    // 'pdBrin',

    //Distribution


    // 'pbCoupleurSortie',
    // 'cbFibre',
    // 'ptoFibre',
    // 'cbNom',
    // 'ptoNom',

    // 'clusterId',
    // 'clusterSiteId',

    // 'commentaire',
    // 'disrupTrspId',

    // 'id',
    // 'oltId',

    // 'oltTypeId',
    // 'pbBrinColorId',

    // 'pbColorId',

    // 'pbEtatId',



    // 'pbSiteId',
    // 'pdEtatTransp',
    // 'pdTypeId',
    // 'ports',
    // 'technoId',
    // 'technoLibelle',

    'options',
    // 'pied'
    // 'typeSitePbId'
  ];
  searchData: any = {
    sro: '',
    port: '',
    etat: '',
    techno: '',
    type: '',
    nom: '',
    oltTypeId: '',
    nd:'',
    ressource: 'transport',
    architecture: 'legacy',
    pbCoupleur: '',
    coupleur: '',
    pdNomTiroir: ''
  };
  listeEtats: any[]= [];
  listeTechnos: any[]= [];
  listeTypeOlt: any[]= [];
  // dataSource = ELEMENT_DATA;
  selectedAll: any;
  selection = new SelectionModel(true, []);
  selectedRow: any = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getDisruptive();
    // console.log('%cteste style', 'color:green');
    this.getEtat();
    this.getOltType();
    this.getTechno();
    // this.toastService.success('Opération échouée', 'Problême de communication');
  }

  selectionAllRow(event: any){
    console.log('selection all row', this.selectedAll);
    if(!this.selectedAll){
      this.listeRessourcesDisruptive = this.listeRessourcesDisruptive.map((elt: any)=>{return{...elt, checked: true}})
      this.selectedRow = this.listeRessourcesDisruptive;
    } else {
      this.listeRessourcesDisruptive = this.listeRessourcesDisruptive.map((elt: any)=>{return{...elt, checked: false}})
      this.selectedRow = [];
    }
    console.log('selection all', this.selectedRow);
  }

  selectionRow(elt: any, index: number){
    // console.log('selection row', index);
    if(!elt.checked){
      // console.log('check', elt);
      this.selectedRow.push(elt);
    } else {
      // console.log('uncheck', elt);
      const pos = this.selectedRow.findIndex((val:any)=>val.id==elt.id)
      this.selectedRow.splice(pos, 1);
    }
    // console.log('this.selectedRow', this.selectedRow);
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getDisruptive();
  }

  rechercher(){
    console.log('this.searchData',this.searchData);

    this.page.index = 0
    this.getDisruptive();
  }

  exportRessources(){
    this.page.index = 0
    this.export();
  }

  export(){
    const methode = 'disruptiveDistribution/exportationDisruptive';
    const data = {
      connexionWs: true,
      serviceLibelle: `Exportation des ressources en disruptive`,
      ndCode: '',
      customerCode: '',
      user: '1118',
      data: {
        pbNomTiroir: this.searchData.pbNomTiroir ?? '',
        technoId: this.searchData.techno ?? '',
        oltTypeId: this.searchData.oltTypeId ?? '',
        pbCoupleur: this.searchData.pbCoupleur ?? '',
        sortieCoupleur: this.searchData.sortieCoupleur ? this.searchData.sortieCoupleur: '',
        pbCassette: this.searchData.pbCassette ?? '',
        pbCoupleurSortie: this.searchData.pbCoupleurSortie ? this.searchData.pbCoupleurSortie: '',
        pbColorId: this.searchData.pbColorId ?? '',
        pbBrinColorId: this.searchData.pbBrinColorId ?? '',
        cbNom: this.searchData.cbNom ?? '',
        cbFibre: this.searchData.cbFibre ?? '',
        cbColorId: this.searchData.cbColorId ?? '',
        ptoNom: this.searchData.ptoNom ?? '',
        ptoFibre: this.searchData.ptoFibre ?? '',
        ptoColorId: this.searchData.ptoColorId ?? '',
        pbSiteId: this.searchData.pbSiteId ?? '',
        longitude: this.searchData.longitude ?? '',
        latitude: this.searchData.latitude ?? '',
        typeSitePbId: this.searchData.typeSitePbId ?? '',
        pbEtatId: this.searchData.etat ?? '',
        pbNom: this.searchData.pbNom ?? '',
        pbPlot: this.searchData.pbPlot ?? '',
        disrupTrspId: this.searchData.disrupTrspId ?? '',
        fibreDistribution: this.searchData.fibreDistribution ? this.searchData.fibreDistribution: '',
        fibreColorId: this.searchData.fibreColorId ?? '',
        commentaire: this.searchData.commentaire ?? '',
        oltLibelle: this.searchData.port && this.searchData.port.split('/')[0] ? this.searchData.port.split('/')[0].trim() : '',
        // baie: this.searchData.ports && this.searchData.ports.split(':')[1] && this.searchData.ports.split(':')[1].split('-')[0] ? this.searchData.ports.split(':')[1].split('-')[0].trim() : '',
        chassis: this.searchData.port && this.searchData.port.split('/')[1] && this.searchData.port.split('/')[1] ? this.searchData.port.split('/')[1].trim() : '',
        carte: this.searchData.port && this.searchData.port.split('/')[2] && this.searchData.port.split('/')[2].trim() ? this.searchData.port.split('/')[2].trim() : '',
        port: this.searchData.port && this.searchData.port.split('/')[3] && this.searchData.port.split('/')[3].split(':')[0].trim() ? this.searchData.port.split('/')[3].split(':')[0] : '',
        ont: this.searchData.port && this.searchData.port.split('/')[3] && this.searchData.port.split('/')[3].split(':')[1] ? this.searchData.port.split('/')[3].split(':')[1].trim() : ''
        // chassis: this.searchData.ports && this.searchData.ports.split('/')[1] && this.searchData.ports.split('/')[1].split('-')[0] ? this.searchData.ports.split(':')[1].split('-')[0].trim() : '',
        // carte: this.searchData.ports && this.searchData.ports.split(':')[1] && this.searchData.ports.split(':')[1].split('-')[1] ? this.searchData.ports.split(':')[1].split('-')[1].trim() : '',
        // port: this.searchData.port && this.searchData.port.split(':')[1] && this.searchData.port.split(':')[1].split('-')[2] ? this.searchData.port.split(':')[1].split('-')[2].trim() : '',
        // ont: this.searchData.port && this.searchData.port.split(':')[1] && this.searchData.port.split(':')[1].split('-')[3] ? this.searchData.port.split(':')[1].split('-')[3].trim() : ''
      },
      searchParam: this.searchData?.plot ?? '',
      orderBy: 'asc',
      takeAll: false
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res) => {
        console.log('res disruptive', res);
        if (res && !res.hasError) {
         if(res.filePathDoc){
            FileSaver.saveAs(res.filePathDoc, `ressources_en_disruptive.xlsx`);
          }
        } else {
          this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'Impossible de télécharger car aucun élément trouvé');
        }

    }, (err) => {
      this.toastService.error('Erreur', 'Problême de communication au serveur');
    });
  }

  getTechno(){
    const methode = 'paramOltTehno/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des technos`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeTechnos = res && res.items ? res.items : [];
        // this.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les états');
      }
    }, (err) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getEtat() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'paramEtat/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des états`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeEtats = res && res.items ? res.items : [];
        // this.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les états');
      }
    }, (err) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getOltType() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'paramTypeOlt/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des types Olt`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res) => {
      if (res && !res.hasError) {
        this.listeTypeOlt = res && res.items ? res.items : [];
        // this.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les états');
      }
    }, (err) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getDisruptive(){
    console.log('param', this.searchData && this.searchData.port.split('/'));
    const methode ='disruptiveDistribution/getByCriteria';
    const data = {
      user: /*this.userService.getUser() ? this.userService.getUser().id : */'1',
      index: this.page.index,
      size: this.page.size,
      data: {
        pb: this.searchData.pb ?? '',
        pbNomTiroir: this.searchData.pbNomTiroir ?? '',
        technoId: this.searchData.techno ?? '',
        oltTypeId: this.searchData.oltTypeId ?? '',
        pbCoupleur: this.searchData.pbCoupleur ?? '',
        sortieCoupleur: this.searchData.sortieCoupleur ? this.searchData.sortieCoupleur: '',
        pbCassette: this.searchData.pbCassette ?? '',
        pbCoupleurSortie: this.searchData.pbCoupleurSortie ? this.searchData.pbCoupleurSortie: '',
        pbColorId: this.searchData.pbColorId ?? '',
        pbBrinColorId: this.searchData.pbBrinColorId ? this.searchData.pbBrinColorId: '',
        cbNom: this.searchData.cbNom ?? '',
        cbFibre: this.searchData.cbFibre ?? '',
        cbColorId: this.searchData.cbColorId ?? '',
        ptoNom: this.searchData.ptoNom ?? '',
        ptoFibre: this.searchData.ptoFibre ?? '',
        ptoColorId: this.searchData.ptoColorId ?? '',
        pbSiteId: this.searchData.pbSiteId ?? '',
        longitude: this.searchData.longitude ?? '',
        latitude: this.searchData.latitude ?? '',
        typeSitePbId: this.searchData.typeSitePbId ? this.searchData.typeSitePbId: '',
        pbEtatId: this.searchData.etat ?? '',
        pbNom: this.searchData.pbNom ?? '',
        pbPlot: this.searchData.pbPlot ?? '',
        disrupTrspId: this.searchData.disrupTrspId ?? '',
        fibreDistribution: this.searchData.fibreDistribution ?? '',
        fibreColorId: this.searchData.fibreColorId ?? '',
        commentaire: this.searchData.commentaire ?? '',
        oltLibelle: this.searchData.port && this.searchData.port.split('/')[0] ? this.searchData.port.split('/')[0].trim() : '',
        // baie: this.searchData.ports && this.searchData.ports.split(':')[1] && this.searchData.ports.split(':')[1].split('-')[0] ? this.searchData.ports.split(':')[1].split('-')[0].trim() : '',
        chassis: this.searchData.port && this.searchData.port.split('/')[1] && this.searchData.port.split('/')[1] ? this.searchData.port.split('/')[1].trim() : '',
        carte: this.searchData.port && this.searchData.port.split('/')[2] && this.searchData.port.split('/')[2].trim() ? this.searchData.port.split('/')[2].trim() : '',
        port: this.searchData.port && this.searchData.port.split('/')[3] && this.searchData.port.split('/')[3].split(':')[0].trim() ? this.searchData.port.split('/')[3].split(':')[0] : '',
        ont: this.searchData.port && this.searchData.port.split('/')[3] && this.searchData.port.split('/')[3].split(':')[1] ? this.searchData.port.split('/')[3].split(':')[1].trim() : ''
        // chassis: this.searchData.ports && this.searchData.ports.split('/')[1] && this.searchData.ports.split('/')[1].split('-')[0] ? this.searchData.ports.split(':')[1].split('-')[0].trim() : '',
        // carte: this.searchData.ports && this.searchData.ports.split(':')[1] && this.searchData.ports.split(':')[1].split('-')[1] ? this.searchData.ports.split(':')[1].split('-')[1].trim() : '',
        // port: this.searchData.port && this.searchData.port.split(':')[1] && this.searchData.port.split(':')[1].split('-')[2] ? this.searchData.port.split(':')[1].split('-')[2].trim() : '',
        // ont: this.searchData.port && this.searchData.port.split(':')[1] && this.searchData.port.split(':')[1].split('-')[3] ? this.searchData.port.split(':')[1].split('-')[3].trim() : ''
      }
    }
    console.log('data disruptive', data);
    this.restApi.executeRessources(methode, data).subscribe((res)=>{
      console.log('res get')
      if(res && !res.hasError){
        this.listeRessourcesDisruptive = res.items.map((elt: any)=> {return{...elt, checked: false}});
        // console.log('listeRessourcesDisruptive', this.listeRessourcesDisruptive);
        this.page.total = res.count;
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
      }
    }, (err)=>{
      this.toastService.error('Opération échouée', 'Problême de communication');
    })
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  uploadRessource(param?: any){
    const dialogRef = this.dialog.open(ModalUploadRessourceComponent, {
      minWidth: '430px',
      disableClose: true,
      data:  this.listeTechnos
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      // this.animal = result;
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalRessourcesComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      // this.animal = result;
    });
  }
  openAttributionModal(param?: any){
    const dialogRef = this.dialog.open(ModalAttributionComponent, {
      width: '800px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      // this.animal = result;
      this.getDisruptive()
    });
  }

  askBeforeDelete(item?: any) {
    Swal.fire({
      title: '<div style="color:#fff">Attention!</div>',
      html: '<div style="color:#fff">Vous êtes sur le point de supprimer cet element. Continuer?</div>',
      showCancelButton: true,
      confirmButtonColor: '#dc3545',
      confirmButtonText: `Supprimer`,
      cancelButtonText: `Annuler`,
      background: '#000',

    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        // Swal.fire('Saved!', '', 'success')
        if(!item){
          if(this.selectedRow.length>0){
          this.deleteAction(this.selectedRow.map((elt:any)=>{return{id: elt.id}}));
         } else {
           return;
         }
       } else {
        this.deleteAction([{
          id: item && item.id ? item.id : '',
        }]);
       }
      } else if (result.isDenied) {
        // Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

  deleteAction(param?: any) {
    const methode = 'disruptiveDistribution/delete';
    const data = {
      user: /*this.userService.getUser() ? this.userService.getUser().id : */'1',
      datas: param
    };
    console.log('data', data);
    // return;
    this.restApi.executeRessources(methode, data).subscribe((res) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getDisruptive();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }
  testMenu(action:string){
    return this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','GESTION_DES_RESSOURCES',action)
  }
}
