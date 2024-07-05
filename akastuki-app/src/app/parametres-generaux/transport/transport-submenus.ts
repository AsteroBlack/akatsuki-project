import { UserService } from '../../shared/services/user.service';
import { Injectable } from '@angular/core';
@Injectable()
export class SubMenus {
    param1: string;
    listMenu = [
        {
        key:'tiroirs',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_TIROIR')
      },{
        key:'clusters',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_CLUSTER')
      },{
        key:'pd-type',hidden:!this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE','PARAMETRES_GENERAUX','VISUALISER_PD_TYPE')
      }
    ]
    constructor(private userService:UserService){
       
    }
}