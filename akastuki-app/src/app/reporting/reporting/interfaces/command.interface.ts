import { ReportingData } from './reportingData.interface';
import { IStatus } from "../../../shared/interfaces/status.interface";

export type Command = ReportingData

export interface GetCommandsResponseApi{
  hasError: boolean;
  status: IStatus;
  items: Command[]
}
