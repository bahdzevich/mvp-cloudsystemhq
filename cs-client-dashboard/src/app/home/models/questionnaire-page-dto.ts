import {QuestionDto} from "./question-dto";

export interface QuestionnairePageDto {
  questions: QuestionDto[];
  lastPage: boolean;
}
