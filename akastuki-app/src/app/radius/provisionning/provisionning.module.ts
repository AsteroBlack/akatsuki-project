import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProvisionningRoutingModule } from './provisionning-routing.module';
import { ProvisionningComponent } from './provisionning.component';
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
import { ModalProvisionningComponent } from './modal-provisionning/modal-provisionning.component';
import { NgxMaskModule } from 'ngx-mask';
import { ModalDetailProvisionningComponent } from './modal-detail-provisionning/modal-detail-provisionning.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { PerfectScrollbarModule, PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { ModalRallogementComponent } from './modal-rallogement/modal-rallogement.component';
import { MatNativeDateModule } from '@angular/material/core';
import {
  MAT_MOMENT_DATE_FORMATS,
  MomentDateAdapter,
  MAT_MOMENT_DATE_ADAPTER_OPTIONS,
} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import { DetailAccountingComponent } from './detail-accounting/detail-accounting.component';
const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

@NgModule({
  declarations: [
    ProvisionningComponent,
    ModalProvisionningComponent,
    ModalDetailProvisionningComponent,
    ModalRallogementComponent,
    DetailAccountingComponent
  ],
  imports: [
    CommonModule,
    ProvisionningRoutingModule,
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
    MatDatepickerModule,
    MatNativeDateModule,
    NgxMaskModule.forRoot(),
    PerfectScrollbarModule
  ],
  entryComponents: [
    ModalProvisionningComponent,
    ModalDetailProvisionningComponent,
    ModalRallogementComponent
  ],
  providers: [
    {
      provide: PERFECT_SCROLLBAR_CONFIG,
      useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
    },
    {provide: MAT_DATE_LOCALE, useValue: 'fr-FR'},
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
  ]
})
export class ProvisionningModule { }
