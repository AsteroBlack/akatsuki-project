import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
// import { RadiusComponent } from '../radius.component';
import { TypeAttributsComponent } from './type-attributs.component';

const routes: Routes = [
  {
    path: '',
    component: TypeAttributsComponent,
    data: {
      title: 'Type d\'Attributs'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TypeAttributsRoutingModule { }
