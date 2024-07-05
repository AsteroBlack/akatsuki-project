import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';

import { GestionRessourcesComponent } from '../gestion-ressources.component';

@Component({
  selector: 'app-modal-upload-ressource',
  templateUrl: './modal-upload-ressource.component.html',
  styleUrls: ['./modal-upload-ressource.component.scss']
})
export class ModalUploadRessourceComponent implements OnInit {
  formModal: FormGroup;
  nomFichier: string = '';
  listeTechno : any[] = [];
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<GestionRessourcesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        techno: new FormControl(null, [Validators.required]),
        file: new FormControl(null),
      });
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
    console.log('input data', this.data);
    this.getTechno();
    // this.listeTechno = this.data.listeTechno;
    // console.log('liste techno', this.listeTechno);
  }

  getTechno(param?: any) {
    const methode = 'paramOltTehno/getByCriteria';
    const data = {
      // user: this.userService.getUser() ? this.userService.getUser().id : '1',
      data: {}
    };
    console.log('data', data);
    this.restApiClient.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeTechno = res.items ? res.items: []
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }


  processFile(event: any, doc?: any) {
    console.log('event file', event.target.files[0]);
    const reader = new FileReader();
    const file = event.target.files[0];
    let fileData;
    reader.onload = (readerEvent) => {
      const data = (readerEvent.target as any).result;
      console.log('data file', data.split('.')[0]);
      this.nomFichier = file.name;

      this.formModal.controls.file.patchValue(file);
      this.formModal.controls.file.updateValueAndValidity();
      event.stopPropagation();
        // console.log('dataDocument', this.formRegleAlerte.value);
    };
    // console.log('dataDocument', this.uploadForm.value);
    reader.readAsDataURL(event.target.files[0]);
    event.preventDefault();
  }

  onSubmit(param: any){
    console.log('submit action', param);
    const methode = 'disruptiveDistribution/upload';
    const formData: any =  new FormData();
    // formData.append('test', 'this.uploadForm.controls.techno.value');
    formData.append('techno', this.formModal.controls.techno.value);
    formData.append('file', this.formModal.controls.file.value, this.formModal.controls.file.value.name);
    console.log('data', formData);
    this.restApiClient.executeFormDataResssources(methode, formData).subscribe((res: any) => {
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
