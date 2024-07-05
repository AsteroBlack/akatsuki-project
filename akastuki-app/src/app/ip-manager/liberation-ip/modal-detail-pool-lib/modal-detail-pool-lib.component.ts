import { Component, OnInit, AfterContentInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../../../shared/services/rest-api-client.service';
import { UserService } from '../../../shared/services/user.service';
declare var $: any;

@Component({
  selector: 'app-modal-detail-pool-lib',
  templateUrl: './modal-detail-pool-lib.component.html',
  styleUrls: ['./modal-detail-pool-lib.component.scss']
})
export class ModalDetailPoolLibComponent implements OnInit {

  pagePool: any = {
    index: 0,
    size: 10,
    total: 0
  };
  pageUniq: any = {
    index: 0,
    size: 10,
    total: 0
  };
  nomFichier: string = '';
  jsTreeElt: any;
  listeClient : any[] = [];
  listeTypeClient : any[] = [];
  listeService : any[] = [];
  listeZone : any[] = [];
  itemsIpAddrPoolDto : any[] = [];
  itemsIpAddrUniqDto : any[] = [];
  dataAddressInIpBand : any;
  ipPoolArrayColumn = [
    'num',
    'network',
    'broadcast',
    'user',
    'options',
  ];
  ipUnipArrayColumn = [
    'num',
    'network',
    'mask',
    'user',
    // 'options',
    // 'datasRole'
  ];
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    public dialogRef: MatDialogRef<ModalDetailPoolLibComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      // this.formModal = new FormGroup({
      //   id: new FormControl(null),
      //   idTypeClient: new FormControl(null, [Validators.required]),
      //   idService: new FormControl(null, [Validators.required]),
      //   idZone: new FormControl(null, [Validators.required]),
      //   address: new FormControl('', [Validators.required]),
      //   mask: new FormControl('255.255.255.0', [Validators.required]),
      // });
    }

    ngAfterContentInit(){

      if (this.data) {
        console.log('input data', this.data);
        this.checkAnIpBandUsage(this.data);
        // this.formModal.patchValue(this.data);
        // this.title = "Modifi&&&&&&&&&&&&er un rôle";
      }

    }

  onNoClick(): void {
    this.dialogRef.close();
  }
  ngOnInit(): void {
  }

  pollPaginationChange(event: PageEvent){
    // console.log('page', event);
    // this.page.index = event.pageIndex;
    // this.page.size = event.pageSize;
    // this.getIp();
    const endItem = (event.pageIndex + 1) * this.pagePool.size;
    const startItem = endItem - this.pagePool.size + 1;
    // console.log('size change', endItem);
    this.itemsIpAddrPoolDto = this.dataAddressInIpBand.itemsIpAddrPoolDto.slice((startItem-1), endItem);
  }

  uniqPaginationChange(event: PageEvent){
    console.log('page', event);
    // this.page.index = event.pageIndex;
    // this.page.size = event.pageSize;
    // this.getIp();
    const endItem = (event.pageIndex + 1) * this.pageUniq.size;
    const startItem = endItem - this.pageUniq.size + 1;
    console.log('page interval', startItem, endItem);
    this.itemsIpAddrUniqDto = this.dataAddressInIpBand.itemsIpAddrUniqDto.slice((startItem-1), endItem);
  }

  checkAnIpBandUsage(param?: any) {
    const methode = 'ip/checkAnIpBandUsage';
    const data = {

      data: {
          id: param.id,
          ipAddress: param.ipAddress,
          mask: param.mask
      }
    };
    console.log('data', data);
    this.restApiClient.executeIpManager(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.dataAddressInIpBand = res.usedAddressInIpBand && res.usedAddressInIpBand;
        // this.itemsIpAddrUniqDto = this.dataAddressInIpBand.itemsIpAddrUniqDto;
        // this.itemsIpAddrPoolDto = this.dataAddressInIpBand.itemsIpAddrPoolDto;

        /* Pagination de l'accordeon (Adresses de réseau utilisées) */
        // this.datasAllIp = allClient;
        this.itemsIpAddrUniqDto = this.dataAddressInIpBand ? this.dataAddressInIpBand.itemsIpAddrUniqDto.slice((this.pageUniq.index) * this.pageUniq.size,
         (this.pageUniq.index) * this.pageUniq.size + this.pageUniq.size) : [];
        console.log('listes ip', this.itemsIpAddrUniqDto);
        this.pageUniq.total = this.dataAddressInIpBand.itemsIpAddrUniqDto.length;

        /* Pagination de l'accordeon (Adresse IP utilisées) */
        this.itemsIpAddrPoolDto = this.dataAddressInIpBand ? this.dataAddressInIpBand.itemsIpAddrPoolDto.slice((this.pagePool.index) * this.pagePool.size,
         (this.pagePool.index) * this.pagePool.size + this.pagePool.size) : [];
        console.log('liste ips', this.itemsIpAddrPoolDto);
        this.pagePool.total = this.dataAddressInIpBand.itemsIpAddrPoolDto.length;
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

}
