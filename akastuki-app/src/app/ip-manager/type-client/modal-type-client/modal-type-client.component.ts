import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { TypeClientService } from 'src/app/core/services/type-client.service';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
// import { RolesComponent } from '../roles.component';
declare var $: any;
@Component({
  selector: 'app-modal-type-client',
  templateUrl: './modal-type-client.component.html',
  styleUrls: ['./modal-type-client.component.scss']
})
export class ModalTypeClientComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  listeLocalisation : any[] = [];
  constructor(
    private typeClientService: TypeClientService,
    private toastService: ToastrService,
    public dialogRef: MatDialogRef<ModalTypeClientComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        id: new FormControl(null),
        code: new FormControl(null, [Validators.required]),
        libelle: new FormControl(null, [Validators.required]),
        // datasAction: new FormControl(null),
      });
    }

    ngAfterContentInit(){

      if (this.data) {
        this.formModal.patchValue(this.data);
        // this.title = "Modifi&&&&&&&&&&&&er un rôle";
      }

    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
  }


  onSubmit(param: any){
    console.log('submit action', param);
    let action: 'update' | 'create'= 'create';
    if(this.formModal.value.id){
      action = 'update';
    }
    this.typeClientService.edit(action, this.formModal.value).subscribe((res: any) => {
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
