import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";

import {JwtInterceptor} from "./auth/interceptors/jwt-interceptor.service";
import {CurrentUserService} from "./auth/services/current-user.service";
import {AuthService} from "./auth/services/auth.service";

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    CurrentUserService,
    AuthService
  ]
})
export class CoreModule {
}
