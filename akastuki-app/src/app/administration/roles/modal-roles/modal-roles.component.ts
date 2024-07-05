import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import { RolesComponent } from '../roles.component';
declare var $: any;
@Component({
  selector: 'app-modal-roles',
  templateUrl: './modal-roles.component.html',
  styleUrls: ['./modal-roles.component.scss']
})
export class ModalRolesComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  listeActions : any[] = [];
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalRolesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        code: new FormControl(null, [Validators.required]),
        libelle: new FormControl(null, [Validators.required]),
        datasAction: new FormControl(null),
        id: new FormControl(null),
      });
    }

    ngAfterContentInit(){
      this.jsTreeElt = $('#actionTree');

      if (this.data) {
        this.formModal.patchValue(this.data);
        // this.title = "Modifi&&&&&&&&&&&&er un rôle";
      }
      this.getAction();
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
  }

  getAction(param?: any) {
    const methode = 'action/getByCriteria';
    const data = {

      data: {

      }
    };
    console.log('data', data);
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      console.log('res get action', res);
      if (res && !res.hasError) {
        this.listeActions = res.items ? res.items: [];
        this.listeActions = this.listeActions.map((item:any)=>{
          return {
            id : item.id.toString(),
            parent : item.parentActionId ? item.parentActionId.toString() : "#",
            text : item.libelle,
          }
        });
        console.log('liste action', this.listeActions);
        this.jsTreeElt.jstree({
          core: {
            data:  this.listeActions,
            themes: {
              name: 'proton',
              responsive: true
            }
          },
          checkbox: {
            three_state: true,
            cascade: 'up'
          },
          plugins: ["checkbox"],
        });

        if (this.data && this.data.datasAction) {
          this.formModal.patchValue(this.data);
          this.jsTreeElt.on('ready.jstree', () => {
            console.log('jstree loading');
            this.data.datasAction.forEach((elt: any) => {
              this.jsTreeElt.jstree(true).select_node(elt.id);
            });
          });
        }
        this.jsTreeElt.on('changed.jstree', (val:any, valObject: any) => {
          console.log('val', val);
          console.log('valObject', valObject);
          this.formModal.controls.datasAction.patchValue(
                  valObject.selected.map((elt: any) => {
                    return {id: elt};
                  })
                );
        });
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
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
    let methode = '';
    if(this.formModal.value.id){
        methode = 'role/update';
    } else {
        methode = 'role/create';
    }
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `Mise à jours des ressources en disruptive`,
      datas: [this.formModal.value]

    };
    console.log('data', data);
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.dialogRef.close();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors l\'exécution de l\'opération');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }



}
