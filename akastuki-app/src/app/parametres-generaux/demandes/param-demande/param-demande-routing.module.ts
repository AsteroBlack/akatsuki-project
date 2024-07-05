import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamDemandeComponent } from './param-demande.component';

const routes: Routes = [
  {
    path:'',component:ParamDemandeComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamDemandeRoutingModule { }
