import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionSystemeRoutingModule } from './gestion-systeme-routing.module';
import { GestionSystemeComponent } from './gestion-systeme.component';

import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule, MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from '../shared/shared.module';
import {MatDialogModule} from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ErrorStateMatcher, ShowOnDirtyErrorStateMatcher } from '@angular/material/core';
import { MatFileUploadModule } from 'angular-material-fileupload';
import {MatSelectModule} from '@angular/material/select';
import {MatPaginatorModule} from '@angular/material/paginator';


@NgModule({
  declarations: [
    GestionSystemeComponent,
  
  ],
  imports: [
    CommonModule,
    GestionSystemeRoutingModule,
    // MatTableModule,
    // MatCardModule,
    // MatFormFieldModule,
    // MatInputModule,
    // MatIconModule,
    // FlexLayoutModule,
    // SharedModule,
    // MatDialogModule,
    // ReactiveFormsModule,
    // FormsModule,
    // MatFileUploadModule,
    // MatSelectModule,
    // MatPaginatorModule
  ]
})
export class GestionSystemeModule { }
