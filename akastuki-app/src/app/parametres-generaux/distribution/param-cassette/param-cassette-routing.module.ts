import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParamCassetteComponent } from './param-cassette.component';

const routes: Routes = [
  {
    path: '',
    component: ParamCassetteComponent,
    data: {
      title: 'Cassette'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParamCassetteRoutingModule { }
