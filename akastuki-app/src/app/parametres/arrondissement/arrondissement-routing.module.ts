import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ArrondissementComponent } from './arrondissement.component';

const routes: Routes = [
  {
    path: '',
    component: ArrondissementComponent,
    data: {
      title: 'Arrondissement'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArrondissementRoutingModule { }
