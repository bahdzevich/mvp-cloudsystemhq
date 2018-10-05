import { Component, OnInit } from '@angular/core';
import {PartnerElementSettings} from "./models/partner-element-settings";

@Component({
  selector: 'app-partners-panel',
  templateUrl: './partners-panel.component.html',
  styleUrls: ['./partners-panel.component.css']
})
export class PartnersPanelComponent implements OnInit {

  public partnerProperties: any[] = [
    new PartnerElementSettings("AWS"),
    new PartnerElementSettings("Google"),
    new PartnerElementSettings("Yandex"),
    new PartnerElementSettings("Sharaga")
  ];

  constructor() { }

  ngOnInit() {
  }

}
