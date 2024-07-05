import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DistributionComponent } from './distribution.component';

const routes: Routes = [
  {
    path:'',component:DistributionComponent,
    data: {
      title: 'Distributions'
    },
    children:[
      {
        path: "",
        redirectTo: "cassettes",
        pathMatch: "full",
      },
      {
        path:'cassettes',
        loadChildren: () =>
              import("./param-cassette/param-cassette.module").then((m) => m.ParamCassetteModule)
      },
      {
        path:'coupleurs',
        loadChildren: () =>
              import("./param-coupleur/param-coupleur.module").then((m) => m.ParamCoupleurModule)
      },
      {
        path:'type-site-pb',
        loadChildren: () =>
              import("./param-pb-site/param-pb-site.module").then((m) => m.ParamPbSiteModule)
      },
      {
        path:'tiroir-distrib',
        loadChildren: () =>
              import("./param-tiroir-distrib/param-tiroir-distrib.module").then((m) => m.ParamTiroirDistribModule)
      },
      {
        path:'pb',
        loadChildren: () =>
              import("./param-pb/param-pb.module").then((m) => m.ParamPbModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DistributionRoutingModule { }
