import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IpComponent } from './ip.component';

const routes: Routes = [
  {
    path: '',
    component: IpComponent,
    data: {
      title: 'IP'
    },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IpRoutingModule { }
