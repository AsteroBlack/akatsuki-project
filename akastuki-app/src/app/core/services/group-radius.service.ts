import { UserService } from 'src/app/shared/services/user.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { GroupRadius, GroupRadiusKey, GroupRadiusResponseBody } from '../../shared/interfaces/group-radius.interface';

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
export class GroupRadiusService {

  private readonly basePath = "groupeRadius"
  private userId: string

  constructor(private restClient: RestApiClientService, private userService: UserService) {
    this.userId = this.userService.getUser().id
  }

  get(searchDatas?: SearchDatas): Observable<GroupRadiusResponseBody & {items: GroupRadius[]}> {
    let bodyDatas = {
      user: this.userId,
      isSimpleLoading: false,
      data: {}
    }

    if(searchDatas){
      bodyDatas = {
        ...bodyDatas,
        data: searchDatas
      }
    }
    console.log(bodyDatas)
    return this.restClient.execute(`${this.basePath}/getByCriteria`, bodyDatas)
  }
  getKeys(searchDatas?: SearchDatas): Observable<GroupRadiusResponseBody & {items: GroupRadiusKey[]}> {
    let bodyDatas = {
      user: this.userId,
      isSimpleLoading: false,
      data: {}
    }

    if(searchDatas){
      bodyDatas = {
        ...bodyDatas,
        data: searchDatas
      }
    }
    console.log(bodyDatas)
    return this.restClient.getRadius('radgroup/getAttributByGroupenameLite', bodyDatas)
  }
  create(datas: GroupRadius): Observable<GroupRadiusResponseBody> {
    const bodyDatas = {
      user: this.userId,
      datas: datas
    }
    return this.restClient.execute(`${this.basePath}/create`, bodyDatas)
  }
  update(id: number, datas: GroupRadius): Observable<GroupRadiusResponseBody> {
    const bodyDatas = {
      datas: [{ id, ...datas }]
    }
    return this.restClient.execute(`${this.basePath}/update`, bodyDatas)
  }
  delete(id: number): Observable<GroupRadiusResponseBody> {
    const bodyDatas = {
      datas: [{ id }]
    }
    return this.restClient.execute(`${this.basePath}/delete`, bodyDatas)
  }
}
