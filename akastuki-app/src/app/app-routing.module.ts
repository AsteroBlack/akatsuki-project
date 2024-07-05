import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { MainViewComponent } from './shared/main-view/main-view.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'auth',
    pathMatch: 'full'
  },
   {
    // Ajoutez cette route pour gérer la redirection vers success.html
    path: 'success.html',
    redirectTo: '', // Redirigez vers la page principale ou une autre page selon votre structure
    pathMatch: 'full'
  },
  {
    path: 'auth',
    component: AuthComponent
    // loadChildren: ()=> import('./auth/auth.module').then((m)=>m.AuthModule)
  },
  {
    path: '',
    component: MainViewComponent,
    children: [
      {
        path: '',
        redirectTo: 'gestion-ressources',
        pathMatch: 'full'
      },
      {
        path: 'gestion-ressources',
        loadChildren: ()=> import('./gestion-ressources/gestion-ressources.module').then((m)=>m.GestionRessourcesModule)
      },
      {
        path: "administration",
        loadChildren: () =>
          import("./administration/administration.module").then((m) => m.AdministrationModule),
      },
      {
        path: "ip-manager",
        loadChildren: () =>
          import("./ip-manager/ip-manager.module").then((m) => m.IpManagerModule),
      },
      {
        path: "gestion-systeme",
        loadChildren: () =>
          import("./gestion-systeme/gestion-systeme.module").then((m) => m.GestionSystemeModule),
      },
      {
        path: "radius",
        loadChildren: () =>
          import("./radius/radius.module").then((m) => m.RadiusModule),
      },
      // {
      //   path: "parametre-fibre",
      //   loadChildren: () =>
      //     import("./parametres-fibre/parametres-fibre.module").then((m) => m.ParametresFibreModule),
      // },
      {
        path: "parametres",
        loadChildren: () =>
          import("./parametres/parametres.module").then((m) => m.ParametresModule),
      },
      {
        path: "parametres-generaux",
        loadChildren: () =>
          import("./parametres-generaux/parametres-generaux.module").then((m) => m.ParametresGenerauxModule),
      },
      {
        path: "dashboard",
        loadChildren: () => import('./reporting/reporting.module').then((m)=>m.ReportingModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
