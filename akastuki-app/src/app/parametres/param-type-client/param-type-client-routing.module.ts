import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamTypeClientComponent } from './param-type-client.component';

const routes: Routes = [
  {
    path: '',
    component: ParamTypeClientComponent,
    data: {
      title: 'État'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamTypeClientRoutingModule { }
