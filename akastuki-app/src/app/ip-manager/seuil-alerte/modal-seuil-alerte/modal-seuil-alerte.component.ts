import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../../../shared/services/rest-api-client.service';
import { UserService } from '../../../shared/services/user.service';
// import { RolesComponent } from '../roles.component';
import Swal from 'sweetalert2';
import * as moment from 'moment';
declare var $: any;

@Component({
  selector: 'app-modal-seuil-alerte',
  templateUrl: './modal-seuil-alerte.component.html',
  styleUrls: ['./modal-seuil-alerte.component.scss']
})
export class ModalSeuilAlerteComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  listeZone : any[] = [];
  listeLocalisation : any[] = [];
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalSeuilAlerteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        id: new FormControl(null),
        // key: new FormControl('Expiration'),
        // type: new FormControl(null, [Validators.required]),
        seuilAlerte: new FormControl(null, [Validators.required, Validators.max(100), Validators.min(0)]),
      });
    }

    ngAfterContentInit(){
      // this.jsTreeElt = $('#actionTree');
      console.log("data", this.data);
      if (this.data) {
        this.formModal.patchValue(this.data);
        console.log("formModal", this.formModal.value);
      }

      // this.getLocalisation();
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
  }

  onSave(param?: any) {
    let methode = 'parametrage/create';
    if(this.formModal.value.id){
      methode = 'parametrage/update';
  } else {
      methode = 'parametrage/create';
  }
    const data = {
      
      datas: [this.formModal.value]
    };
    console.log('data', data);
    // return;
    this.restApiClient.executeIpManager(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.formModal.reset();
        this.dialogRef.close();
        // this.getProvisionning({username: param.username});
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

}
