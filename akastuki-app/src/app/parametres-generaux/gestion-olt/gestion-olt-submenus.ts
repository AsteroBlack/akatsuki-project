import { UserService } from '../../shared/services/user.service';
import { Injectable } from '@angular/core';
@Injectable()
export class SubMenus {
    param1: string;
    listMenu = [
        {
        key:'olt',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_OLT')
      },{
        key:'modele-carte',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_MODELE_CARTE')
      },{
        key:'techno',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_TECHNO')
      },{
          key:'type-olt',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_TYPE_OLT')
      },{
        key:'nro',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_NRO')
      },{
        key:'type-carte',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_TYPE_CARTE')
      }
    ]
    constructor(private userService:UserService){
       
    }
}