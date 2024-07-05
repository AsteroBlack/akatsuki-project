import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from '../shared/services/rest-api-client.service';
import { UserService } from '../shared/services/user.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {
  isLoading: boolean = false;
  authForm: FormGroup;

  constructor(
    private router: Router,
    private restApiClient: RestApiClientService,
    private toastService: ToastrService,
    private userService: UserService,
  ) {
    this.authForm = new FormGroup({
      login: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }

  connexion(param?: any) {
    this.isLoading = true;
    const methode = 'user/connexion'
    const data = {
      data: this.authForm.value
    };

    console.log('data login', JSON.stringify(data));
    
    this.restApiClient.connexion(methode, data).subscribe((res: any) => {
      console.log('res of authentication', res);
      this.isLoading = false;
      if (res && !res.hasError) {
        this.userService.setUserData('userData', res.items[0]);

        // Redirection en fonction des permissions
        if (this.userService.canConnect()) {
          // Insérez ici votre logique de redirection vers la page 'success.html'
          this.router.navigate(['./success.html']);
        } else {
          this.toastService.error("L'utilisateur ne dispose pas d'assez d'autorisations", "Impossible de se connecter");
        }
      } else {
        this.toastService.error('Opération échouée', res.status.message);
      }
    }, (err) => {
      this.isLoading = false;
      this.toastService.error('Erreur', 'Problème de communication');
    });
  }
}

