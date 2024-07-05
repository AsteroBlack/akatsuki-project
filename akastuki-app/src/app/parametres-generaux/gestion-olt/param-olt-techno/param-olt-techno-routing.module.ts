import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamOltTechnoComponent } from './param-olt-techno.component';

const routes: Routes = [
  {
    path: '',
    component: ParamOltTechnoComponent,
    data: {
      title: 'Olt Techno'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamOltTechnoRoutingModule { }
