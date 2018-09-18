import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {User} from "../models/user";

@Injectable()
export class AuthService {

  private static readonly REGISTRATE_URL = 'api/url-for-registration';

  constructor(
    private http: HttpClient
  ) { }

  public registrate(user: User): Observable<any> {
    return this.http.post(AuthService.REGISTRATE_URL, user);
  }
}
