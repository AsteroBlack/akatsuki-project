import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SubMenus } from './demandes-submenus';

@Component({
  selector: 'app-demandes',
  templateUrl: './demandes.component.html',
  styleUrls: ['./demandes.component.scss','../../../sub-menu.css'],
  providers:[SubMenus],
})
export class DemandesComponent implements OnInit {
  activeMenu: any;
  listMenu : any[]
  listActiveMenus: any[]
  constructor(private router:Router,private subMenus:SubMenus) {
    this.activeMenu = this.router.url.split('/')[this.router.url.split('/').length-1]


    this.listMenu = subMenus.listMenu
    console.log('list submenus',this.listMenu);
    this.listActiveMenus = this.listMenu .filter((lm:any)=>!lm.hidden)
  if(!this.listActiveMenus.find((lam:any)=>lam.key ===this.activeMenu)){
    this.activeMenu = this.listActiveMenus[0]?this.listActiveMenus[0].key:''
    let mainMenu =  this.router.url.split('/')[this.router.url.split('/').length-2]
    this.router.navigate(['parametres-generaux/'+mainMenu+'/'+this.activeMenu.toLowerCase()])
  }
  console.log('listActiveMenus',this.listActiveMenus);
  
    
   }

  ngOnInit(): void {
  }

  onSelectMenu(menu:any){
    this.activeMenu = menu
  }
}
