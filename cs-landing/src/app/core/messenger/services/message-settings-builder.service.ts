import {Injectable} from '@angular/core';

import {CustomNotificationSettings} from "../models/custom-notification-settings";

@Injectable({
  providedIn: 'root'
})
export class MessageSettingsBuilder {

  constructor() { }

  public getLoginGoodSettings(): CustomNotificationSettings {
    return {
      content: () => {},
      title: 'Login good',
      message: 'Login was successfully',
      animation: {type: 'slide', duration: 200},
      position: {horizontal: 'right', vertical: 'top'},
      closable: true,
      type: {style: 'success', icon: true}
    };
  }

  public getLoginBadSettings(): CustomNotificationSettings {
    return {
      content: () => {},
      title: 'Login bad',
      message: 'Login was not successfully',
      animation: {type: 'slide', duration: 200},
      position: {horizontal: 'right', vertical: 'top'},
      closable: true,
      type: {style: 'error', icon: true}
    };
  }
}
