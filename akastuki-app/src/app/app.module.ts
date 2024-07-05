import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { AuthComponent } from './auth/auth.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { LoadingBarHttpClientModule } from '@ngx-loading-bar/http-client';
import { ToastNoAnimationModule, ToastrModule, ToastrService } from 'ngx-toastr';
import { LaddaModule } from 'angular2-ladda';
import { LoadingBarModule, LOADING_BAR_CONFIG } from '@ngx-loading-bar/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { IntercepteurHttpInterceptor } from './shared/services/intercepteur-http.interceptor';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { GestionRessourcesModule } from './gestion-ressources/gestion-ressources.module';
import {MatCardModule} from '@angular/material/card';
import { IpManagerModule } from './ip-manager/ip-manager.module';
// import { DetailAccountingComponent } from './src/app/radius/provisionning/detail-accounting/detail-accounting.component';


@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    // DetailAccountingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LoadingBarHttpClientModule,
    HttpClientModule,
    // LoadingBarModule,
    SharedModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatDialogModule,
    MatCardModule,
    ToastNoAnimationModule.forRoot(),
    ToastrModule.forRoot({
      timeOut: 10000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
    ReactiveFormsModule,
    FormsModule,
    // BsDatepickerModule.forRoot(),
    LaddaModule.forRoot({
      style: 'expand-right',
      spinnerSize: 35,
      spinnerColor: 'white',
      spinnerLines: 12
    }),
    MatProgressBarModule,
    GestionRessourcesModule,
   
    MatProgressBarModule,
    IpManagerModule
  ],
  providers: [
    // IntercepteurHttpInterceptor,
    {
      provide: HTTP_INTERCEPTORS, 
      useClass: IntercepteurHttpInterceptor, 
      multi: true
    },
    // { provide: LOADING_BAR_CONFIG, useValue: { latencyThreshold: 100 } }
    // { provide: ToastrService, useValue: ToastrService }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
