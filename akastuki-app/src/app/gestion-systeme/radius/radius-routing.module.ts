import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RadiusComponent } from './radius.component';

const routes: Routes = [
  {
    path: '',
    component: RadiusComponent,
    data: {
      title: 'Offre'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RadiusRoutingModule { }
