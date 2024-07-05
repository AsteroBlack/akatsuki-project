import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SeuilAlerteRoutingModule } from './seuil-alerte-routing.module';
import { SeuilAlerteComponent } from './seuil-alerte.component';
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
import { ModalSeuilAlerteComponent } from './modal-seuil-alerte/modal-seuil-alerte.component';


@NgModule({
  declarations: [
    SeuilAlerteComponent,
    ModalSeuilAlerteComponent
  ],
  imports: [
    CommonModule,
    SeuilAlerteRoutingModule,
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
    MatPaginatorModule
  ],
  entryComponents: [
    ModalSeuilAlerteComponent
  ]
})
export class SeuilAlerteModule { }
