import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TypeAttributsRoutingModule } from './type-attributs-routing.module';
import { TypeAttributsComponent } from './type-attributs.component';
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
import { SharedModule } from 'src/app/shared/shared.module';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { ModalTypeAttributsComponent } from './modal-type-attributs/modal-type-attributs.component';


@NgModule({
  declarations: [
    TypeAttributsComponent,
    ModalTypeAttributsComponent
  ],
  imports: [
    CommonModule,
    TypeAttributsRoutingModule,
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
  ],
  entryComponents: [
    ModalTypeAttributsComponent
  ]
})
export class TypeAttributsModule { }
