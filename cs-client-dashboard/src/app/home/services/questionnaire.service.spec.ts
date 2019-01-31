import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";

import {QuestionnaireService} from './questionnaire.service';
import {Question} from "../models/question";
import {QuestionDto} from "../models/question-dto";
import {ResponseTypeEnum} from "../models/response-type.enum";

describe('QuestionnaireService', () => {
  let service: QuestionnaireService;
  let backend: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [QuestionnaireService]
    });
    service = TestBed.get(QuestionnaireService);
    backend = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should load page', () => {
    service.currentPage$
      .subscribe((page: Question[]) => {
        expect(page.length).toBe(1);
      });
    service.loadNextPage();
    backend.expectOne('http://localhost:8080/api/question/test/page/0').flush([<QuestionDto>{
      id: 0,
      title: '',
      responses: [],
      type: ResponseTypeEnum.TEXT
    }]);
    backend.verify();
  });
});
