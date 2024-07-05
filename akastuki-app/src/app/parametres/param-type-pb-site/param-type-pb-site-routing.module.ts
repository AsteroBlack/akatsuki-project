import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamTypePbSiteComponent } from './param-type-pb-site.component';

const routes: Routes = [
  {
    path: '',
    component: ParamTypePbSiteComponent,
    data: {
      title: 'Param type pb site'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamTypePbSiteRoutingModule { }
