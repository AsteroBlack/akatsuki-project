import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DistributionRoutingModule } from './distribution-routing.module';
import { DistributionComponent } from './distribution.component';


@NgModule({
  declarations: [DistributionComponent],
  imports: [
    CommonModule,
    DistributionRoutingModule
  ]
})
export class DistributionModule { }
