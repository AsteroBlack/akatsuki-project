import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { INewOfferOLTBody, IOffreOLTResponseBody } from 'src/app/shared/interfaces/offreOLT.interface';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';

@Injectable({
  providedIn: 'root'
})
export class OfferOltService {
  private readonly defaultBody:{
    id?: string;
    numero: string|null;
    createdAt: string;
    createdAtParam:{
      operator: string;
      start: string;
      end: string;
    };
  } = {
    numero: null,
    createdAt: "",
    createdAtParam: {
      operator: "[]",
      start: "",
      end: ""
    }
  }
  private readonly basePath = "offerOlt"
  constructor(
    private restClient: RestApiClientService,
  ) { }

  get(searchDatas?: { template?: string, rx?: string, tx?: string }): Observable<IOffreOLTResponseBody> {
    const bodyDatas = {
      /* index: page,
      size: dataLength, */
      isSimpleLoading: false,
      data: { ...this.defaultBody, ...searchDatas }
    }
    console.log(bodyDatas)
    return this.restClient.execute(`${this.basePath}/getByCriteria`, bodyDatas)
  }
  findOne(id: number): Observable<any> {
    const data = { ...this.defaultBody }
    data.id = id.toString()
    const datas = {
      datas: data
    }
    return this.restClient.execute(`${this.basePath}/getByCriteria`, datas)
  }
  create(datas: INewOfferOLTBody): Observable<IOffreOLTResponseBody> {
    const bodyDatas = {
      datas: [datas]
    }
    return this.restClient.execute(`${this.basePath}/create`, bodyDatas)
  }
  update(id: number, datas: INewOfferOLTBody): Observable<any> {
    const bodyDatas = {
      datas: [{ id, ...datas }]
    }
    return this.restClient.execute(`${this.basePath}/update`, bodyDatas)
  }
  delete(id: number): Observable<any> {
    const bodyDatas = {
      datas: [{ id }]
    }
    return this.restClient.execute(`${this.basePath}/delete`, bodyDatas)
  }
}
