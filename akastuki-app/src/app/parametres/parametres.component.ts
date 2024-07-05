import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { UserService } from '../shared/services/user.service';
import { SubMenus } from './parametres-submenus';

@Component({
  selector: 'app-parametres',
  templateUrl: './parametres.component.html',
  styleUrls: ['./parametres.component.scss','../../sub-menu.css'],
  providers:[SubMenus]
})
export class ParametresComponent implements OnInit {
  activeMenu :any
  currentMenu:any;
  listMenu:any[]
  firstActiveMenu: any;
  constructor(private router:Router,private userService:UserService,private subMenus:SubMenus) { 
  this.activeMenu = this.router.url.split('/')[this.router.url.split('/').length-1]
  console.log('default active menu',this.activeMenu);


  this.listMenu = subMenus.listMenu
  console.log('list submenus',this.listMenu);
  let listActiveMenus = this.listMenu .filter((lm:any)=>!lm.hidden)
  if(!listActiveMenus.find((lam:any)=>lam.key ===this.activeMenu)){
    this.activeMenu = listActiveMenus[0]?listActiveMenus[0].key:''
    console.log('new default active menu',this.activeMenu);
  this.onSelectMenu(this.activeMenu)

  }
  else{
  // this.logout()
  }
  // this.activeMenu = listActiveMenus[0].key
  
  

  }

  ngOnInit(): void {
  }

  logout(){
    window.localStorage.removeItem('userData');
    this.router.navigate(['./auth']);
  }
  


  onSelectMenu(menu:any){
   
    this.activeMenu = menu;
    let mainMenu = this.router.url.split('/')[this.router.url.split('/').length-2]
    console.log('route', mainMenu+'/'+menu.toLowerCase());
    this.router.navigate([mainMenu+'/'+menu.toLowerCase()])
  }
}
