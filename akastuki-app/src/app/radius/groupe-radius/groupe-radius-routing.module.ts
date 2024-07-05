import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GroupeRadiusComponent } from './groupe-radius.component';

const routes: Routes = [
  {
    path: '',
    component: GroupeRadiusComponent,
    data: {
      title: 'Groupe Radius'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GroupeRadiusRoutingModule { }
