import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";

import {Observable} from "rxjs/internal/Observable";
import {OperatorFunction} from "rxjs/interfaces";
import {map} from "rxjs/operators";

import {User} from "../models/user";
import {UserDTO} from "../models/user-dto";
import {CurrentUserService} from "./current-user.service";
import {Token} from "../models/token";

/**
 * Service for registration and authorisation.
 */
@Injectable()
export class AuthService {

  private static readonly REGISTRATE_URL = 'api/customers';

  private static readonly LOGIN_URL = 'api/oauth/token';

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

  public registrate(user: User): Observable<any> {
    return this.http.post<HttpResponse<UserDTO>>(AuthService.REGISTRATE_URL, user);
  }

  public login(user: User): Observable<User> {
    return this.http.post(AuthService.LOGIN_URL, user)
      .pipe(this.authResponseHandler)
  }

}
