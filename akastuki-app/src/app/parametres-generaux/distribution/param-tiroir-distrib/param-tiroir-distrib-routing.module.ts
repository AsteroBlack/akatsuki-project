import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamTiroirDistribComponent } from './param-tiroir-distrib.component';

const routes: Routes = [
  {
    path: '',
    component: ParamTiroirDistribComponent,
    data: {
      title: 'Tiroir distribution'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamTiroirDistribRoutingModule { }
