import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ParametresGenerauxComponent } from './parametres-generaux.component';

const routes: Routes = [
  {
    path:'',
    component:ParametresGenerauxComponent,
    data: {
      title: 'Parametres généraux'
    },
    children:[
      {
        path: "",
        redirectTo: "couleurs",
        pathMatch: "full",
      },
      {
        path:'couleurs',
        loadChildren: () =>
              import("./param-color/param-color.module").then((m) => m.ParamColorModule)
      },
      {
        path:'distributions',
        loadChildren: () =>
              import("./distribution/distribution.module").then((m) => m.DistributionModule)
      }
      ,
      {
        path:'transports',
        loadChildren: () =>
              import("./transport/transport.module").then((m) => m.TransportModule)
      }
      ,
      {
        path:'gestion-olt',
        loadChildren: () =>
              import("./gestion-olt/gestion-olt.module").then((m) => m.GestionOltModule)
      }
      ,
      {
        path:'gestion-demandes',
        loadChildren: () =>
              import("./demandes/demandes.module").then((m) => m.DemandesModule)
      },
      {
        path:'architectures',
        loadChildren: () =>
              import("./param-architecture/param-architecture.module").then((m) => m.ParamArchitectureModule)
      }
    ]
  },
  

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParametresGenerauxRoutingModule { }
