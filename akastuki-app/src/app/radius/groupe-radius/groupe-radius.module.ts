import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GroupeRadiusRoutingModule } from './groupe-radius-routing.module';
import { GroupeRadiusComponent } from './groupe-radius.component';
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
import { ModalGroupeRadiusComponent } from './modal-groupe-radius/modal-groupe-radius.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';


@NgModule({
  declarations: [
    GroupeRadiusComponent,
    ModalGroupeRadiusComponent
  ],
  imports: [
    CommonModule,
    GroupeRadiusRoutingModule,
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
    ModalGroupeRadiusComponent
  ]
})
export class GroupeRadiusModule { }
