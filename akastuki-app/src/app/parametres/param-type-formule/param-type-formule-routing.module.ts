import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamTypeFormuleComponent } from './param-type-formule.component';

const routes: Routes = [
  {
    path: '',
    component: ParamTypeFormuleComponent,
    data: {
      title: 'Type de formule'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamTypeFormuleRoutingModule { }
