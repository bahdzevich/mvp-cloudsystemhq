import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Question} from '../../models/question';
import {ResponseTypeEnum} from "../../models/response-type.enum";

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit {

  @Input() public question: Question;

  @Output() public onSelect: EventEmitter<string | number> = new EventEmitter<string | number>();

  public ResponseTypeEnum = ResponseTypeEnum;

  constructor() {
  }

  ngOnInit() {
  }

  public changeSelected(selected: string | Set<string>): void {
    this.question.selectedValue = selected;
  }
}
