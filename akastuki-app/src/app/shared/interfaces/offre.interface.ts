import { IStatus } from "./status.interface";

export interface IGroupeDto {
    createdAt: string;
    createdBy: number;
    id: number;
    idOffre: number;
    idPlateforme: number;
    isDeleted: boolean;
    libelle: string;
    modeleBoxe: string;
    offerLibelle: string;
    plateformeCode: string;
    plateformeLibelle: string;
}

export interface IOffre {
    codeOffer: string;
    codeTypeClient: string;
    createdAt: string;
    createdBy: number;
    datasGroupe: IGroupeDto[];
    id: number;
    description?: string;
    idTypeOffre: number;
    typeOffreCode: string;
    typeOffreLibelle: string;
    isDeleted: boolean;
    libelle: string;
    idPlateforme?: number;
    plateformeCode: string;
    plateformeLibelle: string;
    idTemplate?: number;
    groupeRadiusCode: string;
    groupeRadiusLibelle: string;
    idGroupe?: number;
}

export interface IOffreResponseBody {
    status: IStatus;
    hasError: boolean;
    count: number;
    items: IOffre[];
}
export interface IDatasGroupe {
    libelle: string;
    idPlateforme: string;
}

export interface INewOfferBody {
    codeOffer: string;
    codeTypeClient: string;
    datasGroupe: IDatasGroupe[];
}
