import {QuestionDto} from './question-dto';
import {QuestionResponse} from './question-response';
import {ResponseTypeEnum} from "./response-type.enum";

export class Question {

  public static fromDTO(dto: QuestionDto): Question {
    return new Question(
      dto.id,
      dto.responses.map(resp => QuestionResponse.fromDTO(resp)),
      dto.title,
      dto.type,
      dto.type == ResponseTypeEnum.CHECKBOX ? new Set<string>() : ''
    );
  }

  constructor(
    public id: number,
    public responses: QuestionResponse[],
    public title: string,
    public type: ResponseTypeEnum,
    public selectedValue: string | Set<string>
  ) {
  }
}
