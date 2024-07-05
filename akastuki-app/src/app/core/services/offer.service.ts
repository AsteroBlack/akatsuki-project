import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { INewOfferBody, IOffreResponseBody } from 'src/app/shared/interfaces/offre.interface';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  private readonly defaultBody = {
    id: "",
    libelle: ""
  }
  private readonly basePath = "offer"
  constructor(
    private restClient: RestApiClientService,
    private user: UserService
  ) {
    // this.defaultBody.id = this.user.getId()
  }

  get(searchDatas: {codeOffer?: string, codeTypeClient?: string}, page = 0, dataLength = 10): Observable<IOffreResponseBody> {
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
  create(datas: INewOfferBody): Observable<IOffreResponseBody> {
    const bodyDatas = {
      datas: [datas]
    }
    return this.restClient.execute(`${this.basePath}/create`, bodyDatas)
  }
  update(id: number, datas: INewOfferBody): Observable<any> {
    const bodyDatas = {
      datas: [{id, ...datas}]
    }
    return this.restClient.execute(`${this.basePath}/update`, bodyDatas)
  }
  delete(id: number): Observable<any> {
    const bodyDatas = {
      datas: [{ id: id }]
    }
    return this.restClient.execute(`${this.basePath}/delete`, bodyDatas)
  }
}
