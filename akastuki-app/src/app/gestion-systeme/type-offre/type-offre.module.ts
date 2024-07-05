import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TypeOffreRoutingModule } from './type-offre-routing.module';
import { TypeOffreComponent } from './type-offre.component';
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
import { ModalTypeOffreComponent } from './modal-type-offre/modal-type-offre.component';


@NgModule({
  declarations: [
    TypeOffreComponent,
    ModalTypeOffreComponent
  ],
  imports: [
    CommonModule,
    TypeOffreRoutingModule,
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
    // ModalTypeOffreComponent
  ]
})
export class TypeOffreModule { }
