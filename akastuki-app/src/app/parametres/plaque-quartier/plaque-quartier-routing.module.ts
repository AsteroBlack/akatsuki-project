import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlaqueQuartierComponent } from './plaque-quartier.component';

const routes: Routes = [
  {
    path: '',
    component: PlaqueQuartierComponent,
    data: {
      title: 'Plaque Quartier'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlaqueQuartierRoutingModule { }
