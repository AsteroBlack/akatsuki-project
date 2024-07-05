import { IStatus } from "../../../shared/interfaces/status.interface";
import { StatusesKeys } from './reporting-status.interface';

export interface GetCommandsListResponseApi{
  hasError: boolean;
  status: IStatus;
  count: number;
  items: CommandsListCommand[]
}

export interface CommandsListCommand{
  _id: string;
  date_debut?: string;
  date_fin?: string;
  libelle: string;
  reference: string;
  requestValue?: {
    parameters?: CommandsListCommandParameters[];
  } | string;
  responseValue?: {
    parameters: CommandsListCommandParameters[];
  } | string;
  status: StatusesKeys;
  user: any;
}

export interface CommandsListCommandParameters{
  key: string;
  value: string;
}

export interface CommandsListCommandViewTableDto extends CommandsListCommand{
  id: number;
}
