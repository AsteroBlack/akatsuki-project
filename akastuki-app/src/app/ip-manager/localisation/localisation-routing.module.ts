import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LocalisationComponent } from './localisation.component';

const routes: Routes = [
  {
    path: '',
    component: LocalisationComponent,
    data: {
      title: 'Localisation'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LocalisationRoutingModule { }
