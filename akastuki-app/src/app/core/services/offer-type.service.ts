import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OfferType, OfferTypeResponseBody } from 'src/app/shared/interfaces/offreType.interface';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';

export interface SearchDatas {
  id: string
  libelle: string
  code: string
  parentId: string
  isLocked: string
  menusId: string
  createdAt: string
  updatedAt: string
  deletedAt: string
  createdBy: string
  updatedBy: string
  deletedBy: string
  isDeleted: string
}

@Injectable({
  providedIn: 'root'
})
export class OfferTypeService {

  private readonly basePath = "typeOffre"
  private userId: string

  constructor(private restClient: RestApiClientService, private userService: UserService) {
    this.userId = this.userService.getUser().id
  }

  get(searchDatas?: SearchDatas): Observable<OfferTypeResponseBody> {
    let bodyDatas = {
      user: this.userId,
      isSimpleLoading: false,
      data: {}
    }

    if (searchDatas) {
      bodyDatas = {
        ...bodyDatas,
        data: searchDatas
      }
    }
    console.log(bodyDatas)
    return this.restClient.execute(`${this.basePath}/getByCriteria`, bodyDatas)
  }
  create(datas: OfferType): Observable<OfferTypeResponseBody> {
    const bodyDatas = {
      user: this.userId,
      datas: datas
    }
    return this.restClient.execute(`${this.basePath}/create`, bodyDatas)
  }
  update(id: number, datas: OfferType): Observable<OfferTypeResponseBody> {
    const bodyDatas = {
      datas: [{ id, ...datas }]
    }
    return this.restClient.execute(`${this.basePath}/update`, bodyDatas)
  }
  delete(id: number): Observable<OfferTypeResponseBody> {
    const bodyDatas = {
      datas: [{ id }]
    }
    return this.restClient.execute(`${this.basePath}/delete`, bodyDatas)
  }
}
