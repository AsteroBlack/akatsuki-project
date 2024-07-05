import { ReportingRoutingModule } from './reporting-routing.module';
import { NgModule, LOCALE_ID } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportingComponent } from './reporting/reporting.component';
import { ProvisioningStatusChartComponent } from './reporting/provisioning-status-chart/provisioning-status-chart.component';

import { HighchartsChartModule } from 'highcharts-angular';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatMomentDateModule } from '@angular/material-moment-adapter';
import { StatusDetailsChartComponent } from './reporting/status-details-chart/status-details-chart.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ChartLoaderComponent } from './reporting/chart-loader/chart-loader.component';
import { CommandesListComponent } from './reporting/commandes-list/commandes-list.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { ModalCommandeDetailsComponent } from './reporting/commandes-list/modal-commande-details/modal-commande-details.component';
import { NgxTimelineModule } from '@frxjs/ngx-timeline';
import { RequestValueDetailsComponent } from './reporting/commandes-list/modal-commande-details/request-value-details/request-value-details.component';
import { SafePipe } from './reporting/pipes/safe.pipe';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';


registerLocaleData(localeFr, 'fr-FR');


@NgModule({
  providers: [{ provide: LOCALE_ID, useValue: 'fr-FR' }],
  declarations: [ReportingComponent, ProvisioningStatusChartComponent, StatusDetailsChartComponent, ChartLoaderComponent, CommandesListComponent, ModalCommandeDetailsComponent, RequestValueDetailsComponent, SafePipe],
  imports: [
    CommonModule,
    ReportingRoutingModule,
    HighchartsChartModule,
    MatDatepickerModule,
    MatInputModule,
    MatMomentDateModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    NgxTimelineModule
  ]
})
export class ReportingModule { }
