import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PersonneNotifRoutingModule } from './personne-notif-routing.module';
import { PersonneNotifComponent } from './personne-notif.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { SharedModule } from 'src/app/shared/shared.module';
import { ModalPersonneNotifComponent } from './modal-personne-notif/modal-personne-notif.component';
import { NgxMaskModule } from 'ngx-mask';


@NgModule({
  declarations: [
    PersonneNotifComponent,
    ModalPersonneNotifComponent
  ],
  imports: [
    CommonModule,
    PersonneNotifRoutingModule,
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
    NgxMaskModule.forRoot(),
  ],
  entryComponents: [
    ModalPersonneNotifComponent
  ]
})
export class PersonneNotifModule { }
