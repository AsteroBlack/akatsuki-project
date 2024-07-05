import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamColorComponent } from './param-color.component';

const routes: Routes = [
  {
    path: '',
    component: ParamColorComponent,
    data: {
      title: 'Color'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamColorRoutingModule { }
