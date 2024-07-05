import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ComptesComponent } from './comptes.component';


const routes: Routes = [
  {
    path: '',
    component: ComptesComponent,
    data: {
      title: 'Comptes'
    }
  }
];
@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ]
})
export class ComptesRoutingModule { }
