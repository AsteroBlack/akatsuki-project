import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UtilisateursComponent } from '../utilisateurs.component';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/shared/services/user.service';
import { CustomValidators } from 'ng2-validation';

@Component({
  selector: 'app-modal-utilisateurs',
  templateUrl: './modal-utilisateurs.component.html',
  styleUrls: ['./modal-utilisateurs.component.scss']
})
export class ModalUtilisateursComponent implements OnInit {
  formModal: FormGroup;
  listeOlt: any = [];
  listeRfo: any = [];
  listeRoles: any = [];
  isLoading: boolean = false;
  password: FormControl;
  // @Input() param: any;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<UtilisateursComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.password = new FormControl('', [Validators.required])
      this.formModal = new FormGroup({
        id: new FormControl(null),
        nom: new FormControl(null, [Validators.required]),
        prenom: new FormControl(null, [Validators.required]),
        matricule: new FormControl(null, [Validators.required]),
        login: new FormControl(null, [Validators.required]),
        password: this.password,
        confirmPassword: new FormControl('', CustomValidators.equalTo(this.password)),
        contact: new FormControl(null, [Validators.required]),
        email: new FormControl(null, [Validators.required, Validators.email]),
        // datasRole: new FormControl([], [Validators.required]),
        roleId: new FormControl([], [Validators.required]),
        status: new FormControl(null, [Validators.required]),
      });
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
    console.log('param', this.data)
    if(this.data){
      this.formModal.patchValue(this.data);
      if(this.data.datasRole && this.data.datasRole.length){
        // this.formModal.controls.datasRole.patchValue(this.data.datasRole.map((elt:any)=>elt.id));
        this.formModal.controls.roleId.patchValue(this.data.datasRole[0].id);

      }
      console.log('form user', this.formModal.value);
    }
    // this.getParamPa();
    this.getRole();
  }

  tiroirChange(event:any){
    console.log('event change', event);
  }

  getErrorMessage() {
    if (this.formModal.controls.email.hasError('required')) {
      return 'veuillez saisir votre mail';
    }

    return this.formModal.controls.email.hasError('email') ? 'Email invalide' : '';
  }

  getRole() {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    const methode = 'role/getByCriteria';
    const data = {
      serviceLibelle: `Liste des roles`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
        // libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.execute(methode, data).subscribe((res) => {
      console.log('res roles', res);
      if (res && !res.hasError) {
        this.listeRoles = res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'impossible de recupérer les rôles');
      }
    }, (err) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  onSubmit(param: any) {
    this.isLoading = true;
    // if(this)
    const methode = this.formModal.value.id ? 'user/update' : 'user/create';
    console.log('form value',this.formModal.value);
    
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `Mise à jours des ressources en disruptive`,
      // datas: [{...this.formModal.value, datasRole: this.formModal.value.datasRole.map((elt:any)=> {return {id:elt}})}]
      datas: [{...this.formModal.value, datasRole: [{id:this.formModal.value.roleId}]}]

  
    };
    console.log('data', JSON.stringify(data));
    // console.log('datasRole', this.formModal.value.datasRole.map((elt:any)=> {return {id:elt}}))
    // return;
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
        console.log('res update', res);
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
