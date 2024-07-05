import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonneNotifComponent } from './personne-notif.component';

const routes: Routes = [
  {
    path: '',
    component: PersonneNotifComponent,
    data: {
      title: 'Personne à notifier'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonneNotifRoutingModule { }
