import { IStatus } from "./status.interface";

export interface OfferType {
  id?: string;
  libelle: boolean;
  code: number;
  isDeleted: string;
  createdBy: string;
}

export interface OfferTypeResponseBody {
  status: IStatus;
  hasError: boolean;
  count: number;
  items?: OfferType[];
}
