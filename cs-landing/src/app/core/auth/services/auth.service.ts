import {Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';
import {HttpClient, HttpResponse} from "@angular/common/http";

import {Observable} from "rxjs/internal/Observable";
import {OperatorFunction} from "rxjs/interfaces";
import {finalize, map} from "rxjs/operators";

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

  private authResponseHandler: OperatorFunction<HttpResponse<Token>, any> = map((resp: HttpResponse<Token>) => {
    localStorage.setItem('token', JSON.stringify(resp));
    return null;
  });

  public registrate(user: User): Observable<any> {
    return this.http.post<HttpResponse<UserDTO>>(AuthService.REGISTRATE_URL, user);
  }

  public login(user: User): Observable<any> {
    const httpOptions = {
    headers: new HttpHeaders({
        'Authorization': 'Basic dHJ1c3RlZC1hcHA6c2VjcmV0'
      })
    };
    let payload=new FormData();
    payload.append('password', user.password);
    payload.append('username', user.email);
    payload.append('grant_type', 'password');
    return this.http.post(AuthService.LOGIN_URL, payload, httpOptions)
      .pipe(this.authResponseHandler)
      .pipe(finalize(() => window.open("https://mangaclub.ru/", "_self")));
  }

}
