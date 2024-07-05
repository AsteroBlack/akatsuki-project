import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamOffreComponent } from './param-offre.component';

const routes: Routes = [
  {
    path: '',
    component: ParamOffreComponent,
    data: {
      title: 'Offre'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamOffreRoutingModule { }
