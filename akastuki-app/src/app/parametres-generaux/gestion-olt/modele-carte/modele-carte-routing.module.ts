import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModeleCarteComponent } from './modele-carte.component';

const routes: Routes = [
  {
    path: '',
    component: ModeleCarteComponent,
    data: {
      title: 'ModeleCarte'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModeleCarteRoutingModule { }
