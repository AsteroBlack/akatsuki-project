import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ParametresRoutingModule } from './parametres-routing.module';
import { PlaqueModule } from '../parametres/plaque/plaque.module';
import { ParametresComponent } from './parametres.component';


@NgModule({
  declarations: [ParametresComponent],
  imports: [
    CommonModule,
    ParametresRoutingModule,
    PlaqueModule
  ]
})
export class ParametresModule { }
