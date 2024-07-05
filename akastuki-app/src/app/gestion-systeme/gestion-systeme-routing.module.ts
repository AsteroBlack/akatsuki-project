import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GestionSystemeComponent } from './gestion-systeme.component';

const routes: Routes = [
  {
    path: '',
    component: GestionSystemeComponent,
    data: {
      title: 'Gestion des système'
    },
    children: [
      {
        path: "",
        redirectTo: "modeles",
        pathMatch: "full",
      },
      {
        path: "modeles",
        loadChildren: () =>
          import("./modeles/modele.module").then((m) => m.ModeleModule),
      },
      {
        path: "type-offre",
        loadChildren: () =>
          import("./type-offre/type-offre.module").then((m) => m.TypeOffreModule),
      },
      // {
      //   path: "groupe-radius",
      //   loadChildren: () =>
      //     import("./groupe-radius/groupe-radius.module").then((m) => m.GroupeRadiusModule),
      // },
      /* {
        path: "offre",
        loadChildren: () =>
          import("./radius/radius.module").then((m) => m.RadiusModule),
      }, */
      {
        path: "offre",
        loadChildren: () =>
          import("./offre/offre.module").then((m) => m.OffreModule),
      },
      {
        path: "offre-olt",
        loadChildren: () =>
          import("./offre-olt/offre-olt.module").then((m) => m.OffreOltModule),
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionSystemeRoutingModule { }
