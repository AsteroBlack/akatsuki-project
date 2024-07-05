import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../../../shared/services/rest-api-client.service';

@Component({
  selector: 'app-modal-ip-adresse',
  templateUrl: './modal-ip-address.component.html',
  styleUrls: ['./modal-ip-address.component.scss']
})
export class ModalIPAddressComponent implements OnInit {
  displayResponse: any;
  displayResponseRight: any;
  constructor(
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    public dialogRef: MatDialogRef<ModalIPAddressComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string) { }

  ngOnInit(): void {
    this.getAdressInfos()
  }

  onClose(): void {
    this.dialogRef.close();
  }

  getAdressInfos() {
    const methode = 'ip/ipAddressInfosByNd';
    this.restApiClient.getAddressInfosByNd(methode, this.data).subscribe(
      (res: any) => {
        console.log("res", res)
        if (res && !res.hasError && res.itemsIpAddress.length>0) {
            this.displayResponse = res.itemsIpAddress[0];
            console.log(`adresse: ${this.displayResponse.address} nd: ${this.displayResponse.nd}`)
        } else {
          this.dialogRef.close()
          this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors l\'exécution de l\'opération');
        }
      },
      (err: any) => {
        this.dialogRef.close()
        this.toastService.error('Opération échouée', 'Problême de communication');
      }
    );
  }

  offloadIp(){
    const method = 'ip/freeIpWithNd'
    const data = {
      user: "1",
      datas: [
        {
          ipAddress: this.displayResponse.address,
          nd: this.displayResponse.nd
        }
      ]
    };

    this.restApiClient.executeIpManager(method, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie')
        this.onClose()
      } else {
        this.toastService.error(res.status.message)
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'problème de connexion')
    })
  }

}
