import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommunicationService } from '../shared/services/communication.service';
import { SubMenus } from './parametres-generaux-submenus';
import { UserService } from '../shared/services/user.service';

@Component({
  selector: 'app-parametres-generaux',
  templateUrl: './parametres-generaux.component.html',
  styleUrls: ['./parametres-generaux.component.scss','../../sub-menu.css'],
  providers:[SubMenus]
})
export class ParametresGenerauxComponent implements OnInit {
  activeMenu :any
  listMenu : any[]
  constructor(private router:Router, private communicationService:CommunicationService,private subMenus:SubMenus,private userService:UserService) { 
  this.activeMenu = this.router.url.split('/')[this.router.url.split('/').length-1]
  console.log('full url in pg',this.router.url);
  
  console.log('default menu',this.activeMenu);

  this.communicationService.getData().subscribe(
    res=>{
      
      console.log('this.router.url',this.router.url.split('/').length);
      if(this.router.url.split('/').length>3)
        this.activeMenu = res.items
      console.log('current main',this.activeMenu);

    }

)

  this.listMenu = subMenus.listMenu
  console.log('list submenus of parametres generaux',this.listMenu);
  let listActiveMenus = this.listMenu.filter((lm:any)=>!lm.hidden)

  
  
  if(subMenus.listMenu.filter((lm:any)=>!lm.hidden)[0]){
    this.activeMenu = listActiveMenus[0]?listActiveMenus[0].key:''
    this.onSelectMenu(this.activeMenu)
    let mainMenu = this.router.url.split('/')[this.router.url.split('/').length-2]

    console.log('mainMenu',mainMenu);
    console.log('this.activeMenu',this.activeMenu);
    
    
    // debugger
    this.router.navigate([mainMenu+'/'+this.activeMenu.toLowerCase()])
    }
    else{
      console.log('forworded to login page');
      
    this.logout()
    }
  


  }
  logout(){
    window.localStorage.removeItem('userData');
    this.router.navigate(['./auth']);
  }

  
  ngOnInit(): void {
  }
  


  onSelectMenu(menu:any){
    this.activeMenu = menu
    console.log('router.url',this.activeMenu);
    // if(menu == 'distributions') menu = 'parametres-generaux/distribution'
    // let mainMenu = this.router.url.split('/')[this.router.url.split('/').length-2]
    // console.log('mainMenu',mainMenu);
    
    
  }


}
