import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionRessourcesRoutingModule } from './gestion-ressources-routing.module';
import { GestionRessourcesComponent } from './gestion-ressources.component';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule, MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from '../shared/shared.module';
import {MatDialogModule} from '@angular/material/dialog';
import { ModalRessourcesComponent } from './modal-ressources/modal-ressources.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ErrorStateMatcher, ShowOnDirtyErrorStateMatcher } from '@angular/material/core';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { ModalUploadRessourceComponent } from './modal-upload-ressource/modal-upload-ressource.component';
import {MatSelectModule} from '@angular/material/select';
import {MatPaginatorModule} from '@angular/material/paginator';
import { HttpClientModule } from '@angular/common/http';
import { ModalAttributionComponent } from './modal-attribution/modal-attribution.component';
import { MatTabsModule } from '@angular/material/tabs';
import {MatCheckboxModule} from '@angular/material/checkbox';


@NgModule({
  declarations: [
    GestionRessourcesComponent, 
    ModalRessourcesComponent,
    ModalUploadRessourceComponent,
    ModalAttributionComponent
  ],
  imports: [
    CommonModule,
    GestionRessourcesRoutingModule,
    // HttpClientModule,
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
    MatTabsModule,
    MatCheckboxModule
  ],
  entryComponents: [
    ModalRessourcesComponent,
    ModalUploadRessourceComponent
  ],
  providers: [
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {floatLabel: 'always'}},
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}
  ]
})
export class GestionRessourcesModule { }
