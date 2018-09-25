import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {User} from "../../../core/auth/models/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Output() public onClose: EventEmitter<void> = new EventEmitter<void>();

  @Output() public onLogin: EventEmitter<User> = new EventEmitter<User>();

  public loginForm: FormGroup;

  constructor(
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.createForm();
  }

  private createForm(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  public close() {
    this.onClose.emit();
  }

  public login(): void {
    this.onLogin.emit(new User(
      null,
      this.loginForm.get('email').value,
      this.loginForm.get('password').value
    ));
  }

}
