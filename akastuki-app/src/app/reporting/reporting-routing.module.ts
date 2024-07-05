import { ReportingComponent } from './reporting/reporting.component';
import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';


@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: '',
        redirectTo: '/reporting',
        pathMatch: 'full'
      },
      {
        path: 'reporting',
        component: ReportingComponent,
      }
    ])
  ],
  exports: [RouterModule]
})
export class ReportingRoutingModule { }
