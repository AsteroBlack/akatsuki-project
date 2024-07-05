import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GestionRessourcesComponent } from './gestion-ressources.component';

const routes: Routes = [
  {
    path: '',
    component: GestionRessourcesComponent,
    data: {
      title: 'Gestion des ressources'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionRessourcesRoutingModule { }
