import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TypeOffreComponent } from './type-offre.component';

const routes: Routes = [
  {
    path: '',
    component: TypeOffreComponent,
    data: {
      title: 'Type d\'offre'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TypeOffreRoutingModule { }
