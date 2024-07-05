import { Component, OnInit, Inject, AfterContentInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../../../shared/services/rest-api-client.service';
import { UserService } from '../../../shared/services/user.service';
declare var $: any;

@Component({
  selector: 'app-modal-generate-adresse-lib',
  templateUrl: './modal-generate-adresse-lib.component.html',
  styleUrls: ['./modal-generate-adresse-lib.component.scss']
})
export class ModalGenerateAdresseLibComponent implements OnInit {

  formModal: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  listeClient : any[] = [];
  listeTypeClient : any[] = [];
  listeService : any[] = [];
  listeZone : any[] = [];
  displayResponse: any;
  displayResponseRight: any;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalGenerateAdresseLibComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        // id: new FormControl(null),
        idClient: new FormControl(null, [Validators.required]),
        nbreAddr: new FormControl({value: 1, disabled: true}, [Validators.required, Validators.min(0)]),
        nd: new FormControl(null, [Validators.required, Validators.min(8)]),
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


  onSubmit(form: any){
    const param = form.value
    param.nbreAddr = form.get('nbreAddr').value
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
        this.displayResponse  = res.ipAddressBand;
        this.displayResponseRight = res.ipAddressBand;
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.formModal.reset();
        // this.dialogRef.close();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors l\'exécution de l\'opération');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

}
