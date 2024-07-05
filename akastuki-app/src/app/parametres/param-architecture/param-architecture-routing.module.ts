import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamArchitectureComponent } from './param-architecture.component';

const routes: Routes = [
  {
    path: '',
    component: ParamArchitectureComponent,
    data: {
      title: 'Architecture'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamArchitectureRoutingModule { }
