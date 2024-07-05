import { IStatus } from "./status.interface";

export interface IOffreOLT {
  id: number;
  template: string;
  rx: string;
  tx: string;
  isDeleted: boolean;
  createdAt: string;
}

export interface IOffreOLTResponseBody {
  status: IStatus;
  hasError: boolean;
  count: number;
  items: IOffreOLT[];
}

export interface INewOfferOLTBody {
  offer: string;
  template: string;
  rx: string;
  tx: string;
}
