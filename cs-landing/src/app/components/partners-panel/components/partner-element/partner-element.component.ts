import {Component, Input, OnInit} from '@angular/core';
import {PartnerElementSettings} from "../../models/partner-element-settings";

@Component({
  selector: 'app-partner-element',
  templateUrl: './partner-element.component.html',
  styleUrls: ['./partner-element.component.css']
})
export class PartnerElementComponent implements OnInit {

  @Input() public settings: PartnerElementSettings;

  constructor() { }

  ngOnInit() {
  }

}
