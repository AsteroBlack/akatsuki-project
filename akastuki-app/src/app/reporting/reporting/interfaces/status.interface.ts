import { ReportingData } from './reportingData.interface';
import { IStatus } from "../../../shared/interfaces/status.interface";

export type Status = ReportingData

export interface GetStatusesResponseApi{
  hasError: boolean;
  status: IStatus;
  items: Status[]
}
