import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GestionOltComponent } from './gestion-olt.component';

const routes: Routes = [
  {
    path:'',component:GestionOltComponent,
    data: {
      title: 'Gestion Olt'
    },
    children:[
      {
        path: "",
        redirectTo: "olt",
        pathMatch: "full",
      },
      {
        path:'olt',
        loadChildren: () =>
              import("./olt/olt.module").then((m) => m.OltModule)
      }
      ,
      {
        path:'modele-carte',
        loadChildren: () =>
              import("./modele-carte/modele-carte.module").then((m) => m.ModeleCarteModule)
      },
      {
        path:'techno',
        loadChildren: () =>
              import("./param-olt-techno/param-olt-techno.module").then((m) => m.ParamOltTechnoModule)
      },
      {
        path:'type-olt',
        loadChildren: () =>
              import("./param-type-olt/param-type-olt.module").then((m) => m.ParamTypeOltModule)
      }
      ,
      {
        path:'nro',
        loadChildren: () =>
              import("./param-nro/param-nro.module").then((m) => m.ParamNroModule)
      },
      {
        path:'type-carte',
        loadChildren: () =>
              import("./param-type-carte/param-type-carte.module").then((m) => m.ParamTypeCarteModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionOltRoutingModule { }
