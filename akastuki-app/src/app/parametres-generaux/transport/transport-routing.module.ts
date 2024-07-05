import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TransportComponent } from './transport.component';

const routes: Routes = [
  {
    path:'',component:TransportComponent,
    data: {
      title: 'Transports'
    },
    children:[
      {
        path: "",
        redirectTo: "tiroirs",
        pathMatch: "full",
      },
      {
        path:'coupleurs',
        loadChildren: () =>
              import("./param-coupleur/param-coupleur.module").then((m) => m.ParamCoupleurModule)

      },
      {
        path:'tiroirs',
        loadChildren: () =>
              import("./param-tiroir-trsp/param-tiroir-trsp.module").then((m) => m.ParamTiroirTrspModule)
      },
      {
        path:'clusters',
        loadChildren: () =>
              import("./cluster/cluster.module").then((m) => m.ClusterModule)
      },
      {
        path:'pd-type',
        loadChildren: () =>
              import("./param-pd-type/param-pd-type.module").then((m) => m.ParamPdTypeModule)
      },
     
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransportRoutingModule { }
