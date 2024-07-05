import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommunesComponent } from './communes.component';

const routes: Routes = [
  {
    path: '',
    component: CommunesComponent,
    data: {
      title: 'Communes'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CommunesRoutingModule { }
