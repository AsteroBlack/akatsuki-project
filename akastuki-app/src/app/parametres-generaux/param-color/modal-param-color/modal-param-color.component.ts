import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
// import { RolesComponent } from '../roles.component';
declare var $: any;
@Component({
  selector: 'app-modal-param-color',
  templateUrl: './modal-param-color.component.html',
  styleUrls: ['./modal-param-color.component.scss']
})
export class ModalParamColorComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  nomFichier: string = '';
  listTypeOffre : any[] = [];
  listeLocalisation : any[] = [];
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalParamColorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        id: new FormControl(null),
        libelle: new FormControl(null, [Validators.required]),
        // localisationId: new FormControl(null, [Validators.required]),
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
  }


  // getLocalisation(param?: any) {
  //   const methode = 'localisation/getByCriteria';
  //   const data = {
  //     
  //     data: {
       
  //     }
  //   };
  //   console.log('data', data);
  //   this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
  //     console.log('res', res);
  //     if (res && !res.hasError) {
  //       this.listeLocalisation = res.items ? res.items: [];

  //     } else {
  //       this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
  //       // this.listeUser = [];
  //     }
  //   }, (err) => {
  //     this.toastService.error('Opération échouée', 'Problême de communication');
  //   });
  // }

  onSubmit(param: any){
    console.log('submit action', param);
    let methode = '';
    if(this.formModal.value.id){
        methode = 'paramColor/update';
    } else {
        methode = 'paramColor/create';
    }
    const data = {
      ndCode: '',
      customerCode: '',
      // user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `${this.formModal.value.id ? 'Mise à jours' : 'Création'} de params color`,
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
