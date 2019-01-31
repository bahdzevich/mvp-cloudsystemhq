import { QuestionResponseDto } from './question-response-dto';

export class QuestionResponse {

  public static fromDTO(dto: QuestionResponseDto): QuestionResponse {
    return new QuestionResponse(dto.id, dto.text, dto.value);
  }

  constructor(
    public id: number,
    public text: string,
    public value: string
  ){}
}