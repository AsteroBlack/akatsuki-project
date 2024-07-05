import { IStatus } from "../../../shared/interfaces/status.interface";

export interface GetCommandeDetailsResponseApi {
  hasError: boolean;
  status: IStatus;
  count: number;
  items: CommandeDetails[]
}

export interface CommandeDetails {
  _id: string;
  _index: string;
  commande_id: string;
  commande_index: string;
  date_debut: string;
  libelle: string;
  plateforme: string;
  request?: string;
  responseValue: string;
  status: string;
  user: string;
  requestValue?: RequestValue;
}

export interface RequestValue {
  datasProvisioning?: DatasProvisioning[];
  index?: string;
  para?: { [key: string]: string };
}

export interface DatasProvisioning {

  codeTypeClient: string;
  datasParameter: DatasParameter[];
  groupename: string;
  username: string;
}

export interface DatasParameter {
  key: string;
  type: string;
  value: string;
}
