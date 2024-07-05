import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CommunicationService {
  public subject = new Subject<any>();
  public data = new Subject<any>();
  constructor() { }
  clearRequest() {
    this.subject.next();
}

sendData(req: any){
    console.log('data', req);
    this.data.next({items: req });
}
getData(): Observable<any> {
  return this.data.asObservable();
}

// sendRequest(requestItem: any) {
//     this.subject.next({items: requestItem });
// }

// getRequest(): Observable<any> {
//     return this.subject.asObservable();
// }

// deSouscrire() {
//     this.subject.unsubscribe();
// }
}
