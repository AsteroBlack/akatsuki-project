import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from './user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class IntercepteurHttpInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private activedRoute: ActivatedRoute,
    private userService: UserService,
    private toastService: ToastrService,
  ) {
    console.log('interceptor', this.userService.getUser().id)
    this.checkUser();
  }

  checkUser(){
    if(!this.userService.getUser()){
      this.toastService.error('Attention', 'Veuillez-vous connecter à nouveau!');
      window.localStorage.removeItem('userData');
      this.router.navigate(['./auth']);
    }
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<any>> {
    let headers: HttpHeaders = new HttpHeaders();
    // const token = this.userService.getUser() ? this.userService.getUser().token : null;
    // const req = {
    //   user: 1118,
    //   datas: request.body.datas
    // };
    // console.log('interceptor')
    this.checkUser();
    const bodyRequest: any = request.body
    if (request.method === 'POST' && request.body && !bodyRequest.isConnexion) {
      console.log('req', request.body)
      if (request.body instanceof FormData) {
        console.log('multipart/form-data');
        headers = headers.append('Content-Type', 'multipart/form-data');
        let body = request.body as FormData
        request =  request.clone({
           setHeaders: {
              // 'Content-Type' : 'multipart/form-data',
              // 'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
           },
          // body: {...request.body, user: this.userService.getUser().id}
          body: body.append('user', this.userService.getUser().id)
          });
      } else {
        request = request.clone({
          headers,
          method: request.method,
          body: {...bodyRequest, user: this.userService.getUser().id}
        });
      }
    }
    else if(request.method === 'POST' && request.body && bodyRequest.isConnexion) {

      request = request.clone({
        headers,
        method: request.method,
        body: {...bodyRequest.data, user: this.userService.getUser().id}
      });
    }
    return next.handle(request);
  }
}
