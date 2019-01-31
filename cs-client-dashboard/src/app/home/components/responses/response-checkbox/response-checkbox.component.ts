import { Component, OnInit } from '@angular/core';
import { QuestionResponsesComponent } from '../question-response/question-response.component';

@Component({
  selector: 'app-response-checkbox',
  templateUrl: './response-checkbox.component.html',
  styleUrls: ['./response-checkbox.component.scss']
})
export class ResponseCheckboxComponent extends QuestionResponsesComponent<string[]> implements OnInit {

  constructor() {
    super();
  }

  ngOnInit() {
  }

  public handleResponseChange(value){
    console.log(value);
  }
}
