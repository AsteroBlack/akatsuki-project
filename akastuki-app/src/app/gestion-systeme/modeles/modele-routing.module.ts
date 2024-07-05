import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModeleComponent } from './modele.component';

const routes: Routes = [
  {
    path: '',
    component: ModeleComponent,
    data: {
      title: 'Modèles Boxes'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModeleRoutingModule { }
