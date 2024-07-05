import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RadiusRoutingModule } from './radius-routing.module';
import { RadiusComponent } from './radius.component';


@NgModule({
  declarations: [RadiusComponent],
  imports: [
    CommonModule,
    RadiusRoutingModule
  ]
})
export class RadiusModule { }
