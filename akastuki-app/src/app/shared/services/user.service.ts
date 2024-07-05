import { Injectable, TemplateRef } from '@angular/core';

import * as _ from 'lodash';
import { Router, ActivatedRoute, ChildActivationEnd } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
// import { TabService } from './tab.service';
// import { CommunicationService } from './communication.service';
// import * as CryptoJS from 'crypto-js';
// import * as SecureLS from 'secure-ls';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  listeMenu: any = [];
  encryptData: any;
  decryptData: any;
  byteData: any;
  keyCode: any;
  iv: any;
  ls: any;
  requestDb: any;
  secureStorage: any;
  jsonFormatter: any;
  countCall = 0;
  userGetData: any;
  codeSecret = 'khqWPuvI+431q/0Ev1wVhzG3Po8Z0UIBlvM/fm6uGW0=';
  constructor(
    private router: Router,
    private activedRoute: ActivatedRoute,
    private toastService: ToastrService,
    // private tabService: TabService,
    // private communication: CommunicationService
  ) {
    // this.ls = new SecureLS({encodingType: 'aes', isCompression: true});
    // console.log('ls', this.ls);
    this.countCall = 0;
    this.userGetData = this.getUser();
    // console.log('userget', this.userGetData);
    // this.keyCode = CryptoJS.lib.WordArray.random(16).toString();
    // this.iv  = CryptoJS.enc.Base64.parse(this.codeSecret);
    // const iv  = CryptoJS.lib.WordArray.random(16);
    // const encrypted = CryptoJS.AES.encrypt('Message',  this.keyCode, {iv});
    // const decrypted = CryptoJS.AES.decrypt(encrypted,  this.keyCode, {iv});

    this.router.events.subscribe(event => {
      // console.log('route change');

    });
  }
  toasts: any[] = [];



  reverseString(texteValue: any) {
    return texteValue.split('').reverse().join('');
  }

  // Push new Toasts to array with content and options
  setUserData(storageName: string, data: any) {
    // this.iv  = CryptoJS.enc.Base64.parse(this.codeSecret);
    // this.keyCode = CryptoJS.lib.WordArray.random(16).toString();
    // window.localStorage.setItem('@@@1V', this.iv); // stockage de iv
    // window.localStorage.setItem('@@@1de©', this.keyCode); // stockage de keyCode
    // this.encryptData = CryptoJS.AES.encrypt(JSON.stringify(data), this.keyCode, {iv: this.iv});
    // // console.log('encrypt', this.encryptData);
    // window.localStorage.setItem(storageName ? storageName : 'userData', this.encryptData);
    window.localStorage.setItem(storageName ? storageName : 'userData', JSON.stringify(data));
    this.userGetData = this.getUser()
  }

  getOnly() {
    return window.localStorage.getItem('userData');
  }
  getUserActions() {

  }

  // Callback method to remove Toast DOM element from view
  getUser(storageName?: any): any {

    // let storageData;
    // this.encryptData =  window.localStorage.getItem(storageName ? storageName : 'userData');
    // this.iv = window.localStorage.getItem('@@@1V'); // recuperation de iv
    // this.keyCode = window.localStorage.getItem('@@@1de©'); // recuperation de keyCode

    // if(this.encryptData){
    //   storageData = CryptoJS.AES.decrypt(this.encryptData, this.keyCode, {iv: this.iv}).toString(CryptoJS.enc.Utf8);
    // }
    // console.log('get user', this.countCall++);
    // console.log('get user', (window.localStorage.getItem(storageName ? storageName : 'userData')));
    // return storageData ? JSON.parse(JSON.parse(storageData)) : null;
    return JSON.parse(window.localStorage.getItem(storageName ? storageName : 'userData') || '{}');
  }

  getId(): string {
    return this.getUser().id ?? '118'
  }

  getRoleUser() {
    // const roleData = JSON.parse(window.localStorage.getItem('userData')).datasRole;
  }



  getUserMenuExiste() {
    const menus: any[] = [];
    if (this.userGetData) {
      this.userGetData.datasModule.forEach((elt: any) => {
        // console.log('var role', elt);
        elt.datasMenus.forEach((res: any) => {
          // console.log('dar', res);
          menus.push(res);
        });
      });
    } else {
      // this.getUser().datasModule.forEach((elt: any) => {
      //     // console.log('role', elt);
      //     elt.datasMenus.forEach((res: any) => {
      //       menus.push(res);
      //     });
      // });
    }
    return menus;
  }

  checkModuleExiste(moduleCode: string): boolean {
    // return JSON.parse(window.localStorage.getItem('userData'))
    // .datasModule.findIndex((res) => res.moduleCode === moduleCode) > -1;

    if (this.userGetData.datasModule?.length > 0) {
      return !!this.userGetData.datasModule.find((res: any) => res.moduleCode === moduleCode);
    }
    else {
      return false
      //  return this.getUser().datasModule.find((res: any) => res.moduleCode === moduleCode);
    }

    // return JSON.parse(JSON.parse(window.localStorage.getItem('userData')))
    // .datasModule.find((res) => res.moduleCode === moduleCode);
  }
  checkMenuExiste(module: string, menu: string, action: string): boolean {
    if (this.userGetData.datasModule?.length > 0) {

      let moduleMatching = this.userGetData.datasModule.find((udm: any) => udm.moduleCode === module)

      if (moduleMatching?.datasMenus?.length > 0) {
        let menuMatching = moduleMatching.datasMenus.find((res: any) => res.menusCode === menu)
        if (menuMatching?.datasAction?.length > 0) {
          return !!menuMatching.datasAction.find((res: any) => res.code === action);
        }
      }
    }
    return false
    //  return this.getUser().datasModule.find((res: any) => res.moduleCode === moduleCode);
  }

  canConnect() {
    console.log('this.userGetData', this.userGetData);

    console.log('can connect', this.userGetData && this.userGetData.datasModule && this.userGetData.datasModule.length);
    if (this.userGetData)
      return !!this.userGetData.datasModule
    else
      return false
  }

  findUserAction(code: string) {
    // let listeReturn = [];
    // if(this.userGetData){
    //   // console.log('find module',this.userGetData.datasModule.findIndex((res) => res.moduleCode === moduleCode) > -1);
    //   // console.log('return', this.userGetData.datasRole.map((item)=> [...item.datasAction]).flat(1));
    //   listeReturn = this.userGetData.datasRole.map((item: any)=> [...item.datasAction]).flat(1)
    //   return listeReturn.findIndex((res: any) => res.code === code) > -1;
    // } else {
    //   // console.log('no find', this.getUser().datasModule.findIndex((res) => res.moduleCode === moduleCode) > -1);
    //   listeReturn = this.getUser().datasRole.map((item: any)=> [...item.datasAction]).flat(1)
    //   return listeReturn.findIndex((res: any) => res.code === code) > -1;
    // }

  }

  // checkMenuByModule(module: string) {
  //   const listeValue = this.getUser();
  //   const menus: any[] = [];
  //   listeValue.datasModule.forEach((elt: any) => {
  //     // console.log('elt lodume', elt);
  //     elt.datasMenus.forEach((res: any) => {
  //       menus.push(res);
  //     });

  //   });
  //   console.log('elt module', menus);
  //   // const listeValue = this.getUserMenuExiste();
  // //  const listeMenu =  this.getUserMenuExiste().find((res) => res.moduleCode === module);
  //   const listeMenu =  _.find(menus, {moduleCode: module});
  //   return listeMenu;
  // }

  removeUser(storageName: string) {
    window.localStorage.removeItem(storageName);
  }

}
