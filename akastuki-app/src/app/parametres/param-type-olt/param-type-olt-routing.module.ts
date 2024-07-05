import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamTypeOltComponent } from './param-type-olt.component';

const routes: Routes = [
  {
    path: '',
    component: ParamTypeOltComponent,
    data: {
      title: 'État'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamTypeOltRoutingModule { }
