import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamPbComponent } from './param-pb.component';

const routes: Routes = [
  {
    path:'',component:ParamPbComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamPbRoutingModule { }
