import { ReportingData } from './reportingData.interface';
import { IStatus } from "../../../shared/interfaces/status.interface";

export type Plateform = ReportingData

export interface GetPlateformsResponseApi{
  hasError: boolean;
  status: IStatus;
  items: Plateform[]
}
