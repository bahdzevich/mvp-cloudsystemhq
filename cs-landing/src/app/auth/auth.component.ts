import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../core/auth/models/user";
import {AuthService} from "../core/auth/services/auth.service";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  /**
   * Required param for correct work.
   */
  @Input() public registrationDisplay: boolean;

  @Output() public registrationDisplayChange: EventEmitter<boolean> = new EventEmitter<boolean>();

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
      .subscribe(() => this.registrationDisplay = false);
  }
}
