import { QuestionResponseDto } from './question-response-dto';
import {ResponseTypeEnum} from "./response-type.enum";

export interface QuestionDto {
  id: number;
  responses: QuestionResponseDto[];
  title: string;
  type: ResponseTypeEnum
}
