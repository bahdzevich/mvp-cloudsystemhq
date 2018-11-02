import {Injectable} from '@angular/core';

import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";

import {CustomNotificationSettings} from "../models/custom-notification-settings";

@Injectable({
  providedIn: 'root'
})
export class MessengerService {

  private notifications: Subject<CustomNotificationSettings> = new Subject<CustomNotificationSettings>();

  constructor() {}

  public get notifications$(): Observable<CustomNotificationSettings> {
    return this.notifications.asObservable();
  }

  public add(settings: CustomNotificationSettings): void {
    this.notifications.next(settings);
  }


}
