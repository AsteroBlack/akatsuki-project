import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamEtatDemandeComponent } from './param-etat-demande.component';

const routes: Routes = [
  {
    path: '',
    component: ParamEtatDemandeComponent,
    data: {
      title: 'Paramètre état demande'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamEtatDemandeRoutingModule { }
