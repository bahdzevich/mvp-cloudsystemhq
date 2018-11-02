import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";

import {Observable} from "rxjs/Observable";
import {tap} from "rxjs/operators";

import {ApplicationStateService} from "../services/application-state.service";
import {ApplicationStateEnum} from "../models/application-state.enum";

@Injectable()
export class ApplicationStateInterceptor implements HttpInterceptor {

  private static requestsInProgress: number = 0;

  constructor(private applicationStateService: ApplicationStateService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.executeBeforeRequest();
    return next.handle(request)
      .pipe(tap(() => this.executeAfterRequest()));
  }

  private executeAfterRequest(): void {
    ApplicationStateInterceptor.requestsInProgress = ApplicationStateInterceptor.requestsInProgress - 1;
    if (ApplicationStateInterceptor.requestsInProgress === 0) {
      this.applicationStateService.setState(ApplicationStateEnum.Available);
    }
  }

  private executeBeforeRequest(): void {
    ApplicationStateInterceptor.requestsInProgress = ApplicationStateInterceptor.requestsInProgress + 1;
    this.applicationStateService.setState(ApplicationStateEnum.Buzy);
  }
}
