import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators,FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { GestionRessourcesComponent } from '../gestion-ressources.component';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/shared/services/user.service';
import { PageEvent } from '@angular/material/paginator';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-modal-attribution',
  templateUrl: './modal-attribution.component.html',
  styleUrls: ['./modal-attribution.component.scss']
})
export class ModalAttributionComponent implements OnInit {
  options = [
   {libelle:"OUI",valeur:true} ,
   {libelle:"NON",valeur:false} ,

  ]

  typeAttribution = [
    {libelle:"Attribution en masse",valeur:true} ,
    {libelle:"Attribution unitaire",valeur:false} ,
 
   ]



  RessourceArrayColumn = [
    // 'num',
    'cluster',
    'pbNom',
    'ports',
    'pbNomTiroir',
    'pbCoupleur',
    'paramColorLibelle',
    'options',
    // 'datasRole'
  ];



  page: any = {
    index: 0,
    size: 10,
    total: 0
  };

  formModal: FormGroup;
  listeOlt: any = [];
  listeRfo: any = [];
  listeNro: any = [];
  listePort: any = [];
  listeInternalCluster: any = [];
  listePdTypeTiroir: any = [];
  listePdType: any = [];
  listePdNomTiroir: any = [];
  listeCarte: any = [];
  listeParamColor: any = [];
  listeParamPa: any = [];
  listeParamPb: any = [];
  listeParamColorDisruptive: any = [];
  listeCoupleur: any = [];
  listeEtat: any = [];
  listeCity: any = [];
  listeSite: any = [];
  listeCassette: any = [];
  listeParamTypePbSite: any = [];
  listeFournisseur: any = [];
  listeTypeOlt: any = [];
  listeTechno: any = [];
  listeParamPdType: any = [];
  isLoading: boolean = false;
  resourceId: any;

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  activatedStep: number =0;
  listeParamTypeClient: any;
  listeRessourcesDisponibles: [];
  clientId: any;
  selectedRessource:any = {};
  nomFichier: any;
  totalItems: any;
  // @Input() param: any;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<GestionRessourcesComponent>,
    
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        // id: new FormControl(null),
        // clientNomClient: new FormControl(null, [Validators.required]),
        searchClient: new FormControl(null),
        technoId: new FormControl(null),

        isNouveauClient:new FormControl(false),

        clientLoginClient:new FormControl(null),
        clientNdClient:new FormControl(null,[Validators.required]),
        clientNomClient:new FormControl(null,[Validators.required]),
        clientSnClient:new FormControl(null),
        clientMacClient:new FormControl(null,[Validators.required]),
        clientTypeClientId:new FormControl(null,[Validators.required]),
        clientMaskClient:new FormControl(null),
        clientContratBiling:new FormControl(null),
        clientVlanClient:new FormControl(null),
        clientOffreCodeClient:new FormControl(null),
        clientOffreLibClient:new FormControl(null),
        clientLocalisationClient:new FormControl(null),
        clientIpClient:new FormControl(null),
        isAttributionEnMasse:new FormControl(true),
        // techno:new FormControl(null),
        file:new FormControl(null),
        pb:new FormControl(null),

      });
    }
    onSearchClient(param:any){
      console.log('this.formModal.value',this.formModal.value);
    

    let request = {
      user: '1',

      data: {
        typeAction: this.formModal.value.isNouveauClient?"nouveau client":"client exitant",
        searchClient: this.formModal.value.searchClient,
        // technoId: this.formModal.value.technoId,

        clientLoginClient: this.formModal.value.clientLoginClient,
        clientNdClient: this.formModal.value.clientNdClient,
        clientNomClient: this.formModal.value.clientNomClient,
        clientSnClient: this.formModal.value.clientSnClient,
        clientMacClient: this.formModal.value.clientMacClient,
        clientTypeClientId:this.formModal.value.clientTypeClientId,
        clientMaskClient: this.formModal.value.clientMaskClient,
        clientContratBiling: this.formModal.value.clientContratBiling,
        clientVlanClient: this.formModal.value.clientVlanClient,
        clientOffreCodeClient: this.formModal.value.clientOffreCodeClient,
        clientOffreLibClient: this.formModal.value.clientOffreLibClient,
        clientLocalisationClient: this.formModal.value.clientLocalisationClient,
        clientIpClient: this.formModal.value.clientIpClient,

      }
  }
    console.log('data sent to onSearchClient',JSON.stringify(request));
    

    this.restApiClient.executeRessources('managRessources/searchClient', request).subscribe((res: any) => {
      console.log('res param pd type', res);
      if (res && !res.hasError) {

        
        this.listeParamPdType = res.items ? res.items : [];

        if(res.items[0].ressourceExist){
          Swal.fire({
            title: '<div style="color:#fff">Attention!</div>',
            html: `<div style="color:#fff">
            Ce client dispose dejà d'une resource
            
            <table class="table table-bordered" style="color:#fff; margin-top:15px">
            <tr>
            <th>PB</th>
            <th>PORT</th>
            <th>Tiroir Transport</th>
            <th>Coupleur Transport</th>
            <th>PlotT ransport</th>
            <th>Tiroir Distribution</th>
            <th>Coupleur Distribution </th>
            <th>Plot Distribution</th>
            <th>BRIN</th>
           
          </tr>
          <tr>
            <td>`+res.items[0].cluster+`/`+res.items[0].pbNom+`</td>
            <td>`+res.items[0].ports+`</td>
            <td>`+res.items[0].pdNomTiroir+`</td>
            
            <td>`+res.items[0].pdCoupleur+`</td>
            <td>`+res.items[0].pdPlot+`</td>
            <td>`+res.items[0].pbNomTiroir+`</td>
            <td>`+res.items[0].pbCoupleur+`</td>

            <td>`+res.items[0].pbPlot+`</td>
            <td>`+res.items[0].paramColorLibelle+`</td>
          </tr>
        
        
        </table>
        
        </body>
        </html>
        
            </div>
            `,
            showCancelButton: true,
            confirmButtonColor: '#dc3545',
            confirmButtonText: `Continuer`,
            width: '1200px',
            cancelButtonText: `Annuler`,
            background: '#23272b',
            
          }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
              this.clientId = res.items[0].clientId;
              this.activatedStep=1;
            } else if (result.isDenied) {
              // Swal.fire('Changes are not saved', '', 'info')
              return;
            }
          })
          return
        }


        if(!res.items[0].clientExist && this.formModal.value.isNouveauClient){
          this.activatedStep=1
        }
        if(res.items[0].clientExist && !this.formModal.value.isNouveauClient){
          this.clientId = res.items[0].clientId;
          // this.activatedStep=1;
          // this.toastService.error('Opération échouée',  "Ce client n'existe pas ");
          this.activatedStep=1;
        }
        if(!res.items[0].clientExist && !this.formModal.value.isNouveauClient){
          this.clientId = res.items[0].clientId;
          // this.activatedStep=1;
          this.toastService.error('Opération échouée',  "Ce client n'existe pas ");
        }
       
  
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramPdType');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });

    }


    onSearchRessourcesDisponibles(){
      console.log('this.formModal.value',this.formModal.value);
      
    let request = {
      user: '1',
      index: this.page.index,
      size: this.page.size,

      data: {
        pbEtatId: '2',
        pb: this.formModal.value.pb,
        technoId:this.formModal.value.technoId,

      }
  }
    console.log('data sent to onSearchRessourcesDisponibles',JSON.stringify(request));
    

    this.restApiClient.executeRessources('disruptiveDistribution/getRessouceDisponible', request).subscribe((res: any) => {
      console.log('res param onSearchRessourcesDisponibles', res);
      if (res && !res.hasError) {
        this.listeRessourcesDisponibles = res.items ? res.items : [];
        this.totalItems = res.count
  
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les ressources disponibles');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });

    }
  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {




    console.log('param data', this.data);
    // this.formModal.patchValue(this.data);
    // this.resourceId = this.data.id;
    this.getOlt();
    // this.getParamPb();
    this.getPdType();
    // this.getParamPa();
    this.getSite();
    this.getCassette();
    this.getParamTypePbSite();
    // this.getFournisseur();
    this.getPdCoupleur();
    this.getEtat();
    this.getPdTypeTiroir();
    this.getParamTiroirTrsp();
    // this.getParamColorDisruptive();
    this.getParamColor();
    this.getCluster();
    // this.getRfo();
    // this.getPort();
    this.getParamTypeClient()
    this.getTypeOlt();
    this.getParamOltTehno();
    this.getParamPdType();
  }
  onNextStep(){
    this.activatedStep ++;
  }
  onPrevStep(){
    this.activatedStep --;
  }
  activateStep(step:number){
    this.activatedStep = step;
  }

  tiroirChange(event:any){
    console.log('event change', event);
  }

  getSite() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'site/getByCriteria';
    const data = {
      serviceLibelle: `Liste des sites`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        // libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res param pd type', res);
      if (res && !res.hasError) {
        this.listeSite = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les sites');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamPdType() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramPdType/getByCriteria';
    const data = {
      serviceLibelle: `Liste des paramPdType`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        // libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res param pd type', res);
      if (res && !res.hasError) {
        this.listeParamPdType = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramPdType');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamTypeClient() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramTypeClient/getByCriteria';
    const data = {
      serviceLibelle: `Liste des paramTypeClient`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        // libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res  paramTypeClient', res);
      if (res && !res.hasError) {
        this.listeParamTypeClient = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramPdType');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamOltTehno() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramOltTehno/getByCriteria';
    const data = {
      serviceLibelle: `Liste des paramOltTehno`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        // libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeTechno = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramOltTehno');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getTypeOlt() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramTypeOlt/getByCriteria';
    const data = {
      serviceLibelle: `Liste de Paramétrage des types Olt`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        // libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeTypeOlt = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les types olt');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamColorDisruptive() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramColorDisruptive/getByCriteria';
    const data = {
      serviceLibelle: `Liste des paramPb`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        // libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res paramPb');
      if (res && !res.hasError) {
        this.listeParamColorDisruptive = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramPbs');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamPb() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramPb/getByCriteria';
    const data = {
      serviceLibelle: `Liste des paramPb`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        // libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res paramPb');
      if (res && !res.hasError) {
        this.listeParamPb = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramPbs');
      }
    }, (eerr: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamColor() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramColor/getByCriteria';
    const data = {
      serviceLibelle: `Liste de Paramétrage des paramColors`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        // libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res param color');
      if (res && !res.hasError) {
        this.listeParamColor = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les param color');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getEtat() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramEtat/getByCriteria';
    const data = {
      serviceLibelle: `Liste de Paramétrage des Etats`,
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
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeEtat = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les états');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getOlt() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'olt/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des olt`,
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
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeOlt = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les états');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getCarte(param?: any) {
    // const methode = 'paramTypeCarte/getByCriteria';
    const methode = 'disruptiveTransport/logicToGetInformation';
    const data = {
      serviceLibelle: `Consultation des cartes disponibles de l'OLT sélectionné.`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      datas:[
        {
          olt: 1,
          typeCarte: param && param.modelId ? param.modelId : ''
        }
      ],
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data carte', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeCarte = res && res.items && res.items[0] ? res.items[0].datasCartes : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les cartes');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getCluster(param?: any) {
    console.log('param cluster', param);
    const methode = 'cluster/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des cluster`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      isSimpleLoading: false,
      data: {
        nro: param ? param.nro : ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data cluster', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeInternalCluster = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les clusters');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getPdType() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramPdType/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des paramPdType`,
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
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listePdType = res && res.items ? res.items : [];
        // this.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible des paramPdType');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getPortOlt(param?: any) {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'disruptiveTransport/logicToGetInformation';
    const data = {
      serviceLibelle: `Consultation des Olt disponible`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      datas: [{
        olt: 1,
        typeCarte: 1,
        carte: this.formModal.controls.carte.value.carte
      }],
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res olt', res);
      if (res && !res.hasError) {
        // this.listeOlt = res.items && res.items[0] ? res.items[0].datasPorts : [];
        this.listePort = res.items && res.items[0] ? res.items[0].datasCartes : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les disruptiveTransport');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamTypePbSite(param?: any) {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramTypePbSite/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des paramTypePbSite`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        moduleRfoNroId: param ? param.id : ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data paramTypePbSite', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeParamTypePbSite = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramTypePbSite');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getCassette() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramCassette/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des cassettes`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data rfo', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeCassette = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramPdType');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamPa() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramPa/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des paramPa`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data paramPa', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeParamPa = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramPa');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getFournisseur() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'fournisseur/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des fournisseurs`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data fournisseur', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res:any) => {
      if (res && !res.hasError) {
        this.listeFournisseur = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les fournisseurs');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getPdCoupleur() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramCoupleur/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des paramDisruptvieCoupleur`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data paramCoupleur', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeCoupleur = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramDisruptvieCoupleur');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getPdTypeTiroir() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramTypeTiroir/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des paramTypeTiroir`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data paramTypeTiroir', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listePdTypeTiroir = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramTypeTiroir');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamTiroirTrsp() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'paramTiroirTrsp/getByCriteria';
    const data = {
      serviceLibelle: `Consultation des paramTiroirTrsp`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data paramTiroirTrsp', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res:any) => {
      if (res && !res.hasError) {
        this.listePdNomTiroir = res && res.items ? res.items : [];
        console.log('la liste de listePdNomTiroir',this.listePdNomTiroir);
        
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les paramTiroirTrsp');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

//   addslashes(data:string) {
//     return data.
//         replace(/\\/g, '\\\\').
//         replace(/\u0008/g, '\\b').
//         replace(/\t/g, '\\t').
//         replace(/\n/g, '\\n').
//         replace(/\f/g, '\\f').
//         replace(/\r/g, '\\r').
//         replace(/'/g, '\\\'').
//         replace(/"/g, '\\"');
// }

  onSubmit(param: any) {
    this.isLoading = true;
    let methode = 'disruptiveDistribution/update';
    const data = {
      user: "1",
      ressourceModel: {
        typeAction: this.formModal.value.isNouveauClient?"nouveau client":"client exitant",
        client: this.clientId,
        dataClient: this.formModal.value.isNouveauClient?{
          login: this.formModal.value.clientLoginClient,
          nd: this.formModal.value.clientNdClient,
          nom: this.formModal.value.clientNomClient,
          sn: this.formModal.value.clientSnClient,
          mac: this.formModal.value.clientMacClient,
          typeClient:this.formModal.value.clientTypeClientId,
          mask: this.formModal.value.clientMaskClient,
          vlan: this.formModal.value.clientVlanClient,
          ip: this.formModal.value.clientIpClient,
          contratBiling: this.formModal.value.clientContratBiling,
          offreCode: this.formModal.value.clientOffreCodeClient,
          offreLib:this.formModal.value.clientOffreLibClient,
          localisation: this.formModal.value.clientLocalisationClient,
          }:{},
          idDistrib: this.selectedRessource.id
      }
  }
    console.log('data', JSON.stringify(data));
    // return;
    this.restApiClient.executeRessources('disruptiveDistribution/doAttributionDisruptivUnitaiteLite', data).subscribe((res: any) => {
        console.log('res methode', res);
        this.isLoading = false;
        if (res && !res.hasError) {
          this.toastService.success('Opération réussie', res.status && res.status.message ? res.status.message : 'Opération éffectuée avec succès');
          this.formModal.reset();
          this.dialogRef.close();
        } else {
          this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de l\'importation');
        }
    }, (err: any) => {
      this.isLoading = false;
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  onSubmitAttributionEnMasse(param: any){
    console.log('submit action', param);
    const methode = 'disruptiveDistribution/uploadWithCustomer';
    const formData: any =  new FormData();
    // formData.append('test', 'this.uploadForm.controls.techno.value');
    formData.append('techno', 1);
    formData.append('file', this.formModal.controls.file.value, this.formModal.controls.file.value.name);
    console.log('data', formData);
    this.restApiClient.executeFormDataResssources(methode, formData).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.dialogRef.close();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors l\'exécution de l\'opération');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.onSearchRessourcesDisponibles();
  }

  onSelectResource(element:any){
    console.log('element selected',element);
    this.selectedRessource = element
    this.selectedRessource.isSelected = true
    
  }

  processFile(event: any, doc?: any) {
    console.log('event file', event.target.files[0]);
    const reader = new FileReader();
    const file = event.target.files[0];
    let fileData;
    reader.onload = (readerEvent) => {
      const data = (readerEvent.target as any).result;
      console.log('data file', data.split('.')[0]);
      this.nomFichier = file.name;

      this.formModal.controls.file.patchValue(file);
      this.formModal.controls.file.updateValueAndValidity();
      event.stopPropagation();
        // console.log('dataDocument', this.formRegleAlerte.value);
    };
    // console.log('dataDocument', this.uploadForm.value);
    reader.readAsDataURL(event.target.files[0]);
    event.preventDefault();
  }
  
}
