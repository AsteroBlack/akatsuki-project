import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../../../shared/services/rest-api-client.service';
import { UserService } from '../../../shared/services/user.service';
import * as _ from 'lodash';
declare var $: any;

@Component({
  selector: 'app-modal-ip',
  templateUrl: './modal-ip.component.html',
  styleUrls: ['./modal-ip.component.scss']
})
export class ModalIpComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  nomFichier: string = '';
  modifIp: any ;
  jsTreeElt: any;
  listeClient : any[] = [];
  listeTypeClient : any[] = [];
  listeService : any[] = [];
  listeZone : any[] = [];
  isCorrectIp: boolean | undefined;
  isCorrectBeginAddr:boolean | undefined;
  isCorrectGateway: boolean | undefined;
  isCorrectEndAddr:boolean | undefined;
  isCorrectMask: boolean | undefined;
  isCorrectBroadcastAddr: boolean | undefined;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalIpComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.formModal = new FormGroup({
        id: new FormControl(null),
        idTypeClient: new FormControl(null, [Validators.required]),
        idService: new FormControl(null, [Validators.required]),
        idZone: new FormControl(null, [Validators.required]),
        ipAddress: new FormControl('', [Validators.required]),
        gateway: new FormControl('', [Validators.required]),
        beginAddr: new FormControl('', [Validators.required]),
        endAddr: new FormControl('', [Validators.required]),
        broadcastAddr: new FormControl('', [Validators.required]),
        mask: new FormControl('255.255.255.0', [Validators.required]),
      });
    }

    ngAfterContentInit(){
      this.getTypeClient();
      this.getService();
      this.getZone();
      this.validateMaskAddress();
      if (this.data) {
       /*  console.log('eeddd1',this.data); 
        console.log('eeddd',this.data[1]);  */
        this.formModal.patchValue(this.data[0]);
            /* this.formModal.patchValue({ipAddress:this.data[1].ipAddress ? this.data[1].ipAddress : ""}); */
           /* this.formModal.patchValue({id:this.data[1].id}); */
        this.formModal.patchValue(this.data[1]);
        this.modifIp = this.data[1].id
        /* this.formModal.value.address = this.data.itemsIpDtobands[0].ipAddress */
      }
      this.validateIPaddress();
      this.validateBeginAddr();
      this.validateEndAddr();
      this.validateGateway();
      this.validateBroadcastAddr();
      
    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
    
  }

  getTypeClient(param?: any) {
    const methode = 'typeClient/getByCriteria';
    const data = {
      
      data: {
       
      }
    };
    console.log('data', data);
    this.restApiClient.executeIpManager(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeTypeClient = res.items ? res.items: [];
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getZone(param?: any) {
    const methode = 'zone/getByCriteria';
    const data = {
      
      data: {
       
      }
    };
    console.log('data', data);
    this.restApiClient.executeIpManager(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeZone = res.items ? res.items: [];
        this.listeZone = _.sortBy(this.listeZone, [function(lb) { return lb.libelle; }]);

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  getService(param?: any) {
    const methode = 'service/getByCriteria';
    const data = {
      
      data: {
       
      }
    };
    console.log('data', data);
    this.restApiClient.executeIpManager(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.listeService = res.items ? res.items: [];

      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }

  validateIPaddress() {  
    console.log('typed ip',this.formModal.value.ipAddress);
    this.isCorrectIp = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(this.formModal.value.ipAddress) 

  }  
  validateGateway() {  
    console.log('typed validateGateway',this.formModal.value.gateway);
    this.isCorrectGateway = /^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(this.formModal.value.gateway) 

  } 

  validateBeginAddr() {  
    console.log('typed validateBeginAddr',this.formModal.value.beginAddr);
    this.isCorrectBeginAddr = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(this.formModal.value.beginAddr) 

  }  
  validateEndAddr() {  
    console.log('typed validateEndAddr',this.formModal.value.endAddr);
    this.isCorrectEndAddr = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(this.formModal.value.endAddr) 

  }  
  validateBroadcastAddr() {  
    console.log('typed validateBroadcastAddr',this.formModal.value.broadcastAddr);
    this.isCorrectBroadcastAddr = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(this.formModal.value.broadcastAddr) 

  }

  validateMaskAddress(){
    console.log('typed ip',this.formModal.value.mask);
    this.isCorrectMask = /^(254|252|248|240|224|192|128|0)\.0\.0\.0|255\.(254|252|248|240|224|192|128|0)\.0\.0|255\.255\.(254|252|248|240|224|192|128|0)\.0|255\.255\.255\.(255|254|252|248|240|224|192|128|0)$/.test(this.formModal.value.mask)
  }
  onSubmits(param: any){
    console.log('submit action', param.id);
      const methode = 'client/update';
     /* delete this.formModal.value.id*/
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `Uploded de IP client`,
      // dataIpAddress: this.formModal.value
      datas: [this.formModal.value]
    };
    console.log('data', data);
    console.log('data json', JSON.stringify(data));
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

  onSubmit(param: any){
    let methode
    console.log('submit action', param.id);
     if(param.id){
       methode = 'ip/update';
     } else {
       methode = 'ip/createIpBand';
    }
     /* delete this.formModal.value.id  */
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `Création de IP client`,
      // dataIpAddress: this.formModal.value
      datas: [this.formModal.value]
  
    };
    console.log('data', data);
    console.log('data json', JSON.stringify(data));
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
