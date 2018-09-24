import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../core/auth/services/auth.service";
import {User} from "../../../core/auth/models/user";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  @Output() public onClose: EventEmitter<void> = new EventEmitter<void>();

  @Output() public onReg: EventEmitter<User> = new EventEmitter<User>();

  public regForm: FormGroup;

  constructor(
    private fb: FormBuilder,
  ) { }

  ngOnInit() {
    this.createForm();
  }

  private createForm(): void {
    this.regForm = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
      confirm: ['', [Validators.required]]
    });
  }

  public close() {
    this.onClose.emit();
  }

  public registrate(): void {
    this.onReg.emit(new User(
      null,
      this.regForm.get('email').value,
      this.regForm.get('password').value
    ));

  }
}
