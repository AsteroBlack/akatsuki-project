import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlaqueComponent } from './plaque.component';

const routes: Routes = [
  {
    path: '',
    component: PlaqueComponent,
    data: {
      title: 'Plaque'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlaqueRoutingModule { }
