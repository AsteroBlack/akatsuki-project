import { Injectable, isDevMode } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Observable, throwError, Subject } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import {environment} from '../../../environments/environment';
import { UserService } from './user.service';
// import { catchError } from 'rxjs/operators';
// import 'rxjs/add/operator/timeout';
// import 'rxjs/add/operator/catch';
// import 'rxjs/add/observable/throw';
// import 'rxjs/add/operator/publishReplay';
// import { Subject } from 'rxjs/Rx';
@Injectable({
  providedIn: 'root'
})
export class RestApiClientService {
  baseUrl: string = environment.baseUrl;
  baseUrlRessources: string = environment.baseUrlRessources;
  baseUrlRadius: string = environment.baseUrlRadius;
  baseUrlEndPRadius: string = environment.baseUrlEndPRadius;
  baseUrlIpManager: string = environment.baseUrlIpManager;
  baseUrlDashboard: string = environment.baseUrlDashboard;
  baseUrlDashboardCommande: string = environment.baseUrlDashboardCommande;
  constructor(
      private http: HttpClient,
      private user: UserService
    ) {
  }

  connexion(action: string, data: any, ignore?: any): Observable<any>  {
    // console.log('connexion')
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    header = header.append('isAuth', 'true');
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrl + action, {data, isConnexion: true}, {headers: header})
    .pipe(catchError(this.handleError));
  }

  executeRadius(action: string, data: any, ignore?: any): Observable<any> {
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrlRadius + action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  executeIpManager(action: string, data: any, ignore?: any): Observable<any> {
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrlIpManager + action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  getIpManager(action: string, ignore?: any): Observable<any> {
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.get(this.baseUrlIpManager + action, {headers: header})
    .pipe(catchError(this.handleError));
  }

  getAddressInfosByNd(action: string, nd: string){
    const data = {
      data: {nd}
    }
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrlIpManager + action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  getSystem(action: string, ignore?: any): Observable<any> {
    return this.http.get(this.baseUrlEndPRadius + action)
    .pipe(catchError(this.handleError));
  }

  getRadius(action: string, ignore?: any): Observable<any> {
    return this.http.get(this.baseUrlRadius + action)
    .pipe(catchError(this.handleError));
  }

  executeRessources(action: string, data: any, ignore?: any): Observable<any> {
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrlRessources + action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  executeSystem(action: string, data: any, ignore?: any): Observable<any> {
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrlEndPRadius + action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  execute(action: string, data: any, ignore?: any): Observable<any> {
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrl + action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  executeRadiusFtth(action: string, data: any, ignore?: any): Observable<any>{
    console.log('passing here')
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrlEndPRadius + action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  executeDashboard<T>(action: string, data: any, ignore?: boolean){
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post<T>(this.baseUrlDashboard + action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  executeDashboardCommande<T>(action: string, data: any, ignore?: boolean){
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post<T>(this.baseUrlDashboardCommande + action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  executeDirect(action: string, data: any, ignore?: any): Observable<any> {
    // console.log('execute function', this.user.getUser().id);
    let header: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (ignore) {
      header = header.append('ignoreLoadingBar', '');
    }
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(action, data, {headers: header})
    .pipe(catchError(this.handleError));
  }

  executeWithFileRadius(action: string, data: any): Observable<any> {

    // let header: HttpHeaders = new HttpHeaders();
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrlEndPRadius + action, data, {
      responseType: 'arraybuffer',
    })
    .pipe(catchError(this.handleError));
  }

  executeWithFile(action: string, data: any): Observable<any> {

    let header: HttpHeaders = new HttpHeaders();
    // header = header.append('userId', this.user.getUser() ? this.user.getUser().id : '1');
    return this.http.post(this.baseUrlRadius + action, data, {
        headers: header,
       responseType: 'arraybuffer',
    })
    .pipe(catchError(this.handleError));
  }

  executeFormData(action: string, data: any, ignore?: any): Observable<any>  {
      let headers: HttpHeaders = new HttpHeaders();
      // console.log('multipart/form-data');
      // headers = headers.append('Content-Type', 'multipart/form-data');
      // headers = headers.append('Access-Control-Allow-Origin', '*');

    return this.http.post(this.baseUrl + action, data, {headers: headers}
      // {
      //   headers: { formData: 'true'},
      //   reportProgress: true,
      //   observe: 'events'
      // }
      ).pipe(catchError(this.handleError));
  }

  executeFormDataResssources(action: string, data: any, ignore?: any): Observable<any>  {
      let headers: HttpHeaders = new HttpHeaders();
      // console.log('multipart/form-data');
      // headers = headers.append('Content-Type', 'multipart/form-data');
      // headers = headers.append('Access-Control-Allow-Origin', '*');

    return this.http.post(this.baseUrlRessources + action, data, {headers: headers}
      // {
      //   headers: { formData: 'true'},
      //   reportProgress: true,
      //   observe: 'events'
      // }
      ).pipe(catchError(this.handleError));
  }

  getPublicKey(action: string, param: any) {
    console.log('param', param);
    let header: HttpHeaders = new HttpHeaders();
    // header = header.append('user', param.user);
    // header = header.append('sessionUser', param.sessionUser);
    // header = header.append('customerCode', param.customerCode);
    // header = header.append('ndCode', param.ndCode);
    header = header.append('serviceLibelle', param.serviceLibelle);
    // return this.http.get(this.baseUrl + this.backendFolder + action).catch(this.handleError)
    // return this.http.get(this.baseUrl +  action, {headers: header}).catch(this.handleError)
    return this.http.get(this.baseUrl + action, {headers: header}).pipe(catchError(this.handleError));
}

  request(url: string, data: any): Observable<any>  {
    return this.http.post(url, data).pipe(catchError(this.handleError));
  }


  requestGet(url: string, param?: any): Observable<any>  {
    // const header = new HttpHeaders({
    //   user: param.id,
    //   serviceLibelle: param.serviceLibelle
    // });
    return this.http.get(url).pipe(catchError(this.handleError));
    // return this.http.get(url, {headers: header}).catch(this.handleError);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error}, ` +
        `body was: ${error}`);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }
}
