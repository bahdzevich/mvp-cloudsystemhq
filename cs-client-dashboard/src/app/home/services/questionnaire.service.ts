import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {ReplaySubject} from "rxjs/internal/ReplaySubject";

import {Question} from '../models/question';
import {QuestionDto} from '../models/question-dto';

@Injectable()
export class QuestionnaireService {

  private static readonly LOAD_QUESTION_PAGE_QUERY: string = 'http://localhost:8080/api/question/test/page/';

  private currentPage: ReplaySubject<Question[]> = new ReplaySubject<Question[]>(1);
  private pages: Array<Question[]> = [];

  constructor(
    private http: HttpClient
  ) {
  }

  public hasPrevPage(): boolean {
    return this.pages.length > 1;
  }

  public get currentPage$(): Observable<Question[]> {
    return this.currentPage.asObservable();
  }

  private get nextPageNumber(): number {
    return this.pages.length;
  }

  public loadNextPage(): void {
    this.loadPage(this.nextPageNumber)
      .subscribe((page: Question[]) => {
        this.pages.push(page);
        this.currentPage.next(page);
      });
  }

  public loadPrevPage(): void {
    this.pages.pop();
    this.currentPage.next(this.pages[this.pages.length - 1]);
  }

  private loadPage(pageNumber: number): Observable<Question[]> {
    return this.http.get<QuestionDto[]>(QuestionnaireService.LOAD_QUESTION_PAGE_QUERY + pageNumber).pipe(
      map((dtos: QuestionDto[]) => {
        return dtos.map((dto: QuestionDto) => Question.fromDTO(dto));
      }));
  }

  public submitResponses(responses: Map<number, string | number>): void {
    let mapAsObj = {};
    responses.forEach(((value, key) => mapAsObj[key] = value));
    this.http.post('', mapAsObj).subscribe();
  }
}
