import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
// import { RestApiClientService } from '../../shared/services/rest-api-client.service';
import { RestApiClientService } from '../../shared/services/rest-api-client.service';
import { UserService } from '../../shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalRadiusComponent } from './modal-radius/modal-radius.component';

@Component({
  selector: 'app-radius',
  templateUrl: './radius.component.html',
  styleUrls: ['./radius.component.scss']
})
export class RadiusComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  pageGroupe: any = {
    index: 0,
    size: 10,
    total: 0
  };
  radiusArrayColumn = [
    'num',
    'offre',
    // 'groupeRadiusLibelle',
    'typeOffreLibelle',
    // 'localisation',
    'options',
    // 'datasRole'
  ];
  groupeRadiusArrayColumn = [
    'num',
    // 'code',
    'libelle',
    'typeOffreCode',
    // 'typeOffreLibelle',
    'localisationLibelle',
    // 'localisation',
    // 'groupeRadiusId',
    // 'groupeRadiusLibelle'
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    groupeRadiusId: '',
    libelle: '',
    typeOffreId: '',
    localisationId: '',
  };
  listeTypeOffre: any[] = [];
  listeOffres: any[] = [];
  listeLocalisation: any[] = [];
  listeGroupeRadius: any = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getOffres();
    this.getGroupeRadius();
    this.getLocalisation();
    this.getTypeOffre();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getOffres();
  }

  paginationChangeGroupe(event: PageEvent){
    console.log('page', event);
    this.pageGroupe.index = event.pageIndex;
    this.pageGroupe.size = event.pageSize;
    this.getGroupeRadius();
   
  }

  rechercher(){
    this.page.index = 0
    this.getOffres(this.searchData);
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
    this.restApi.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeLocalisation = res.items ? res.items: [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }


  getGroupeRadius(param?: any) {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'radgroup/getGroupeRadius';
    const data = {
      serviceLibelle: `Consultation de la liste des groupe radius`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {

      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data getGroupeRadius', JSON.stringify(data));
    this.restApi.executeRadiusFtth(methode, data).subscribe((res: any) => {
   
      console.log('response',res);
      
      if (res && !res.hasError) {
        this.listeGroupeRadius = res && res.items ? res.items : [];
        console.log('this.listeGroupeRadius',this.listeGroupeRadius);
        

        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les GroupeRadius');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getTypeOffre(param?: any) {
    const methode = 'typeOffre/getByCriteria';
    const data = {
      
      data: {
       localisationId: param && param.localisation ? param.localisation : ''
      }
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeTypeOffre = res.items ? res.items: [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  openRadius(item: any, index: number, etat: any){
    console.log('index', index);
    this.getOffres({groupeRadiusId: item.id}, index);
  }

  getOffres(param?: any, index?: number) {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    /* Get des offres (radius) */
    const methode = 'radius/getByCriteria';
    const data = {
      index: this.page.index,
      size: this.page.size,
      serviceLibelle: `Consultation de la liste des radius`,
      ndCode: '',
      customerCode: '',
      data: {
        groupeRadiusLibelle: param && param.groupeRadiusLibelle ? param.groupeRadiusLibelle : '',
        typeOffreId: param && param.typeOffreId ? param.typeOffreId : '',
        offre: param && param.offre ? param.offre : ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.execute(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        console.log('list des offres',res);
        
        // this.listeOffres = res && res.items ? res.items : [];
        this.listeOffres = res && res.items ? res.items.map((elt: any)=>{
            return{
              ...elt, 
              opened: false, 
              // children: []
              }
            }
          ) : [];
        // console.log('group index', this.listeGroupeRadius[index]);
        
        // if(typeof index !== 'undefined'){
        //   console.log('group index', this.listeGroupeRadius[index]);
        //   this.listeGroupeRadius[index].children = this.listeOffres;
        // }
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les Service');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any, row?:any){
    // console.log('param', param, row);
    const dialogRef = this.dialog.open(ModalRadiusComponent, {
      width: '600px',
      disableClose: true,
      data: {...param, ...row}
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      // console.log('The dialog was closed', result);
      this.getOffres();
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
    const methode = 'radius/delete';
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
        this.getOffres();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  testMenu(action:string){
    return this.userService.checkMenuExiste('GESTION_DES_OFFRES','OFFRES',action)
  }
}
