import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {Observable, of} from "rxjs";

import {HomeComponent} from './home.component';
import {HomeModule} from "./home.module";
import {QuestionnaireService} from "./services/questionnaire.service";
import {Question} from "./models/question";
import {ResponseTypeEnum} from "./models/response-type.enum";

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  let questionnaireServiceStub = {
    hasPrevPage(): boolean {
      return true;
    },
    get currentPage$(): Observable<Question[]> {
      return of([Question.fromDTO({
        type: ResponseTypeEnum.CHECKBOX,
        title: '',
        responses: [],
        id: 0
      })]);
    },
    get nextPageNumber(): number {
      return 1;
    },
    loadNextPage() {
    },
    loadPrevPage() {
    }
  };

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HomeModule],
      providers: [{provide: QuestionnaireService, useValue: questionnaireServiceStub}]
    });
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
})
;
