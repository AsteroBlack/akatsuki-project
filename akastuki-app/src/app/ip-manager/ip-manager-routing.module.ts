import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IpManagerComponent } from './ip-manager.component';

const routes: Routes = [
  {
    path: '',
    component: IpManagerComponent,
    data: {
      title: 'IP manager'
    },
    children:  [
      {
        path: "",
        redirectTo: "ip",
        pathMatch: "full",
      },
      {
        path: "ip",
        loadChildren: () =>
          import("./ip/ip.module").then((m) => m.IpModule),
      },
      {
        path: "liberation-ip",
        loadChildren: () => 
          import("./liberation-ip/liberation-ip.module").then((m) => m.LiberationIpModule)
      },
      {
        path: "client",
        loadChildren: () =>
          import("./client/client.module").then((m) => m.ClientModule),
      },
      {
        path: "type-client",
        loadChildren: () =>
          import("./type-client/type-client.module").then((m) => m.TypeClientModule),
      },
      {
        path: "localisation",
        loadChildren: () =>
          import("./localisation/localisation.module").then((m) => m.LocalisationModule),
      },
      {
        path: "technologie",
        loadChildren: () =>
          import("./service/service.module").then((m) => m.ServiceModule),
      },
      {
        path: "zone",
        loadChildren: () =>
          import("./zone/zone.module").then((m) => m.ZoneModule),
      },
      {
        path: "personne-notif",
        loadChildren: () =>
          import("./personne-notif/personne-notif.module").then((m) => m.PersonneNotifModule),
      },
      {
        path: "seuil-alerte",
        loadChildren: () =>
          import("./seuil-alerte/seuil-alerte.module").then((m) => m.SeuilAlerteModule),
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IpManagerRoutingModule { }
