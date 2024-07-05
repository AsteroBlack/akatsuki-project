import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { CommunicationService } from 'src/app/shared/services/communication.service';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import { ModalRallogementComponent } from '../modal-rallogement/modal-rallogement.component';

@Component({
  selector: 'app-detail-accounting',
  templateUrl: './detail-accounting.component.html',
  styleUrls: ['./detail-accounting.component.scss']
})
export class DetailAccountingComponent implements OnInit {
  objectKeys = Object.keys;
  constructor(
    private formBuilder: FormBuilder,
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
    private communicationService:CommunicationService,
    public dialogRef: MatDialogRef<ModalRallogementComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
