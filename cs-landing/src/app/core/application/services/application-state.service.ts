import {Injectable} from "@angular/core";

import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";

import {ApplicationStateEnum} from "../models/application-state.enum";

@Injectable({
  providedIn: 'root'
})
export class ApplicationStateService {

  private applicationState: Subject<ApplicationStateEnum> = new Subject<ApplicationStateEnum>();

  public get state$(): Observable<ApplicationStateEnum> {
    return this.applicationState.asObservable();
  }

  public setState(state: ApplicationStateEnum): void {
    this.applicationState.next(state);
  }

}
