import { UserService } from '../../shared/services/user.service';
import { Injectable } from '@angular/core';
@Injectable()
export class SubMenus {
    param1: string;
    listMenu = [
        {
        key:'demande',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_DEMANDE')
      },{
        key:'type-client',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_TYPE_CLIENT_DEMANDE')
      },{
        key:'etat-demande',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_ETAT_DEMANDE')
      },{
          key:'offres',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_OFFRE_DEMANDE')
      },{
        key:'type-formule',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_TYPE_FORMULE')
      }
    ]
    constructor(private userService:UserService){
       
    }
}