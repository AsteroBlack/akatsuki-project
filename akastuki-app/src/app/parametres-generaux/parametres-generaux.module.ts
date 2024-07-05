import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ParametresGenerauxRoutingModule } from './parametres-generaux-routing.module';
import { ParametresGenerauxComponent } from './parametres-generaux.component';


@NgModule({
  declarations: [ParametresGenerauxComponent],
  imports: [
    CommonModule,
    ParametresGenerauxRoutingModule
  ]
})
export class ParametresGenerauxModule { }
