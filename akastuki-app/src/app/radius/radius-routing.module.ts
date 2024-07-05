import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
// import { AttributsComponent } from './attributs/attributs.component';
import { RadiusComponent } from './radius.component';

const routes: Routes = [
  {
    path: '',
    component: RadiusComponent,
    data: {
      title: 'Radius'
    },
    children: [
      {
        path: "",
        redirectTo: "groupe-radius",
        pathMatch: "full",
      },
      {
        path: "attributs",
        loadChildren: () =>
          import("./attributs/attributs.module").then((m) => m.AttributsModule),
      },
      {
        path: "type-attributs",
        loadChildren: () =>
          import("./type-attributs/type-attributs.module").then((m) => m.TypeAttributsModule),
      },
      {
        path: "groupe-radius",
        loadChildren: () =>
          import("./groupe-radius/groupe-radius.module").then((m) => m.GroupeRadiusModule),
      },
      {
        path: "provisionning",
        loadChildren: () =>
          import("./provisionning/provisionning.module").then((m) => m.ProvisionningModule),
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RadiusRoutingModule { }
