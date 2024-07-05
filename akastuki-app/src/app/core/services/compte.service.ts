import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ICompteResponseBody, INewCompteBody, INewCompteResponseBody } from 'src/app/shared/interfaces/compte.interface';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';

@Injectable({
  providedIn: 'root'
})
export class CompteService {
  private readonly defaultBody = {
    id: "",
    libelle: "",
    createdA: "",
    createdBy: "",
    updatedAt: "",
    updatedBy: "",
    isDeleted: ""
  }
  private readonly basePath = "compte"
  constructor(
    private restClient: RestApiClientService,
    private user: UserService
  ) {
    this.defaultBody.id = this.user.getId()
  }

  get(page: number = 0, dataLength: number = 10): Observable<ICompteResponseBody> {
    const bodyDatas = {
      index: page,
      size: dataLength,
      data: { ...this.defaultBody }
    }
    return this.restClient.execute(`${this.basePath}/getByCriteria`, bodyDatas)
  }
  findOne(id: number): Observable<any> {
    const data = { ...this.defaultBody }
    data.id = id.toString()
    const datas = {
      data: data
    }
    return this.restClient.execute(`${this.basePath}/getByCriteria`, datas)
  }
  create(datas: INewCompteBody): Observable<INewCompteResponseBody> {
    const bodyDatas = {
      data: datas
    }
    return this.restClient.execute(`${this.basePath}/create`, bodyDatas)
  }
  update(datas: any): Observable<any> {
    const bodyDatas = {
      datas: [datas]
    }
    return this.restClient.execute(`${this.basePath}/update`, bodyDatas)
  }
  delete(id: number): Observable<any> {
    console.log('suppression compte', id)
    const bodyDatas = {
      datas: [{ idCompte: id }]
    }
    return this.restClient.execute(`${this.basePath}/delete`, bodyDatas)
  }
}
