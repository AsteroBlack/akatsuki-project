import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { RestApiClientService } from '../../../shared/services/rest-api-client.service';
import { UserService } from '../../../shared/services/user.service';
import * as moment from 'moment';
import { CommunicationService } from 'src/app/shared/services/communication.service';
// import { RolesComponent } from '../roles.component';
declare var $: any;
@Component({
  selector: 'app-modal-provisionning',
  templateUrl: './modal-provisionning.component.html',
  styleUrls: ['./modal-provisionning.component.scss']
})
export class ModalProvisionningComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  nomFichier: string = '';
  jsTreeElt: any;
  seuilExist: any;
  indexRowSeuil: any;
  listParamters: any = [];
  listeZone : any[] = [];
  listeModele : any[] = [];
  listeAttributs : any[] = [];
  listeGroupeRadius : any[] = [];
  listeTypeAttribut : any[] = [];
  patternCustom = `^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$`;
  editMode= false;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    private communicationService:CommunicationService,
    public dialogRef: MatDialogRef<ModalProvisionningComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        id: new FormControl(null),
        username: new FormControl(null, [Validators.required]),
        type: new FormControl(null, [Validators.required]),
        groupename: new FormControl(null, [Validators.required]),
        attribut: new FormControl(''),
        mac: new FormControl(''),
        coeff: new FormControl(''),
        datasParameter: new FormArray([]),
      });
      this.formModal.controls.username.disable();
    }

    ngAfterContentInit(){
      // this.jsTreeElt = $('#actionTree');
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    if (this.data) {
      this.data.groupename = this.data.dataGroup.value
      // this.data.type = 'postpaid'
      console.log("data info", this.data);
      if(this.data.type && this.data.username){
        this.editMode = true
      }

      this.formModal.patchValue(this.data);
      // this.title = "Modifi&&&&&&&&&&&&er un rôle";
      console.log("formModal", this.formModal.value);
      if(this.data.datasCheck.value && this.data.datasCheck.value.length){
        this.data.datasCheck.value.map(
          (dc:any)=>{
            dc.type = 'check'
          }
        )
      }
      if(this.data.datasReply.value && this.data.datasReply.value.length){
        this.data.datasReply.value.map(
          (dc:any)=>{
            dc.type = 'reply'
          }
        )
      }
      this.listParamters = this.data.datasCheck.value.concat(this.data.datasReply.value)
      if(this.listParamters && this.listParamters.length){
        this.listParamters.map(
          (lp:any)=>{
            lp.attribut = lp.attribute

            if(this.data.type == 'prepaid' && lp.attribute=='Expiration'){
              // lp.value = moment(new Date(lp.value)).format('DD/MM/YYYY')
              lp.valueOld= moment(new Date(lp.value)).format("DD/MM/YYYY")
              lp.value = new Date(lp.value)
            }
            
          }
           
        )
      console.log('listParamters',this.listParamters);
      }


      // moment(item.annSouscriptionCarte, 'DD/MM/YYYY').toDate()
      // this.formModal.controls.coeff.value 

      // console.log('this.formModal.value.groupename',this.formModal.value.groupename);
      

    }
    this.getAttribut();
    this.getModele();
    this.getGroupeRadius();
  }

  removeParams(index: number){
    this.listParamters.splice(index, 1);
    this.formModal.controls.datasParameter.patchValue(this.listParamters);
  }

  changeType(){
    this.getTypeAttribut({code: this.formModal.controls.type.value});
  }

  getTypeAttribut(param?: any) {
    console.log('param sent',param);
    
    const methode = 'attribut/getFormulaire';
    const data = {
      
      data: {
        code: param.code
      }
    };
    console.log('data getTypeAttribut', JSON.stringify(data));
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      console.log('res type attribut', res);
      if (res && !res.hasError) {
        this.listParamters = res.items ? res.items: [];
        if(this.listParamters && this.listParamters.length){
          this.listParamters.map(
            (lp:any)=>{
              // lp.attribut = lp.attribute
  
              if(data.data.code == 'prepaid' && lp.attribut=='Expiration'){
                // lp.value = moment(new Date(lp.value)).format('DD/MM/YYYY')
                // lp.valueOld= moment(new Date(lp.value)).format("DD/MM/YYYY")
                console.log('date to convert',lp.value);
                
                console.log('date converted',moment(lp.value,"DD/MM/YYYY").toDate());
                
                // lp.value = new Date(lp.value)

                lp.valueOld= lp.value
                lp.value = moment(lp.value,"DD/MM/YYYY").toDate()

                
              }
              
            }
             
          )
        console.log('listParamters',this.listParamters);
        }

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
      // console.log('filter liste', this.listParamters.filter((item: any)=>item.key === paramsObject.key));
      // this.formModal.controls.parameters.patchValue(seuilObject)
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

  checkGetAddress(){
    console.log('change action');
    if(this.formModal.controls.mac.value !== '' && this.formModal.controls.coeff.value !== ''){
      console.log('check');
      this.getMacAdresse();
    }
  }

  getMacAdresse(param?: any) {
    const methode = 'radius/getMacAdresse';
    const data = {
      
      data: {
       mac: this.formModal.controls.mac.value,
       coeff: this.formModal.controls.coeff.value,
      }
    };
    console.log('data', data);
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.formModal.controls.username.patchValue(res.items ? res.items[0].mac: '');
        // this.listeAttributs = res.items ? res.items: [];

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }
  
  getGroupeRadius(param?: any) {
    const methode = 'radgroup/getAttributByGroupename';
    const data = {
      serviceLibelle: `Consultation de la liste des provisionning`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data pro', data);
    this.restApiClient.getSystem(methode).subscribe((res: any) => {
      console.log('res get', res);
      if (res && !res.hasError) {
        this.listeGroupeRadius = res && res.items ? res.items : [];
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les Provisionning');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
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
        this.listeAttributs = res.items ? res.items.map((elt: any)=>{ return{...elt, key: elt.attribut}}): [];
        console.log('liste attribut', this.listeAttributs);
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getModele(param?: any) {
    // const methode = 'costomer-back-end-api-1.0/paramEtat/getByCriteria';
    // const methode = 'api-customer-care-fixe/getEtatCriteria';
    const methode = 'modele/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des modeles`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApiClient.execute(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeModele = res && res.items ? res.items : [];
        console.log('list model',this.listeModele);
        
        // this.formModal.controls.coeff.patchValue(this.listeModele.length>0 && this.listeModele[0].coefficientModele.coefMac);
        // this.activedId = 'panel-3';
        console.log('getModele',this.listeModele.length>0 && this.listeModele[0].coefficientModele);
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les Modeles');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
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

  changeAttribute(param:any, index: number){
    console.log('param', moment(new Date(this.listeAttributs.filter((elt)=>elt.attribut === param.key)[0].value)).format('yyyy-MM-DD'));
    if(param.key === 'Expiration'){
      const object = this.listeAttributs.filter((elt)=>elt.attribut === param.key)[0];
      this.listParamters[index] = {...object, value: moment(new Date(this.listeAttributs.filter((elt)=>elt.attribut === param.key)[0].value)).format('yyyy-MM-DD')};
      // console.log('liste param', this.listParamters);
    } else {
      const object = this.listeAttributs.filter((elt)=>elt.attribut === param.key)[0];
      this.listParamters[index] = object;
    }
  }

  onSubmit(param: any){
    // console.log('submit action', this.formModal.getRawValue());
    console.log('listeParams', this.listParamters.map((elt: any)=> {return{...elt, value: this.checkDateExpiration(elt)}}));
    let methode = '';
    if(this.editMode){
        methode = 'provisioning/updateInfosBackoffice';
    }
    else{
      methode = 'provisioning/provisioningBo';

    }
    //  else {
    //     methode = 'provisioning/provisioningBo';
    // }
    const parameters = this.listParamters.map((elt: any)=> {return{
      key: elt.attribut, 
      type: elt.type, 
      value: elt.value
      }
    })
    parameters.map(
      (para:any)=>{
        if(para.key ==='Expiration' && para.value){
          para.value = moment(new Date(para.value)).format('DD/MM/YYYY')
        }
      }
    )
    
    const data = {
      ndCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `${this.formModal.value.id ? 'Mise à jours' : 'Création'} de provisioning`,
      datasProvisioning: [{
        groupename: this.formModal.value.groupename,
        username: this.formModal.getRawValue().username,
        type: this.formModal.value.type,
        datasParameter: parameters
      }]
  
    };
    console.log('parameters transform',parameters);

    console.log('data sent to server', JSON.stringify(data));
    // return;
    this.restApiClient.executeSystem(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.communicationService.sendData({mac:this.data.mac,nd:this.data.nd})
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
