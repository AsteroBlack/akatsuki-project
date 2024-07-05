import { IStatus } from "./status.interface";

export interface ICompte {
    createdAt: string;
    idCompte: number;
    isDeleted: boolean;
    salt: string;
    securityPassword: string;
    securityToken: string;
    updatedAt: string;
    updatedBy: number;
    userCompte: string;
}

export interface ICompteResponseBody {
    status: IStatus;
    hasError: boolean;
    count: number;
    items: ICompte[];
}
export interface INewCompteResponseBody {
    status: IStatus;
    hasError: boolean;
    count: number;
    item: ICompte;
}
export interface INewCompteBody {
    userCompte: string;
    login: string;
    password: string;
}