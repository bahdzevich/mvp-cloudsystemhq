import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../core/auth/models/user";
import {AuthService} from "../core/auth/services/auth.service";
import {finalize, tap} from "rxjs/operators";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  @Input() public registrationDisplay: boolean;

  @Output() public registrationDisplayChange: EventEmitter<boolean> = new EventEmitter<boolean>();

  @Input() public loginDisplay: boolean;

  @Output() public loginDisplayChange: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit() {
  }

  public closeRegistrationDialog(): void {
    this.registrationDisplay = false;
    this.registrationDisplayChange.emit(false);
  }

  public registrate(user: User): void {
    this.authService.registrate(user)
      .pipe(finalize(() => this.closeRegistrationDialog()))
      .subscribe();
  }

  public closeLoginDialog(): void {
    this.loginDisplay = false;
    this.loginDisplayChange.emit(false);
  }

  public login(user: User): void {
    this.authService.login(user)
      .pipe(finalize(() => this.closeLoginDialog()))
      .subscribe();
  }
}
