import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public registrationDisplay = false;

  public loginDisplay = false;

  constructor() { }

  ngOnInit() {
  }

  public showRegistrationDialog(): void {
    this.registrationDisplay = true;
  }

  public showLoginDialog(): void {
    this.loginDisplay = true;
  }
}
