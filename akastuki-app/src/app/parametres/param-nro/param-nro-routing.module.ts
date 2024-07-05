import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamNroComponent } from './param-nro.component';

const routes: Routes = [
  {
    path: '',
    component: ParamNroComponent,
    data: {
      title: 'Param NRO'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamNroRoutingModule { }
