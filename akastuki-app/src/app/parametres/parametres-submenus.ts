import { UserService } from '../shared/services/user.service';
import { Injectable } from '@angular/core';
@Injectable()
export class SubMenus {
    param1: string;
    listMenu = [
        {
        key:'quartiers',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES','VISUALISER_QUARTIER')
      },{
        key:'plaques',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES','VISUALISER_PLAQUE')
      },{
        key:'plaque-quartier',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES','VISUALISER_PLAQUE_QUARTIER')
      },{
          key:'villes',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES','VISUALISER_VILLE')
      },{
        key:'sites',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES','VISUALISER_SITE')
      },{
        key:'arrondissements',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES','VISUALISER_ARRONDISSEMENT')
      }
    ]
    constructor(private userService:UserService){
       
    }
}