import { LiberationIpComponent } from "./liberation-ip.component";
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
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NgxMaskModule } from 'ngx-mask';
import { LiberationIpRoutingModule } from "./liberation-ip-routing.module";
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ModalIpLibComponent } from './modal-ip-lib/modal-ip-lib.component';
import { ModalGenerateAdresseLibComponent } from './modal-generate-adresse-lib/modal-generate-adresse-lib.component';
import { ModalSetupIpLibComponent } from './modal-setup-ip-lib/modal-setup-ip-lib.component';
import { ModalDetailPoolLibComponent } from './modal-detail-pool-lib/modal-detail-pool-lib.component';
import { ModalIpAdresseLibComponent } from './modal-ip-adresse-lib/modal-ip-adresse-lib.component';


@NgModule({
    declarations: [
      LiberationIpComponent,
      ModalGenerateAdresseLibComponent,
      ModalIpLibComponent,
      ModalSetupIpLibComponent,
      ModalDetailPoolLibComponent,
      ModalIpAdresseLibComponent
    ],
    imports: [
      CommonModule,
      LiberationIpRoutingModule,
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
    ],
    entryComponents: [
      // ModalClientComponent
      ModalGenerateAdresseLibComponent,
      ModalIpLibComponent,
      ModalSetupIpLibComponent,
      ModalDetailPoolLibComponent
      
    ],
    providers: [
      // {provide: MAT_INPUT_VALUE_ACCESSOR, useExisting: ''}
    ]
  })
  export class LiberationIpModule {}