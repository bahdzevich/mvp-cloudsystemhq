import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {QuestionComponent} from './question.component';
import {ResponseCheckboxComponent} from "../responses/response-checkbox/response-checkbox.component";
import {ResponseRadioComponent} from "../responses/response-radio/response-radio.component";
import {ResponseTextComponent} from "../responses/response-text/response-text.component";
import {Question} from "../../models/question";
import {ResponseTypeEnum} from "../../models/response-type.enum";
import {QuestionResponse} from "../../models/question-response";

describe('QuestionComponent', () => {

  let testTextQuestion: Question;
  let testRadioQuestion: Question;
  let testCheckQuestion: Question;

  let component: QuestionComponent;
  let fixture: ComponentFixture<QuestionComponent>;

  function buildResponses(): QuestionResponse[] {
    return [
      QuestionResponse.fromDTO({
        id: 1,
        text: 'respT1',
        value: 'respV1'
      }),
      QuestionResponse.fromDTO({
        id: 2,
        text: 'respT2',
        value: 'respV2'
      }),
      QuestionResponse.fromDTO({
        id: 3,
        text: 'respT3',
        value: 'respV3'
      })
    ]
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        QuestionComponent,
        ResponseCheckboxComponent,
        ResponseRadioComponent,
        ResponseTextComponent
      ]
    });
  }));

  beforeEach(() => {
    testTextQuestion = Question.fromDTO({
      id: 1,
      responses: buildResponses(),
      title: '',
      type: ResponseTypeEnum.TEXT
    });
    testRadioQuestion = Question.fromDTO({
      id: 2,
      responses: buildResponses(),
      title: '',
      type: ResponseTypeEnum.RADIO_BUTTON
    });
    testCheckQuestion = Question.fromDTO({
      id: 3,
      responses: buildResponses(),
      title: '',
      type: ResponseTypeEnum.CHECKBOX
    });
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionComponent);
    component = fixture.componentInstance;
  });

  describe('with text question', () => {
    it('should create', () => {
      component.question = testTextQuestion;;
      expect(component).toBeTruthy();
    });
  });

  describe('with radio question', () => {
    it('should create', () => {
      component.question = testRadioQuestion;
      expect(component).toBeTruthy();
    });
  });

  describe('with checkbox question', () => {
    it('should create', () => {
      component.question = testCheckQuestion;;
      expect(component).toBeTruthy();
    });
  });

});
