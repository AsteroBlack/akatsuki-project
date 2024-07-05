import { IStatus } from "src/app/shared/interfaces/status.interface";

export interface GroupRadius{
  id?: number;
  libelle: string;
  code: string;
  localisationId?: string;
  isDeleted: boolean;
  isSuspend: boolean;
  createdAt: string;
  createdBy: number;
}

export interface GroupRadiusKey{
  key: string;
}

export interface GroupRadiusResponseBody {
  status: IStatus;
  hasError: boolean;
  count: number;
}
