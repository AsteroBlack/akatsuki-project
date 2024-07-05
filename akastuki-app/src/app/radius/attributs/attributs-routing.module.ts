import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
// import { RadiusComponent } from '../radius.component';
import { AttributsComponent } from './attributs.component';

const routes: Routes = [
  {
    path: '',
    component: AttributsComponent,
    data: {
      title: 'Attributs'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AttributsRoutingModule { }
