import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";

import {Observable} from "rxjs/internal/Observable";
import {OperatorFunction} from "rxjs/interfaces";
import {map} from "rxjs/operators";

import {User} from "../models/user";
import {UserDTO} from "../models/user-dto";
import {CurrentUserService} from "./current-user.service";

/**
 * Service for registration and authorisation.
 */
@Injectable()
export class AuthService {

  private static readonly REGISTRATE_URL = 'api/url-for-registration';

  private static readonly LOGIN_URL = 'api/url-for-login';

  constructor(
    private http: HttpClient,
    private currentUserService: CurrentUserService
  ) { }

  private authResponseHandler: OperatorFunction<HttpResponse<UserDTO>, User> = map((resp: HttpResponse<UserDTO>) => {
    localStorage.setItem('token', resp.headers.get('token'));
    let user: User = User.fromDTO(resp.body);
    this.currentUserService.next(user);
    return user;
  });

  public registrate(user: User): Observable<User> {
    return this.http.post<HttpResponse<UserDTO>>(AuthService.REGISTRATE_URL, user)
      .pipe(this.authResponseHandler);
  }

  public login(user: User): Observable<User> {
    return this.http.post(AuthService.LOGIN_URL, user)
      .pipe(this.authResponseHandler)
  }

}
