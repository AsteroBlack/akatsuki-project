import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import { ActionsComponent } from '../actions.component';
declare var $: any;
@Component({
  selector: 'app-modal-actions',
  templateUrl: './modal-actions.component.html',
  styleUrls: ['./modal-actions.component.scss']
})
export class ModalActionsComponent implements OnInit {
  formModal: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  listeActions : any[] = [];
  listeMenus : any[] = [];
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalActionsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        code: new FormControl(null, [Validators.required]),
        libelle: new FormControl(null, [Validators.required]),
        menusId: new FormControl(null, [Validators.required]),
        parentActionId: new FormControl(null),
        id:new FormControl(null),
        // datasAction: new FormControl(null),
      });
    }

    ngAfterContentInit(){
      if (this.data) {
        console.log('input data', this.data);
        this.formModal.patchValue(this.data);
        console.log('formModal', this.formModal.value)
        // this.title = "Modifi&&&&&&&&&&&&er un rôle";
      }
    
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
    this.getMenus();
    this.getActions();
  }

  getActions(param?: any) {
    const methode = 'action/getByCriteria';
    const data = {
      
      data: {
       
      }
    };
    console.log('data', data);
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeActions = res.items ? res.items: [];
        this.listeActions = this.listeActions.filter(
          ls=>!ls.parentActionId && ls.libelle !='Create' &&  ls.libelle !='Delete' && ls.libelle !='Update' && ls.libelle !='Read'
        )

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getMenus(param?: any) {
    const methode = 'menus/getByCriteria';
    const data = {
      
      data: {
       
      }
    };
    console.log('data', data);
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeMenus = res.items ? res.items: [];

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
    if(this.formModal.value.id){
        methode = 'action/update';
    } else {
        methode = 'action/create';
    }
    const data = {
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `${this.formModal.value.id ? 'Mise à jours' : 'Création'} de ressources en actions`,
      datas: [this.formModal.value]
  
    };
    console.log('data', JSON.stringify(data));
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getActions();
        this.formModal.reset();
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
