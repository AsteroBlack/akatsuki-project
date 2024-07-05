import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlaqueComponent } from '../parametres/plaque/plaque.component';
import { QuartierComponent } from '../parametres/quartier/quartier.component';
import { VilleComponent } from '../parametres/param-ville/ville.component';
import { ParametresComponent } from './parametres.component';

const routes: Routes = [
  {
    path:'',
    component:ParametresComponent,
    data: {
      title: 'Parametres'
    },
    children:[
      {
        path: "",
        redirectTo: "quartiers",
        pathMatch: "full",
      },
      {
        path: 'param-etat',
        loadChildren: ()=> import('./param-etat/param-etat.module').then((m)=>m.ParamEtatModule)
      },
      {
        path: 'param-nro',
        loadChildren: ()=> import('./param-nro/param-nro.module').then((m)=>m.ParamNroModule)
      },
      {
        path: 'arrondissements',
        loadChildren: ()=> import('./arrondissement/arrondissement.module').then((m)=>m.ArrondissementModule)
      },
      {
        path: 'ville',
        loadChildren: ()=> import('./param-ville/ville.module').then((m)=>m.VilleModule)
      },
      {
        path: 'sites',
        loadChildren: ()=> import('./param-site/site.module').then((m)=>m.SiteModule)
      },
      {
        path: 'cluster',
        loadChildren: ()=> import('./cluster/cluster.module').then((m)=>m.ClusterModule)
      },
      {
        path: 'communes',
        loadChildren: ()=> import('./communes/communes.module').then((m)=>m.CommunesModule)
      },
      {
        path: 'modeles-carte',
        loadChildren: ()=> import('./modele-carte/modele-carte.module').then((m)=>m.ModeleCarteModule)
      },
      {
        path: 'olt',
        loadChildren: ()=> import('./olt/olt.module').then((m)=>m.OltModule)
      },
      {
        path: 'param-architecture',
        loadChildren: ()=> import('./param-architecture/param-architecture.module').then((m)=>m.ParamArchitectureModule)
      },
      {
        path: 'param-etat-demande',
        loadChildren: ()=> import('./param-etat-demande/param-etat-demande.module').then((m)=>m.ParamEtatDemandeModule)
      },
      {
        path: 'param-nro',
        loadChildren: ()=> import('./param-nro/param-nro.module').then((m)=>m.ParamNroModule)
      },
      {
        path: 'param-offre',
        loadChildren: ()=> import('./param-offre/param-offre.module').then((m)=>m.ParamOffreModule)
      },
      {
        path: 'param-olt-techno',
        loadChildren: ()=> import('./param-olt-techno/param-olt-techno.module').then((m)=>m.ParamOltTechnoModule)
      },
      {
        path: 'param-pd-type',
        loadChildren: ()=> import('./param-pd-type/param-pd-type.module').then((m)=>m.ParamPdTypeModule)
      },
      // {
      //   path: 'param-site',
      //   loadChildren: ()=> import('./param-site/site.module').then((m)=>m.SiteModule)
      // },
      {
        path: 'param-tiroir-distrib',
        loadChildren: ()=> import('./param-tiroir-distrib/param-tiroir-distrib.module').then((m)=>m.ParamTiroirDistribModule)
      },
      {
        path: 'param-type-carte',
        loadChildren: ()=> import('./param-type-carte/param-type-carte.module').then((m)=>m.ParamTypeCarteModule)
      },
      {
        path: 'param-type-client',
        loadChildren: ()=> import('./param-type-client/param-type-client.module').then((m)=>m.ParamTypeClientModule)
      },
      {
        path: 'param-type-formule',
        loadChildren: ()=> import('./param-type-formule/param-type-formule.module').then((m)=>m.ParamTypeFormuleModule)
      },
      {
        path: 'param-type-olt',
        loadChildren: ()=> import('./param-type-olt/param-type-olt.module').then((m)=>m.ParamTypeOltModule)
      },
      // {
      //   path: 'param-type-pb-site',
      //   loadChildren: ()=> import('./param-type-pb-site/param-type-pb-site.module').then((m)=>m.ParamTypePbSiteModule)
      // },
      // {
      //   path: 'param-type-tiroir',
      //   loadChildren: ()=> import('./param-type-pb-site/param-type-pb-site.module').then((m)=>m.ParamTypePbSiteModule)
      // },
      {
        path: 'villes',
        loadChildren: ()=> import('./param-ville/ville.module').then((m)=>m.VilleModule)
      },
      {
        path: 'plaques',
        loadChildren: ()=> import('./plaque/plaque.module').then((m)=>m.PlaqueModule)
      },
      {
        path: 'plaque-quartier',
        loadChildren: ()=> import('./plaque-quartier/plaque-quartier.module').then((m)=>m.PlaqueQuartierModule)
      },
      {
        path: 'quartiers',
        loadChildren: ()=> import('./quartier/quartier.module').then((m)=>m.QuartierModule)
      },
      {
        path: 'secteur',
        loadChildren: ()=> import('./secteur/secteur.module').then((m)=>m.SecteurModule)
      },
    ]
  },
  

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ParametresRoutingModule { }
