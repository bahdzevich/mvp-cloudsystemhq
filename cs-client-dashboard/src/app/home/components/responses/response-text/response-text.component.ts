import {Component, OnInit} from '@angular/core';
import {QuestionResponsesComponent} from '../question-response/question-response.component';
import {QuestionResponse} from "../../../models/question-response";

@Component({
  selector: 'app-response-text',
  templateUrl: './response-text.component.html',
  styleUrls: ['./response-text.component.scss']
})
export class ResponseTextComponent extends QuestionResponsesComponent<string> implements OnInit {

  constructor() {
    super();
  }

  ngOnInit() {
  }

  public getTitle(resps: QuestionResponse[]): string {
    return resps.length > 0 ? resps[0].text : '';
  }

  public handleResponseChange(value: string): void {
    console.log(value);
    this.selectedValueChange.emit(value);
  }
}
