import {Injectable} from '@angular/core';

import {ReplaySubject} from "rxjs/ReplaySubject";
import {Observable} from "rxjs/Observable";

import {User} from "../models/user";

/**
 * Service provide the current user as stream data.
 */
@Injectable({
  providedIn: 'root'
})
export class CurrentUserService {

  private currentUser: ReplaySubject<User> = new ReplaySubject<User>(1);

  constructor() { }

  public get currentUser$(): Observable<User> {
    return this.currentUser.asObservable();
  }

  public next(user: User): void {
    this.currentUser.next(user);
  }

  /**
   * Set null to current user stream.
   */
  public clear(): void {
    this.currentUser.next(null);
  }

}
