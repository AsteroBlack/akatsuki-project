import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamPbSiteComponent } from './param-pb-site.component';

const routes: Routes = [
  {
    path: '',
    component: ParamPbSiteComponent,
    data: {
      title: 'Param pb Site'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamPbSiteRoutingModule { }
