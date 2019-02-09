import {Component, OnInit} from '@angular/core';
import {QuestionResponsesComponent} from '../question-response/question-response.component';

@Component({
  selector: 'app-response-radio',
  templateUrl: './response-radio.component.html',
  styleUrls: ['./response-radio.component.scss']
})
export class ResponseRadioComponent extends QuestionResponsesComponent<string> implements OnInit {

  constructor() {
    super();
  }

  ngOnInit() {
  }

  public handleResponseChange(value: string): void {
    this.selectedValue = value;
    this.selectedValueChange.emit(this.selectedValue);
    console.log(value);
  }
}
