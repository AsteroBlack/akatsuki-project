import { Component, OnInit, Input } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { filter, map, mergeMap, shareReplay } from 'rxjs/operators';
import { MatSidenav } from '@angular/material/sidenav';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-main-view',
  templateUrl: './main-view2.component.html',
  styleUrls: ['./main-view.component.scss']
})
export class MainViewComponent implements OnInit {
  @Input() permanentAt: number = 0;
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );


  // currentSubMenuLibelle='Parametres'


  menuItems = [
    {
      libelle: 'Infrastructure physique',
      route: '/gestion-ressources',
      icone: 'home',
      open: false,
      hidden: !this.userService.checkModuleExiste('INFRASTRUCTURE_PHYSIQUE'),
      children: [
        {
          libelle: 'Gestion-ressources',
          route: '/gestion-ressources',
          hidden: !this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'GESTION_DES_RESSOURCES', 'VISUALISER_RESSOURCE'),
        },
        {
          libelle: 'Parametres',
          route: '/parametres',
          hidden: !this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'PARAMETRES', 'VISUALISER_PARAMETRES')
        },
        {
          libelle: 'Parametres-généraux',
          route: '/parametres-generaux',
          hidden: !this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'PARAMETRES_GENERAUX', 'VISUALISER_PARAMETRES_GENERAUX')

        }
      ]
    },
    {
      libelle: 'Gestion des IP',
      route: '/Gestion des IP',
      icone: 'settings_remote',
      hidden: !this.userService.checkModuleExiste('GESTION_DES_IP'),
      open: false,
      children: [
        {
          libelle: 'IP',
          route: '/ip-manager/ip',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_IP', 'IP', 'VISUALISER_IP')

        },
        {
          libelle: 'IP Bloqué',
          route: '/ip-manager/liberation-ip',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_IP', 'IP_BLOQUER', 'GESTION_IP_BLOQUER')
          //hidden: false
        },
        {
          libelle: 'Type-client',
          route: '/ip-manager/type-client',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_IP', 'TYPE_CLIENT', 'VISUALISER_TYPE_CLIENT')
        },
        {
          libelle: 'Localisation',
          route: '/ip-manager/localisation',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_IP', 'LOCALISATION', 'VISUALISER_LOCALISATION')
        },
        {
          libelle: 'Technologie',
          route: '/ip-manager/technologie',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_IP', 'TECHNOLOGIE', 'VISUALISER_TECHNOLOGIE')
        },
        {
          libelle: 'Zone',
          route: '/ip-manager/zone',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_IP', 'ZONE', 'VISUALISER_ZONE')

        },
        {
          libelle: 'Seuil-alerte',
          route: '/ip-manager/seuil-alerte',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_IP', 'SEUIL_ALERTE', 'VISUALISER_SEUIL_ALERTE')

        },
        {
          libelle: 'Personne-notif',
          route: '/ip-manager/personne-notif',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_IP', 'PERSONNE_NOTIF', 'VISUALISER_PERSONNE_NOTIF')

        },
      ]
    },
    {
      libelle: 'Gestion des offres',
      // route: 'gestion-ressources',
      icone: 'system_security_update_warning',
      hidden: !this.userService.checkModuleExiste('GESTION_DES_OFFRES'),
      open: false,
      children: [
        {
          libelle: 'Type-offre',
          route: '/gestion-systeme/type-offre',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'TYPE_OFFRE', 'VISUALISER_TYPE_OFFRE')
        },
        {
          libelle: 'Modèles',
          route: '/gestion-systeme/modeles',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'MODELE', 'VISUALISER_MODELE')
        },
        {
          libelle: 'Offre',
          route: '/gestion-systeme/offre',
          hidden: !this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'OFFRES', 'VISUALISER_OFFRE')
        },
      ]
    },
    {
      libelle: 'OLT',
      // route: '/gestion-systeme/offre-olt',
      icone: 'system_security_update_warning',
      hidden: !this.userService.checkModuleExiste('GESTION_OLT'),
      open: false,
      children: [
        {
          libelle: 'Offre-OLT',
          route: '/gestion-systeme/offre-olt',
          hidden: !this.userService.checkMenuExiste('GESTION_OLT', 'OFFRES_OLT', 'VISUALISER_OFFRE_OLT')
        },
      ]
    },
    {
      libelle: 'Radius',
      route: '/radius/type-attributs',
      icone: 'system_security_update_warning',
      hidden: !this.userService.checkModuleExiste('RADIUS_FTTH'),
      open: false,
      children: [
        {
          libelle: 'Type-attributs',
          route: '/radius/type-attributs',
          hidden: !this.userService.checkMenuExiste('RADIUS_FTTH', 'TYPE_ATTRIBUT', 'VISUALISER_TYPE_ATTRIBUT')
        },
        {
          libelle: 'Attributs',
          route: '/radius/attributs',
          hidden: !this.userService.checkMenuExiste('RADIUS_FTTH', 'ATTRIBUT', 'VISUALISER_ATTRIBUT')
        },
        {
          libelle: 'Groupe-radius',
          route: '/radius/groupe-radius',
          hidden: !this.userService.checkMenuExiste('RADIUS_FTTH', 'GROUPE_RADIUS', 'VISUALISER_GROUPE_RADIUS')
        },
        {
          libelle: 'Provisionning',
          route: '/radius/provisionning',
          hidden: !this.userService.checkMenuExiste('RADIUS_FTTH', 'PROVISIONNING', 'VISUALISER_PROVISIONNING')
        },
      ]
    },
    {
      libelle: 'Administration',
      icone: 'system_security_update_warning',
      hidden: !this.userService.checkModuleExiste('ADMINISTRATION'),
      open: false,
      children: [
        {
          libelle: 'Utilisateurs',
          route: '/administration/utilisateurs',
          hidden: !this.userService.checkMenuExiste('ADMINISTRATION', 'UTILISATEUR', 'VISUALISER_UTILISATEUR')
        },
        {
          libelle: 'Roles',
          route: '/administration/roles',
          hidden: !this.userService.checkMenuExiste('ADMINISTRATION', 'ROLE', 'VISUALISER_ROLE')
        },
        {
          libelle: 'Actions',
          route: '/administration/actions',
          hidden: !this.userService.checkMenuExiste('ADMINISTRATION', 'ACTION', 'VISUALISER_ACTION')
        },
        {
          libelle: 'Menus',
          route: '/administration/menus',
          hidden: !this.userService.checkMenuExiste('ADMINISTRATION', 'MENU', 'VISUALISER_MENU')
        },
        {
          libelle: 'Modules',
          route: '/administration/modules',
          hidden: !this.userService.checkMenuExiste('ADMINISTRATION', 'MODULES', 'VISUALISER_MODULE')
        },
        {
          libelle: 'Comptes',
          route: '/administration/comptes',
          hidden: !this.userService.checkMenuExiste('ADMINISTRATION', 'COMPTES', 'VISUALISER_COMPTE')
        },
      ]
    },
    {
      libelle: 'Dashboard',
      icone:'query_stats',
      hidden: false,
      open: false,
      children: [
        {
          libelle: 'Reporting',
          route: 'dashboard/reporting',
          // hidden: !this.userService.checkMenuExiste('ADMINISTRATION', 'REPORTING', 'VISUALISER_DASHBOARD')
          hidden: false
        }
      ]
    }

  ]
  public items: Array<string> = [];
  private sub: any;
  activeMenu: string;
  userInfo: any;
  currentSubMenuLibelle = ''
  currentMenuLibelle = 'Infrastructure physique'

  constructor(
    private breakpointObserver: BreakpointObserver,
    private router: Router,
    private activedRoute: ActivatedRoute,
    private userService: UserService
    // private sidenav: MatSidenav
  ) {
    console.log('splited url', this.router.url.split('/'));
    console.log('active menu', this.router.url.split('/')[1]);
    let splitedUrl = this.router.url.split('/')
    let position = 2
    if (splitedUrl.includes('gestion-ressources') || splitedUrl.includes('parametres') || splitedUrl.includes('parametres-generaux')) {
      position = 1
    }
    this.currentSubMenuLibelle = this.router.url.split('/')[position].normalize("NFD").replace(/[\u0300-\u036f]/g, "").toUpperCase()

    let parent = this.menuItems.map((element) => {
      return { ...element, subElements: element.children.filter((subElement) => subElement.libelle.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toUpperCase() === this.currentSubMenuLibelle) }
      // return element.children.filter((subElement) => subElement.libelle === 'Roles')
    })
    parent = parent.filter((p: any) => p.subElements.length)
    console.log('parent', parent);
    this.currentMenuLibelle = parent[0] ? parent[0].libelle : ''
    this.menuItems.filter((it: any) => it.libelle === this.currentMenuLibelle)[0].open = true
    this.userInfo = this.userService.getUser()
    const data = this.activedRoute.data.pipe(map(d => d));
    this.sub = this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((data) => {
      this.items = [];
      // console.log('filter router', this.router)
      this.extract(this.router.routerState.root)
    })
  }

  extract(route: any) {
    let title = route.data.value['title'];
    if (title && this.items.indexOf(title) == -1) {
      this.items.push(route.data.value['title']);

    }
    if (route.children) {
      route.children.forEach((it: any) => {
        this.extract(it)
      })
    }
    // console.log('push ', this.items.join(' / '))
  }

  ngOnInit(): void {
    // console.log('route title', this.activedRoute.snapshot.data['title'])
    // this.activedRoute.data
    // .subscribe((data: any) => {
    //   console.log('data', data);
    // });

  }
  controlMenu(menu: any) {
    let activeChildren = menu.children.length ? menu.children.filter((mc: any) => !mc.hidden).length : 0
    return !menu.hidden && activeChildren > 0
  }
  askBeforeAction() {
    Swal.fire({
      title: '<div style="color:#fff">Attention!</div>',
      html: '<div style="color:#fff">Vous êtes sur le point de vous déconnecter. Continuer?</div>',
      showCancelButton: true,
      confirmButtonColor: '#dc3545',
      confirmButtonText: `Se déconnecter`,
      cancelButtonText: `Annuler`,
      background: '#000',

    }).then((result: any) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        // Swal.fire('Saved!', '', 'success')
        this.logout();
      } else if (result.isDenied) {
        // Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }
  logout() {
    window.localStorage.removeItem('userData');
    this.router.navigate(['./auth']);
  }

  onSelectMainMenu(item: any) {
    // this.menuItems.map(
    //   (mi)=>mi.open = false
    // )
    this.menuItems.filter(
      (mi) => mi != item
    ).map(
      (mi) => mi.open = false
    )
    item.open = item.open ? false : true
    // console.log('menu selected',item.libelle);
    this.currentMenuLibelle = item.libelle

  }
  onSelectSubMenu(itemLibelle: any) {
    this.currentSubMenuLibelle = itemLibelle.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toUpperCase()
    console.log('submenu selected', this.currentSubMenuLibelle);

  }
  lowerCase(data: any) {
    // console.log('data lowercased',data.toLowerCase());

    return data.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toUpperCase()
  }
}
