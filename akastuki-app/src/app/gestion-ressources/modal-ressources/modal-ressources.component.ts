import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { GestionRessourcesComponent } from '../gestion-ressources.component';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-modal-ressources',
  templateUrl: './modal-ressources.component.html',
  styleUrls: ['./modal-ressources.component.scss']
})
export class ModalRessourcesComponent implements OnInit {
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
  // @Input() param: any;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<GestionRessourcesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        // id: new FormControl(null),
        clientNomClient: new FormControl(null, [Validators.required]),
        oltLibelle: new FormControl(null, [Validators.required]),
        clusterId: new FormControl(null, [Validators.required]),
        pbNomTiroir: new FormControl(null, [Validators.required]),
        pbNonTiroirId: new FormControl(null, [Validators.required]),
        pbCoupleur: new FormControl(null, [Validators.required]),
        pbCoupleurId: new FormControl(null, [Validators.required]),
        sortieCoupleur: new FormControl(null, [Validators.required]),
        pbCassette: new FormControl(null, [Validators.required]),
        pbCoupleurSortie: new FormControl(null, [Validators.required]),
        pbColorId: new FormControl(null, [Validators.required]),
        pbBrinColorId: new FormControl(null, [Validators.required]),
        cbNom: new FormControl(null, [Validators.required]),
        cbFibre: new FormControl(null, [Validators.required]),
        cbColorId: new FormControl(null, [Validators.required]),
        ptoNom: new FormControl(null, [Validators.required]),
        ptoFibre: new FormControl(null, [Validators.required]),
        ptoColorId: new FormControl(null, [Validators.required]),
        pbSiteId: new FormControl(null, [Validators.required]),
        longitude: new FormControl(null, [Validators.required]),
        latitude: new FormControl(null, [Validators.required]),
        typeSitePbId: new FormControl(null, [Validators.required]),
        pbEtatId: new FormControl(null, [Validators.required]),
        pbNom: new FormControl(null, [Validators.required]),
        pbPlot: new FormControl(null, [Validators.required]),
        disrupTrspId: new FormControl(null, [Validators.required]),
        fibreDistribution: new FormControl(null, [Validators.required]),
        fibreColorId: new FormControl(null, [Validators.required]),
        commentaire: new FormControl(null, [Validators.required]),
        status: new FormControl(null, [Validators.required]),
      });
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
    console.log('param data', this.data);
    this.formModal.patchValue(this.data);
    this.resourceId = this.data.id;
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
    this.getTypeOlt();
    this.getParamOltTehno();
    this.getParamPdType();
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
    // if(this)
    // let methode = '';
    // if(this.formModal.value.id){
    //     methode = 'disruptiveDistribution/update';
    // } else {
    //     methode = 'disruptiveDistribution/create';
    // }
    this.formModal.value.id = this.resourceId

    // this.formModal.value.longitude = this.addslashes(this.formModal.value.longitude)
    // this.formModal.value.latitude = this.addslashes(this.formModal.value.latitude)


    
    let methode = 'disruptiveDistribution/update';
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1',
      serviceLibelle: `Mise à jours des ressources en disruptive`,
      datas: [this.formModal.value]
  
    };
    console.log('data', JSON.stringify(data));
    // return;
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
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
}
