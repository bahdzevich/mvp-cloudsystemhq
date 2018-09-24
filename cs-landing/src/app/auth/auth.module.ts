import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from "@angular/forms";

import {DialogsModule} from '@progress/kendo-angular-dialog';
import {ButtonsModule} from '@progress/kendo-angular-buttons';

import {AuthComponent} from './auth.component';
import {RegistrationComponent} from './component/registration/registration.component';
import {SharedModule} from "../shared/shared.module";

@NgModule({
  imports: [
    CommonModule,
    DialogsModule,
    ButtonsModule,
    SharedModule,
    ReactiveFormsModule
  ],
  declarations: [
    AuthComponent,
    RegistrationComponent
  ],
  exports: [
    AuthComponent
  ]
})
export class AuthModule { }
