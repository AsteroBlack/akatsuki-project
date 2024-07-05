import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SecteurComponent } from './secteur.component';

const routes: Routes = [
  {
    path: '',
    component: SecteurComponent,
    data: {
      title: 'Secteur'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SecteurRoutingModule { }
