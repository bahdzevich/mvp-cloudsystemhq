import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {MainPanelInfoComponent} from './components/main-panel-info/main-panel-info.component';
import {PartnersPanelComponent} from './components/partners-panel/partners-panel.component';
import {AboutPanelComponent} from './components/about-panel/about-panel.component';
import {ContactsPanelComponent} from './components/contacts-panel/contacts-panel.component';
import {CommentsPanelComponent} from './components/comments-panel/comments-panel.component';
import {CoreModule} from "./core/core.module";
import {SharedModule} from "./shared/shared.module";
import {AuthModule} from "./auth/auth.module";
import {NotificationModule} from '@progress/kendo-angular-notification';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { PartnerElementComponent } from './components/partners-panel/components/partner-element/partner-element.component';
import { CommentElementComponent } from './components/comments-panel/components/comment-element/comment-element.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainPanelInfoComponent,
    PartnersPanelComponent,
    AboutPanelComponent,
    ContactsPanelComponent,
    CommentsPanelComponent,
    PartnerElementComponent,
    CommentElementComponent
  ],
  imports: [
    BrowserModule,
    CoreModule,
    SharedModule,
    AuthModule,
    NotificationModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
