import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";

import {NotificationModule} from "@progress/kendo-angular-notification";

import {JwtInterceptor} from "./auth/interceptors/jwt-interceptor.service";
import {MessengerComponent} from './messenger/components/messenger/messenger.component';
import {ApplicationStateInterceptor} from "./application/interceptors/aplication-state-interceptor.service";

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    NotificationModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ApplicationStateInterceptor, multi: true}
  ],
  declarations: [
    MessengerComponent
  ],
  exports: [
    MessengerComponent
  ]
})
export class CoreModule {
}
