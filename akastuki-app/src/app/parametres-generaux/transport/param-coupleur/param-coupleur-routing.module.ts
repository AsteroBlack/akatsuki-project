import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamCoupleurComponent } from './param-coupleur.component';

const routes: Routes = [
  {
    path: '',
    component: ParamCoupleurComponent,
    data: {
      title: 'Coupleur'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamCoupleurRoutingModule { }
