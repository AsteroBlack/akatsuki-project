import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamTiroirTrspComponent } from './param-tiroir-trsp.component';

const routes: Routes = [
  {
    path: '',
    component: ParamTiroirTrspComponent,
    data: {
      title: 'Tiroir transport'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamTiroirTrspRoutingModule { }
