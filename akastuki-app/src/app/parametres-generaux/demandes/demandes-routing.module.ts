import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DemandesComponent } from './demandes.component';

const routes: Routes = [
  {
    path:'',component:DemandesComponent,
    data: {
      title: 'Demandes'
    },
    children:[
      {
        path: "",
        redirectTo: "demande",
        pathMatch: "full",
      },
      {
        path:'type-client',
        loadChildren: () =>
              import("./type-client/type-client.module").then((m) => m.TypeClientModule)
      },
      {
        path:'etat-demande',
        loadChildren: () =>
              import("./param-etat-demande/param-etat-demande.module").then((m) => m.ParamEtatDemandeModule)
      },
      {
        path:'offres',
        loadChildren: () =>
              import("./param-offre/param-offre.module").then((m) => m.ParamOffreModule)
      },
      {
        path:'type-formule',
        loadChildren: () =>
              import("./param-type-formule/param-type-formule.module").then((m) => m.ParamTypeFormuleModule)
      },
      {
        path:'demande',
        loadChildren: () =>
              import("./param-demande/param-demande.module").then((m) => m.ParamDemandeModule)
      }
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DemandesRoutingModule { }
