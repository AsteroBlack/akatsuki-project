import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TypeClientComponent } from './type-client.component';

const routes: Routes = [
  {
    path: '',
    component: TypeClientComponent,
    data: {
      title: 'Type client'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TypeClientRoutingModule { }
