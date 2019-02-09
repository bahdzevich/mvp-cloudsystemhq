import {Component, OnDestroy, OnInit} from '@angular/core';

import {Subscription} from "rxjs/internal/Subscription";

import {QuestionnaireService} from './services/questionnaire.service';
import {Question} from './models/question';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {

  public questionnaire: Question[] = [];

  private events: Subscription[] = [];

  /**
   * Key - question id.
   * Value - response id.
   */
  private responses: Map<number, string | number> = new Map();

  constructor(
    private questionnaireService: QuestionnaireService
  ) {
  }

  ngOnInit() {
    this.initPageLoading();
    this.questionnaireService.loadNextPage();
  }

  private initPageLoading(): void {
    let sub = this.questionnaireService.currentPage$
      .subscribe((page: Question[]) => this.questionnaire = page);
    this.events.push(sub);
  }

  public goToPrevResponses(): void {
    this.questionnaireService.loadPrevPage();
  }

  public submitResponses(): void {
    this.questionnaireService.submitResponses(this.responses);
  }

  public goToNextPage(): void {
    this.questionnaireService.loadNextPage();
  }

  public canGoNextPage(): boolean {
    return true;
  }

  public canGoPrevPage(): boolean {
    return this.questionnaireService.hasPrevPage();
  }

  public isLastPage(): boolean {
    return this.questionnaireService.isLastPage;
  }

  public onSelect(questionId: number, responseId: string | number): void {
    this.responses.set(questionId, responseId);
  }

  ngOnDestroy() {
    this.events.forEach(e => e.unsubscribe());
  }
}
