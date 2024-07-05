import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
// import { RolesComponent } from '../roles.component';
declare var $: any;
@Component({
  selector: 'app-modal-radius',
  templateUrl: './modal-radius.component.html',
  styleUrls: ['./modal-radius.component.scss']
})
export class ModalRadiusComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  listTypeOffre : any[] = [];
  listeGroupeRadius : any[] = [];
  listeLocalisation : any[] = [];
  editMode: boolean =false;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalRadiusComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        id: new FormControl(null),
        offre: new FormControl(null, [Validators.required]),
        groupeRadiusLibelle: new FormControl(null, [Validators.required]),
        localisationId: new FormControl(null, [Validators.required]),
        // datasAction: new FormControl(null),
      });
    }

    ngAfterContentInit(){
      this.jsTreeElt = $('#actionTree');
      console.log('offre data in ngAfterContentInit', this.data)

      if (this.data) {
        console.log('offre data to edit', this.data)
        this.editMode=true
        
        this.formModal.patchValue(this.data);
        // this.formModal.controls.id.patchValue(this.data.groupeRadiusId);
        this.formModal.controls.id.patchValue(this.data.id);

        // this.title = "Modifi&&&&&&&&&&&&er un rôle";
      }
      this.getGroupeRadius();
      this.getLocalisation();
    }
  
  changeLocal(){
    this.getGroupeRadius({localisation: this.formModal.controls.localisationId})
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
  }

  getLocalisation(param?: any) {
    const methode = 'localisation/getByCriteria';
    const data = {
      
      data: {
       
      }
    };
    console.log('data', data);
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
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
    const methode = 'radgroup/getGroupeRadius';
    const data = {
      
      data: {
       localisationId: param && param.localisation ? param.localisation : ''
      }
    };
    console.log('data', data);
    this.restApiClient.executeRadiusFtth(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeGroupeRadius = res.items ? res.items: [];

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
    console.log('this.formModal.value.id',this.formModal.value.id);
    
    if(this.formModal.value.id){
       
        methode = 'radius/update';
    } else {
        methode = 'radius/create';
    }
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `${this.formModal.value.id ? 'Mise à jours' : 'Création'} de radius`,
      datas: [this.formModal.value]
  
    };
    console.log('data sent to save save radius', JSON.stringify(data));
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
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
