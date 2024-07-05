import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionOltRoutingModule } from './gestion-olt-routing.module';
import { GestionOltComponent } from './gestion-olt.component';


@NgModule({
  declarations: [GestionOltComponent],
  imports: [
    CommonModule,
    GestionOltRoutingModule
  ]
})
export class GestionOltModule { }
