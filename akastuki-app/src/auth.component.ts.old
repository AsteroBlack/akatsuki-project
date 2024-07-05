import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../shared/services/rest-api-client.service';
import { UserService } from '../shared/services/user.service';
import { CommunicationService } from '../shared/services/communication.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {
  isLoading: boolean = false;
  authForm: FormGroup;
  constructor(
    private router: Router,
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
  ) {

    this.authForm = new FormGroup({
      login: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }
  
  connexion(param?: any) {
    this.isLoading = true;
    const methode = 'user/connexion'
    //const data = ""
    const data = {
      data: this.authForm.value
    };
    
    console.log('data login', JSON.stringify(data));
    // this.router.navigate(['./gestion-ressources'])
    // return;
    this.restApiClient.connexion(methode, data).subscribe((res: any) => {
      console.log('res of authentication', res);
      this.isLoading = false;
      if (res && !res.hasError) {
        this.userService.setUserData('userData', res.items[0]);
        // this.toastService.success('Opération réussie', res.status.message);
        if (this.userService.canConnect()) {

          if (this.userService.checkModuleExiste('INFRASTRUCTURE_PHYSIQUE')) {

            if (this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'GESTION_DES_RESSOURCES', 'VISUALISER_RESSOURCE')) {
              this.router.navigate(['./gestion-ressources'])
              return
            }
            else if (this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'PARAMETRES', 'VISUALISER_PARAMETRES')) {
              this.router.navigate(['./parametres'])
              return
            }
            else if (this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'PARAMETRES_GENERAUX', 'VISUALISER_PARAMETRES_GENERAUX')) {
              this.router.navigate(['./parametres-generaux'])
              return
            }
            /* else {
              this.toastService.error(`impossible d'accéder à la plateforme.
              Fonctionnalité manquante:
              visualiser ressource ou  visualiser paramètres ou visualiser parametres généraux`, `Opération échouée`);
              return
            } */

          }
          if (this.userService.checkModuleExiste('GESTION_DES_IP')) {

            if (this.userService.checkMenuExiste('GESTION_DES_IP', 'IP', 'VISUALISER_IP')) {
              this.router.navigate(['./ip-manager/ip'])
              return
            }
            else if (this.userService.checkMenuExiste('GESTION_DES_IP', 'TYPE_CLIENT', 'VISUALISER_TYPE_CLIENT')) {
              this.router.navigate(['./ip-manager/type-client'])
              return
            }
            else if (this.userService.checkMenuExiste('GESTION_DES_IP', 'LOCALISATION', 'VISUALISER_LOCALISATION')) {
              this.router.navigate(['./ip-manager/localisation'])
              return
            }
            else if (this.userService.checkMenuExiste('GESTION_DES_IP', 'TECHNOLOGIE', 'VISUALISER_TECHNOLOGIE')) {
              this.router.navigate(['./ip-manager/technologie'])
              return
            }
            else if (this.userService.checkMenuExiste('GESTION_DES_IP', 'ZONE', 'VISUALISER_ZONE')) {
              this.router.navigate(['./ip-manager/zone'])
              return
            }
            else if (this.userService.checkMenuExiste('GESTION_DES_IP', 'SEUIL_ALERTE', 'VISUALISER_SEUIL_ALERTE')) {
              this.router.navigate(['./ip-manager/seuil-alerte'])
              return
            }
            else if (this.userService.checkMenuExiste('GESTION_DES_IP', 'PERSONNE_NOTIF', 'VISUALISER_PERSONNE_NOTIF')) {
              this.router.navigate(['./ip-manager/personne-notif'])
              return
            }

            /* else {
              this.toastService.error('Opération échouée', `impossible d'accéder à la plateforme.
                Fonctionnalité manquante:
                visualiser ip ou
                visualiser type client ou
                visualiser localisation ou
                visualiser technologie ou
                visualiser zone ou
                visualiser seuil alerte ou
                visualiser personne notif

                `
              );
              return
            }*/
          }

          if (this.userService.checkModuleExiste('GESTION_DES_OFFRES')) {

            if (this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'TYPE_OFFRE', 'VISUALISER_TYPE_OFFRE')) {
              this.router.navigate(['./gestion-systeme/type-offre'])
              return
            }
            else if (this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'MODELE', 'VISUALISER_MODELE')) {
              this.router.navigate(['./gestion-systeme/modeles'])
              return
            }
            else if (this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'OFFRES', 'VISUALISER_OFFRES')) {
              this.router.navigate(['./gestion-systeme/offre'])
              return
            }

            /* else {
              this.toastService.error('Opération échouée', `impossible d'accéder à la plateforme.
                Fonctionnalité manquante:
                visualiser type offre ou
                visualiser modèle ou
                visualiser offres
                `
              );
              return
            } */

          }

          if (this.userService.checkModuleExiste('RADIUS_FTTH')) {

            if (this.userService.checkMenuExiste('RADIUS_FTTH', 'TYPE_ATTRIBUT', 'VISUALISER_TYPE_ATTRIBUT')) {
              this.router.navigate(['./radius/type-attributs'])
              return
            }
            else if (this.userService.checkMenuExiste('RADIUS_FTTH', 'ATTRIBUT', 'VISUALISER_ATTRIBUT')) {
              this.router.navigate(['./radius/attributs'])
              return
            }
            else if (this.userService.checkMenuExiste('RADIUS_FTTH', 'GROUPE_RADIUS', 'VISUALISER_GROUPE_RADIUS')) {
              this.router.navigate(['./radius/groupe-radius'])
              return
            }
            else if (this.userService.checkMenuExiste('RADIUS_FTTH', 'PROVISIONNING', 'VISUALISER_PROVISIONNING')) {
              this.router.navigate(['./radius/provisionning'])
              return
            }

          }
          if (this.userService.checkModuleExiste('ADMINISTRATION')) {

            if (this.userService.checkMenuExiste('ADMINISTRATION', 'UTILISATEUR', 'VISUALISER_UTILISATEUR')) {
              this.router.navigate(['./administration/utilisateurs'])
              return
            }
            else if (this.userService.checkMenuExiste('ADMINISTRATION', 'ROLE', 'VISUALISER_ROLE')) {
              this.router.navigate(['./administration/roles'])
              return
            }
            else if (this.userService.checkMenuExiste('ADMINISTRATION', 'ACTION', 'VISUALISER_ACTION')) {
              this.router.navigate(['./administration/actions'])
              return
            }
            else if (this.userService.checkMenuExiste('ADMINISTRATION', 'MENU', 'VISUALISER_MENU')) {
              this.router.navigate(['./administration/menus'])
              return
            }
            else if (this.userService.checkMenuExiste('ADMINISTRATION', 'MODULES', 'VISUALISER_MODULES')) {
              this.router.navigate(['./administration/modules'])
              return
            }
            else if (this.userService.checkMenuExiste('ADMINISTRATION', 'REPORTING', 'VISUALISER_DASHBOARD')) {
              this.router.navigate(['./dashboard'])
              return
            }
            /* else {
              this.toastService.error('Opération échouée', `impossible d'accéder à la plateforme.
                Fonctionnalité manquante:
                visualiser utilisateur ou
                visualiser role ou
                visualiser action ou
                visualiser menu ou
                visualiser module
                `
              );
              return
            } */


          }
        }
        else{
          this.toastService.error("L'utilisateur ne dispose pas d'assez d'autorisations", "Impossible de se connecter")
        }
	} else {
	//	this.router.navigate(['./dashboard'])
	//    return

      this.toastService.error('Opération échouée', res.status.message);
        // this.listeUser = [];
      }
    },
    (err) => {
      this.isLoading = false;
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

}
