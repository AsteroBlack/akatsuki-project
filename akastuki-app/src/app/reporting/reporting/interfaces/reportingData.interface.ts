import { StatusesKeys } from "./reporting-status.interface";

export interface ReportingData{
  key: StatusesKeys;
  value: string;
  percent: string;
}
