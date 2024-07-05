import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IpRoutingModule } from './ip-routing.module';
import { IpComponent } from './ip.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule, MAT_INPUT_VALUE_ACCESSOR } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { SharedModule } from '../../shared/shared.module';
import {MatExpansionModule} from '@angular/material/expansion';
// import { ModalClientComponent } from '../client/modal-client/modal-client.component';
import { ModalIpComponent } from './modal-ip/modal-ip.component';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ModalGenerateAdresseComponent } from './modal-generate-adresse/modal-generate-adresse.component';
import { ModalSetupIpComponent } from './modal-setup-ip/modal-setup-ip.component';
import { ModalDetailPoolComponent } from './modal-detail-pool/modal-detail-pool.component';
import { NgxMaskModule } from 'ngx-mask';
import { ModalIPAddressComponent } from './modal-ip-adresse/modal-ip-address.component';
import { ModalIpBlocageComponent } from './modal-ip-blocage/modal-ip-blocage.component';
import { MatCheckboxModule } from '@angular/material/checkbox';

@NgModule({
  declarations: [
    IpComponent,
    ModalIpComponent,
    ModalGenerateAdresseComponent,
    ModalSetupIpComponent,
    ModalDetailPoolComponent,
    ModalIPAddressComponent,
    ModalIpBlocageComponent,
  ],
  imports: [
    CommonModule,
    IpRoutingModule,
    MatTableModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FlexLayoutModule,
    // SharedModule,
    MatDialogModule,
    ReactiveFormsModule,
    FormsModule,
    MatFileUploadModule,
    MatSelectModule,
    MatPaginatorModule,
    MatExpansionModule,
    ReactiveFormsModule,
    FormsModule,
    MatToolbarModule,
    MatListModule,
    NgxMaskModule.forRoot(),
    MatCheckboxModule
  ],
  entryComponents: [
    // ModalClientComponent
    ModalGenerateAdresseComponent,
    ModalIpComponent,
    ModalSetupIpComponent,
    ModalDetailPoolComponent
  ],
  providers: [
    // {provide: MAT_INPUT_VALUE_ACCESSOR, useExisting: ''}
  ]
})
export class IpModule { }
