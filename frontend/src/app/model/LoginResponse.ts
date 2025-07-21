export interface LoginData {
    idC: number;
    name: string;
    surname: string;
    email: string;
    token: string;
  }

  export interface LoginResponse {
    status: string;
    message: string;
    data: LoginData;
  }