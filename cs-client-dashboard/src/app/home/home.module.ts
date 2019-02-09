import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HomeComponent} from './home.component';
import {QuestionComponent} from './components/question/question.component';
import {ResponseCheckboxComponent} from "./components/responses/response-checkbox/response-checkbox.component";
import {ResponseRadioComponent} from "./components/responses/response-radio/response-radio.component";
import {ResponseTextComponent} from "./components/responses/response-text/response-text.component";
import {QuestionnaireService} from "./services/questionnaire.service";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    HomeComponent,
    QuestionComponent,
    ResponseCheckboxComponent,
    ResponseRadioComponent,
    ResponseTextComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [
    QuestionnaireService
  ],
  exports: [
    HomeComponent
  ]
})
export class HomeModule {
}
