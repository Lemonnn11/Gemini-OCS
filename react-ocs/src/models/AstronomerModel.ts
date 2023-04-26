export class AstronomerModel {
    id?: number;
    fname: string;
    lname: string;
    email?: string;
    accessLevel?: string;
    astronomerId?: number;

    constructor(fname: string, lname: string) {
      this.id = 0;
      this.fname = fname;
      this.lname = lname;
      this.email = "example@mail.com";
      this.accessLevel = "Astronomer";
      this.astronomerId = 0;
    }
  }

export default AstronomerModel;