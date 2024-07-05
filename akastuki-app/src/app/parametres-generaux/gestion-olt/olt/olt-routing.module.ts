import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OltComponent } from './olt.component';

const routes: Routes = [
  {
    path: '',
    component: OltComponent,
    data: {
      title: 'Olt'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OltRoutingModule { }
