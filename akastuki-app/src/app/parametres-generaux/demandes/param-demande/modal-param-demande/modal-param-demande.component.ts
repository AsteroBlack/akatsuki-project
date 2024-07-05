import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
@Component({
  selector: 'app-modal-param-demande',
  templateUrl: './modal-param-demande.component.html',
  styleUrls: ['./modal-param-demande.component.scss']
})
export class ModalParamDemandeComponent implements OnInit {
  formModal: FormGroup;
  nomFichier: string = '';
  listTypeOffre : any[] = [];
  listeModele : any[] = [];
  listeArrondissement: any[] = [];
  listeTypeClient: any;
  listeParamsOffre: any;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalParamDemandeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        id: new FormControl(null),
        nom: new FormControl(null, [Validators.required]),
        prenom: new FormControl(null, [Validators.required]),
        login: new FormControl(null, [Validators.required]),
        typeClient: new FormControl(null, [Validators.required]),
        nd: new FormControl(null, [Validators.required]),
        mac: new FormControl(null, [Validators.required]),
        sn: new FormControl(null, [Validators.required]),
        ip: new FormControl(null, [Validators.required]),
        mask: new FormControl(null, [Validators.required]),
        commentaire: new FormControl(null),
        offreId: new FormControl(null, [Validators.required]),
        // datasAction: new FormControl(null),
      });
    }

    ngAfterContentInit(){
      if (this.data) {
        this.formModal.patchValue(this.data);
        // this.title = "Modifi&&&&&&&&&&&&er un rôle";
      }
      // this.getLocalisation();
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
    this.getModele();
    this.getTypeClient()
    this.getParamsOffre()
  }

  getTypeClient() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'paramTypeClient/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des types clients`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        
        this.listeTypeClient = res && res.items ? res.items : [];
        console.log('listeTypeClient',this.listeTypeClient);

        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les types clients');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getParamsOffre() {
    // const methode = 'costomer-back-end-api-1.0/paramOffre/getByCriteria';
    // const methode = 'api-customer-care-fixe/getOffreCriteria';
    const methode = 'paramOffre/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des params architecture`,
      data: {
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeParamsOffre = res && res.items ? res.items : [];
        console.log('listeParamsOffre',this.listeParamsOffre);
        
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les params architectures');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  getModele(param?: any) {
    const methode = 'modeleCarte/getByCriteria';
    const data = {
      data: {
       
      }
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeModele = res.items ? res.items: [];

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getArrondissements(param?: any) {
    const methode = 'arrondissement/getByCriteria';
    const data = {
      data: {
       
      }
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeArrondissement = res.items ? res.items: [];

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  onSubmit(param: any){
    console.log('submit action', param);
    let methode = '';
    if(this.formModal.value.id){
        methode = 'demande/update';
    } else {
        methode = 'demande/create';
    }
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `${this.formModal.value.id ? 'Mise à jours' : 'Création'} de secteur`,
      datas: [this.formModal.value]
  
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
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
}
