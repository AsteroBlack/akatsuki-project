import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IpManagerRoutingModule } from './ip-manager-routing.module';
import { IpManagerComponent } from './ip-manager.component';


@NgModule({
  declarations: [
    IpManagerComponent,
  ],
  imports: [
    CommonModule,
    IpManagerRoutingModule
  ]
})
export class IpManagerModule { }
