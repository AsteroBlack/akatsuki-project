import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdministrationComponent } from './administration.component';

const routes: Routes = [
  {
    path: "",
    component: AdministrationComponent,
    data: {
      title: 'Administration'
    },
    // canLoad: [ComptesGuard],
    children: [
      {
        path: "",
        redirectTo: "utilisateurs",
        pathMatch: "full",
      },
      {
        path: "utilisateurs",
        loadChildren: () =>
          import("./utilisateurs/utilisateurs.module").then((m) => m.UtilisateursModule),
      },
      {
        path: "roles",
        loadChildren: () =>
          import("./roles/roles.module").then((m) => m.RolesModule),
      },
      {
        path: "actions",
        loadChildren: () =>
          import("./actions/actions.module").then((m) => m.ActionsModule),
      },
      {
        path: "menus",
        loadChildren: () =>
          import("./menus/menus.module").then((m) => m.MenusModule),
      },
      {
        path: "modules",
        loadChildren: () =>
          import("./modules/modules.module").then((m) => m.ModulesModule),
      },
      {
        path: "comptes",
        loadChildren: () =>
          import("./comptes/comptes.module").then((m) => m.ComptesModule),
      }
    ],
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministrationRoutingModule { }
