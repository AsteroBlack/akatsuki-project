import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ParamTypePbSiteRoutingModule } from './param-type-pb-site-routing.module';
import { ParamTypePbSiteComponent } from './param-type-pb-site.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { ModalParamTypePbSiteComponent } from './modal-param-type-pb-site/modal-param-type-pb-site.component';


@NgModule({
  declarations: [
    ParamTypePbSiteComponent,
    ModalParamTypePbSiteComponent
  ],
  imports: [
    CommonModule,
    ParamTypePbSiteRoutingModule,
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
    MatPaginatorModule,
    
    MatExpansionModule,
    ReactiveFormsModule,
    FormsModule,
    MatToolbarModule,
    MatListModule,
  ]
})
export class ParamTypePbSiteModule { }
