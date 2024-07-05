import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AttributsRoutingModule } from './attributs-routing.module';
import { AttributsComponent } from './attributs.component';
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
import { ModalAttributsComponent } from './modal-attributs/modal-attributs.component';


@NgModule({
  declarations: [
    AttributsComponent,
    ModalAttributsComponent
  ],
  imports: [
    CommonModule,
    AttributsRoutingModule,
    MatTableModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FlexLayoutModule,
    MatDialogModule,
    ReactiveFormsModule,
    FormsModule,
    MatFileUploadModule,
    MatSelectModule,
    MatPaginatorModule
  ],
  entryComponents: [
    ModalAttributsComponent
  ]
})
export class AttributsModule { }
