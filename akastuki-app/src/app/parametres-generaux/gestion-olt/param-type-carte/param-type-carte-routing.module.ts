import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamTypeCarteComponent } from './param-type-carte.component';

const routes: Routes = [
  {
    path: '',
    component: ParamTypeCarteComponent,
    data: {
      title: 'État'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamTypeCarteRoutingModule { }
