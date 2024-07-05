import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../../../shared/services/rest-api-client.service';
import { UserService } from '../../../shared/services/user.service';
// import { RolesComponent } from '../roles.component';
import Swal from 'sweetalert2';
import * as moment from 'moment';
import { CommunicationService } from 'src/app/shared/services/communication.service';
declare var $: any;

@Component({
  selector: 'app-modal-rallogement',
  templateUrl: './modal-rallogement.component.html',
  styleUrls: ['./modal-rallogement.component.scss']
})
export class ModalRallogementComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  formGroup: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  listeZone : any[] = [];
  listeLocalisation : any[] = [];
  datepicker: FormGroup;
  minDate = moment().add(1, 'days').toDate()

  constructor(
    private formBuilder: FormBuilder,
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    private communicationService:CommunicationService,
    public dialogRef: MatDialogRef<ModalRallogementComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        date: new FormControl(null, [Validators.required]),
        username: new FormControl(null, [Validators.required]),
      });

    console.log('min date',this.minDate);
    let splitDateExpiration = this.data.dateExpiration.split('/')
    console.log('splitDateExpiration',splitDateExpiration)
    let dateExpiration = splitDateExpiration[1]+'/'+splitDateExpiration[0]+'/'+splitDateExpiration[2]
    console.log('date expiration',new Date(dateExpiration));
    let realDateExpiration = new Date(dateExpiration)
    console.log('moment(realDateExpiration).isAfter(this.minDate)',moment(realDateExpiration).isBefore(this.minDate))
    //this.minDate = moment(realDateExpiration).isAfter(this.minDate)?realDateExpiration: this.minDate

  }

    ngAfterContentInit(){
      // this.jsTreeElt = $('#actionTree');
      console.log("data", this.data);
      if (this.data) {
        this.formModal.patchValue(this.data);
        this.formModal.controls.username.patchValue(this.data.username);
        // this.title = "Modifi&&&&&&&&&&&&er un rôle";
        console.log("formModal", this.formModal.value);
      }

      // this.getLocalisation();
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
    this.datepicker = this.formBuilder.group({
      dateJoined: ''
    });

  }

  updateDateExpiration(param?: any) {
    console.log('date', moment(this.formModal.controls.date.value).format('DD/MM/YYYY'))
    const methode = 'provisioning/activate';
    const data = {

      datasProvisioning: [
        {
            username: param.username,
            type: this.data.type,
            datasParameter: [
                {
                    key: "Expiration",
                    value: moment(this.formModal.controls.date.value).format('DD/MM/YYYY'),
                    type: 'check'
                }
            ]
        }
      ]
    };
    console.log('data to rallonge', JSON.stringify(data));
    // return;
    this.restApiClient.executeSystem(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        Swal.update({
          // type: 'success',
          html: 'You entered: <strong></strong>'
        });
        this.communicationService.sendData(param.username)
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
