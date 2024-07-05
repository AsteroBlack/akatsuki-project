import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './shared/services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'akatsuki';
  constructor(
    private userService: UserService,
    private router: Router
    ){
    if (!this.userService.getUser() || this.userService.getUser() && !this.userService.getUser().id) {
      console.log('app.component')
      this.router.navigate(['/auth']);
    }
  }
}
