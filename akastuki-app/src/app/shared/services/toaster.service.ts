import { Injectable, TemplateRef  } from '@angular/core';
 
@Injectable({
  providedIn: 'root'
})
export class ToastService {
 
  toasts: any[] = [];
 
  // Push new Toasts to array with content and options
  show(textOrTpl: string | TemplateRef<any>, options: any = {}) {
    this.toasts.push({ textOrTpl, ...options });
  }
 
  // Callback method to remove Toast DOM element from view
  remove(toast: any) {
    this.toasts = this.toasts.filter(t => t !== toast);
  }

  error(title: string, message: string, delay?: number){
      window.dispatchEvent(new Event('resize'));
      // console.log('error', title);
      this.show(message,{
        classname: 'bg-danger text-light error',
        delay: delay || 2000,
        autohide: true,
        headertext: title
      });
  }

  succes(title, message, delay?){
      console.log('succes');
      this.show(message,{
        classname: 'bg-success text-light succes',
        delay: delay || 2000,
        autohide: true,
        headertext: title
      });
  }

  
  alerte(title, message, delay?){
      this.show(message,{
        classname: 'bg-success text-light',
        delay: delay || 2000,
        autohide: true,
        headertext: title
      });
  }

  
}
