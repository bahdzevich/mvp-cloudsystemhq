import {Component, OnDestroy, OnInit, TemplateRef, ViewChild} from '@angular/core';

import {NotificationService} from "@progress/kendo-angular-notification";
import {Subscription} from "rxjs/Subscription";

import {MessengerService} from "../../services/messenger.service";
import {CustomNotificationSettings} from "../../models/custom-notification-settings";

@Component({
  selector: 'app-messenger',
  templateUrl: './messenger.component.html',
  styleUrls: ['./messenger.component.css']
})
export class MessengerComponent implements OnInit, OnDestroy {

  @ViewChild('messagesTemplate', {read: TemplateRef}) public notificationTemplate: TemplateRef<any>;

  public settings: CustomNotificationSettings;

  private events: Subscription[] = [];

  constructor(
    private notificationService: NotificationService,
    private messengerService: MessengerService
  ) { }

  ngOnInit() {
    this.startNotificationSub();
  }

  private startNotificationSub(): void {
    let sub = this.messengerService.notifications$
      .subscribe((settings: CustomNotificationSettings) => this.show(settings));
    this.events.push(sub);
  }

  private show(settings: CustomNotificationSettings): void {
    (<any>settings).content = this.notificationTemplate;
    this.settings = settings;
    const notificationRef = this.notificationService.show(settings);
  }

  ngOnDestroy() {
    this.events.forEach(event => event.unsubscribe());
  }
}
