import {EventEmitter, Input, Output} from '@angular/core';
import {QuestionResponse} from "../../../models/question-response";

export abstract class QuestionResponsesComponent<S> {
  @Input() public responses: QuestionResponse[];
  @Input() public selectedValue: S;
  @Output() public selectedValueChange: EventEmitter<S> = new EventEmitter<S>();
}
