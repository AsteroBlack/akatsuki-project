import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProvisionningComponent } from './provisionning.component';

const routes: Routes = [
  {
    path: '',
    component: ProvisionningComponent,
    data: {
      title: 'Provisionning'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProvisionningRoutingModule { }
