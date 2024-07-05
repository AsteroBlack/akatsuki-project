import { AfterContentInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { keyBy } from 'lodash';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { RestApiClientService } from '../../../shared/services/rest-api-client.service';
import { UserService } from '../../../shared/services/user.service';
// import { RolesComponent } from '../roles.component';
import * as moment from 'moment';
import { ProvisionningComponent } from '../../provisionning/provisionning.component';
declare var $: any;

export interface Parametre{
  id?: number,
  key: string,
  value: string,
  type: string
}

@Component({
  selector: 'app-modal-groupe-radius',
  templateUrl: './modal-groupe-radius.component.html',
  styleUrls: ['./modal-groupe-radius.component.scss'],
  // providers: [MatDialogRef]
})
export class ModalGroupeRadiusComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  groupeParametrage: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  // listetypeOffre : any[] = [];
  listeAttributs : any[] = [];
  seuilExist: any;
  indexRowSeuil: any;
  parameterArrayColumn = [
    'num',
    'key',
    'values',
    'type',
    'options',
    // 'datasRole'
  ];
  listParamters: Parametre[]=[]
  editMode: boolean =false;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalGroupeRadiusComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.groupeParametrage = new FormGroup({
        key: new FormControl(null, [Validators.required]),
        type: new FormControl(null, [Validators.required]),
        value: new FormControl(null, [Validators.required]),
      });
      this.formModal = new FormGroup({
        id: new FormControl(null),
        groupename: new FormControl(null, [Validators.required]),
        attribut: new FormControl(''),
        datasParameter: new FormArray([]),
      });
    }

    ngAfterContentInit(){
      // this.jsTreeElt = $('#actionTree');

      
      if(this.data) {
        this.editMode=true
        console.log('data to edit', this.data);
        this.formModal.controls.id.patchValue(this.data.id);
        this.formModal.controls.groupename.patchValue(this.data.key);
        this.listParamters = this.data.value.map((elt: any)=> {return {key: elt.attribute, type: elt.type, value: elt.value}});
        console.log('params', this.listParamters);
      }
      this.getAttribut();
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
  }

  update(param: Parametre){

  }

  removeParams(index: number){
    this.listParamters.splice(index, 1);
    this.formModal.controls.datasParameter.patchValue(this.listParamters);
  }

  changeType(){
    this.getTypeAttribut({code: this.formModal.controls.type.value});
  }

  getTypeAttribut(param?: any) {
    const methode = 'typeAttribut/getByCriteria';
    const data = {
      data: {
        code: param.code
      }
    };
    console.log('data sent to getTypeAttribut', data);
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      console.log('res type attribut', res);
      if (res && !res.hasError) {
        this.listParamters = res.items ? res.items: [];

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  addParamters(){
    const paramsObject = {
      id:  this.formModal.controls.attribut.value.id,
      key: this.formModal.controls.attribut.value.attribut,
      value: this.formModal.controls.attribut.value.value,
      type: this.formModal.controls.attribut.value.type,
    } 
    console.log('paramsObject', paramsObject);
    if(this.indexRowSeuil){
      // this.formModal.controls.threshold.patchValue(seuilObject)
      if(paramsObject.key === 'Expiration'){
        this.listParamters[this.indexRowSeuil]= {
          ...paramsObject, 
          value: moment(new Date(this.listeAttributs.filter((elt)=>elt.attribut === paramsObject.key)[0].value)).format('yyyy-MM-DD')
        };
      } else{
        this.listParamters[this.indexRowSeuil]= paramsObject;
      }
        this.formModal.controls.parameters.patchValue(this.listParamters);
        this.seuilExist = false;
        this.indexRowSeuil =  null;
    } else {
      if(this.listParamters.filter((item: any)=>item.key === paramsObject.key).length == 0){
        if(paramsObject.key === 'Expiration'){
          this.listParamters.push({
            ...paramsObject, 
            value: moment(new Date(this.listeAttributs.filter((elt)=>elt.attribut === paramsObject.key)[0].value)).format('yyyy-MM-DD')
          });
        } else{
          this.listParamters.push(paramsObject);
        }
        this.formModal.controls.datasParameter.patchValue(this.listParamters);
        this.seuilExist = false;
        console.log('empty liste', this.formModal);
      } else {
        console.log('check liste', this.formModal);
        this.seuilExist = true;
        return;
      }
      console.log('listParamters', this.listParamters);
    }
   
    this.formModal.controls.attribut.reset();
  }


  updateParam(item: Parametre, index: number){
    this.indexRowSeuil = index;
    console.log('update row', item);
    this.formModal.controls.key.patchValue(item.key);
    this.formModal.controls.value.patchValue(item.value);
    this.formModal.controls.type.patchValue(item.type);
  }

  askBeforeDelete(item: any) {
    Swal.fire({
      title: '<div style="color:#fff">Attention!</div>',
      html: '<div style="color:#fff">Vous êtes sur le point de supprimer cet element. Continuer?</div>',
      showCancelButton: true,
      confirmButtonColor: '#dc3545',
      confirmButtonText: `Supprimer`,
      cancelButtonText: `Annuler`,
      background: '#000',
      
    }).then((result: any) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        // Swal.fire('Saved!', '', 'success')
        this.removeParams(item);
      } else if (result.isDenied) {
        // Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

  changeAttribute(param:any, index: number){
    // console.log('param', moment(new Date(this.listeAttributs.filter((elt)=>elt.attribut === param.key)[0].value)).format('yyyy-MM-dd'));
    if(param.key === 'Expiration'){
      const object = this.listeAttributs.filter((elt)=>elt.attribut === param.key)[0];
      this.listParamters[index] = {...object, value: moment(new Date(this.listeAttributs.filter((elt)=>elt.attribut === param.key)[0].value)).format('yyyy-MM-DD')};
      // console.log('liste param', this.listParamters);
    } else {
      const object = this.listeAttributs.filter((elt)=>elt.attribut === param.key)[0];
      this.listParamters[index] = object;
    }
  }

  getAttribut(param?: any) {
    const methode = 'attribut/getByCriteria';
    const data = {
      
      data: {
       
      }
    };
    console.log('data', data);
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeAttributs = res.items ? res.items.map((elt: any)=>{ return{...elt, key: elt.attribut}}) : [];
        console.log('liste attribut', this.listeAttributs);
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  checkDateExpiration(item: any){
    // console.log('check', item);
    if(item.key === 'Expiration'){
      // console.log('expiration', item.key);
      if(item.value){
        // console.log('convert date', new Date(item.value));
        return moment(new Date(item.value)).format('DD/MM/YYYY');
      } else {
        // console.log('no convert date');
        return item.value;
      }
    } else {
      return item.value;
    }
  }

  onSubmit(param: any){
    console.log('submit action', param);
    let methode = '';
    if(this.editMode){
        methode = 'radgroup/updateGroupe';
    } else {
        methode = 'radgroup/saveGroupe';
    }
    // this
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `${this.formModal.value.id ? 'Mise à jours' : 'Save'} de provisionning`,
      dataProvisioning: {
        groupename: this.formModal.value.groupename,
        id:this.data?this.data.id:undefined,
        datasParameter: this.listParamters.map((elt: any)=> {return{...elt, value: this.checkDateExpiration(elt)}})
      }
  
    };
    console.log('data sent to save groupe radius', JSON.stringify(data));
    this.restApiClient.executeSystem(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.listParamters = [];
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
