import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamEtatComponent } from './param-etat.component';

const routes: Routes = [
  {
    path: '',
    component: ParamEtatComponent,
    data: {
      title: 'État'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamEtatRoutingModule { }
