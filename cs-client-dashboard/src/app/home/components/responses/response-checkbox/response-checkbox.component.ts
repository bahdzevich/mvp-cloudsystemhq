import { Component, OnInit } from '@angular/core';
import { QuestionResponsesComponent } from '../question-response/question-response.component';

@Component({
  selector: 'app-response-checkbox',
  templateUrl: './response-checkbox.component.html',
  styleUrls: ['./response-checkbox.component.scss']
})
export class ResponseCheckboxComponent extends QuestionResponsesComponent<Set<string>> implements OnInit {

  constructor() {
    super();
  }

  ngOnInit() {
  }

  public handleResponseChange(value: string){
    this.selectedValue.has(value)
      ? this.selectedValue.delete(value)
      : this.selectedValue.add(value);
    this.selectedValueChange.emit(this.selectedValue);
    console.log(value);
  }
}
