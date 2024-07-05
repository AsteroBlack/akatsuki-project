import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainViewComponent } from './main-view/main-view.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { SideNavComponent } from './side-nav/side-nav.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RestApiClientService } from './services/rest-api-client.service';
import { UserService } from './services/user.service';
import { ModalClientComponent } from '../ip-manager/client/modal-client/modal-client.component';
import { MatSelectModule } from '@angular/material/select';
// import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    MainViewComponent,
    SideNavComponent,

    ModalClientComponent, // ce composant sera appelé dans plusieurs module
  ],
  exports: [
    ModalClientComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
    MatSidenavModule,
    MatInputModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FlexLayoutModule,
    MatIconModule,
    MatSelectModule
  ],
  providers: [
    RestApiClientService,
    UserService
  ]
})
export class SharedModule { }
