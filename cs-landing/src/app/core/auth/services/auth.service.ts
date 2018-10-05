import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';

import {Observable} from "rxjs/internal/Observable";
import {OperatorFunction} from "rxjs/interfaces";
import {catchError, finalize, map, take, tap} from "rxjs/operators";

import {User} from "../models/user";
import {UserDTO} from "../models/user-dto";
import {Token} from "../models/token";
import {MessengerService} from "../../messenger/services/messenger.service";
import {MessageSettingsBuilder} from "../../messenger/services/message-settings-builder.service";

/**
 * Service for registration and authorisation.
 */
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private static readonly REGISTRATE_URL = 'api/customers';

  private static readonly LOGIN_URL = 'api/oauth/token';

  constructor(
    private http: HttpClient,
    private messengerService: MessengerService,
    private messageSettingsBuilder: MessageSettingsBuilder
  ) { }

  private authResponseHandlers: OperatorFunction<HttpResponse<Token>, any>[] = [
    map((resp: HttpResponse<Token>) => {
      localStorage.setItem('token', JSON.stringify(resp));
      return null;
    }),
    tap(() => this.executeAfterGoodLogin()),
    catchError(err => {
      this.executeAfterBadLogin();
      throw 'Login error. Details: ' + err;
    })
  ];

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
      .pipe(...this.authResponseHandlers)
  }

  private executeAfterGoodLogin(): any {
    this.messengerService.add(this.messageSettingsBuilder.getLoginGoodSettings());
    // window.open("https://mangaclub.ru/", "_self");
  }

  private executeAfterBadLogin(): any {
    this.messengerService.add(this.messageSettingsBuilder.getLoginBadSettings());
    // window.open("https://mangaclub.ru/", "_self");
  }

}
