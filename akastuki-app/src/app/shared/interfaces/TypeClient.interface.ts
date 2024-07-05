export interface TypeCLient {
  code:       string;
  createdAt?:  string;
  id?:         number;
  isDeleted?:  boolean;
  libelle:    string;
  updatedAt?: string;
  updatedBy?: number;
}

export interface Response {
  status:   ReponseStatus;
  hasError: boolean;
}

export interface ReponseStatus {
  message: string;
  code: string;
}

export interface TypeClientResponse extends Response {
  items:    TypeCLient[];
  count?:    number;
}
