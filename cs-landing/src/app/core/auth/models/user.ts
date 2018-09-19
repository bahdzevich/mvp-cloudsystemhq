import {UserDTO} from "./user-dto";

export class User {

  public static fromDTO(dto: UserDTO): User {
    return new User(
      dto.id,
      dto.email,
      dto.password
    );
  }

  constructor(
    public id: number,
    public email: string,
    public password: string
  ) {
  }

}
