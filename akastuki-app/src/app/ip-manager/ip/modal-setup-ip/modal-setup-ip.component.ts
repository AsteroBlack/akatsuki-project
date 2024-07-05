import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../../../shared/services/rest-api-client.service';
import { UserService } from '../../../shared/services/user.service';
declare var $: any;

@Component({
  selector: 'app-modal-setup-ip',
  templateUrl: './modal-setup-ip.component.html',
  styleUrls: ['./modal-setup-ip.component.scss']
})
export class ModalSetupIpComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  listeClient : any[] = [];
  listeTypeClient : any[] = [];
  listeService : any[] = [];
  listeZone : any[] = [];
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalSetupIpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        // id: new FormControl(null),
        idClient: new FormControl(null, [Validators.required]),
        address: new FormControl(null, [Validators.required]),
        mask: new FormControl(null, [Validators.required, Validators.min(8)]),
      });
    }

    ngAfterContentInit(){

      if (this.data) {
        this.formModal.patchValue(this.data);
        this.formModal.controls.idClient.patchValue(this.data.id);
        // this.title = "Modifi&&&&&&&&&&&&er un rôle";
        console.log('form', this.formModal.value);
      }
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
  }


  onSubmit(param: any){
    console.log('submit action', param);
    const methode = 'ip/generate';

    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `Générer un nombre d'adresse`,
      datas: [this.formModal.value]
      // datas: [this.formModal.value]
  
    };
    console.log('data', data);
    // return;
    this.restApiClient.executeIpManager(methode, data).subscribe((res: any) => {
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
