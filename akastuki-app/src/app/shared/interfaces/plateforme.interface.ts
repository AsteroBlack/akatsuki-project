import { IStatus } from "./status.interface";

export interface Plateforme {
  id: number;
  code: string;
  libelle: string;
  isDeleted: boolean;
  createdAt: string;
  createdBy: number;
}

export interface IPlateformeResponseBody {
    status: IStatus;
    hasError: boolean;
    count: number;
    items: Plateforme[];
}
