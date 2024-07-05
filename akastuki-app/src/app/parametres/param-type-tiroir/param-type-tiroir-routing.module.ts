import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamTypeTiroirComponent } from './param-type-tiroir.component';

const routes: Routes = [
  {
    path: '',
    component: ParamTypeTiroirComponent,
    data: {
      title: 'Param type tiroir '
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamTypeTiroirRoutingModule { }
