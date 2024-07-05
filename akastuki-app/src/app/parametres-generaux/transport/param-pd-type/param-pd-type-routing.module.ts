import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamPdTypeComponent } from './param-pd-type.component';

const routes: Routes = [
  {
    path: '',
    component: ParamPdTypeComponent,
    data: {
      title: 'Pd type'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamPdTypeRoutingModule { }
