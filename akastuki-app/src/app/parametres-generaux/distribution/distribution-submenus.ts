import { UserService } from '../../shared/services/user.service';
import { Injectable } from '@angular/core';
@Injectable()
export class SubMenus {
    param1: string;
    listMenu = [
        {
        key:'cassettes',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_CASSETE')
      },{
        key:'pb',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_PB')
      },{
        key:'tiroir-distrib',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_TIROIR_DISTRIB')
      },{
          key:'coupleurs',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_COUPLEUR')
      },{
        key:'type-site-pb',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_TYPE_SITE_PB')
      }
    ]
    constructor(private userService:UserService){
       
    }
}