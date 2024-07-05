import { UserService } from '../shared/services/user.service';
import { Injectable } from '@angular/core';
@Injectable()
export class SubMenus {
    param1: string;
    listMenu = [
        {
        key:'couleurs',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_COULEUR')
      },{
        key:'distributions',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_DISTRIBUTION')
      },{
        key:'transports',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_TRANSPORT')
      },{
          key:'gestion-olt',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_GESTION_OLT')
      },{
        key:'gestion-demandes',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_GESTION_DEMANDE')
      },{
        key:'architectures',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_ARCHITECTURE')
      }
    ]
    constructor(private userService:UserService){
       
    }
}