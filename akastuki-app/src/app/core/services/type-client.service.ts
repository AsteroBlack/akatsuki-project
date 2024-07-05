import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { TypeClientResponse } from 'src/app/shared/interfaces/TypeClient.interface';

@Injectable({
  providedIn: 'root'
})
export class TypeClientService {

  private readonly basePath = "typeClient"
  constructor(private restApi: RestApiClientService) { }

  get(page?: { index: number; size: number }, searchDatas?: { code: string, libelle: string }): Observable<TypeClientResponse> {
    let data: {
      index?: number;
      size?: number;
      serviceLibelle: string;
      ndCode: string;
      customerCode: string;
      user: string;
      data: {
          code: string;
          libelle: string;
      };
      orderBy: string;
      takeAll: boolean;
  } = {
      serviceLibelle: `Consultation de la liste des types clients`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: searchDatas ? searchDatas : {
        libelle: '',
        code: ''
      },
      orderBy: 'desc',
      takeAll: true
    }

    if(page){
      data = {
        index: page.index,
        size: page.size,
        ...data
      }
    }

    console.log('data', data);
    return this.restApi.executeIpManager(`${this.basePath}/getByCriteria`, data)
  }

  edit(action: 'update' | 'create', datas: { id?: string, code: string, libelle: string }): Observable<TypeClientResponse> {
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: `${datas.id && action == 'update' ? 'Mise à jours' : 'Création'} de ressources en type-client`,
      datas: [datas]
    };

    console.log('data', data);
    return this.restApi.executeIpManager(`${this.basePath}/${action}`, data)
  }

  create(datas: { id: string, code: string, libelle: string }): Observable<TypeClientResponse> {
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: 'Création de ressources en type-client',
      datas: [datas]
    };

    console.log('data', data);
    return this.restApi.executeIpManager(`${this.basePath}/create`, data)
  }

  update(datas: { code: string, libelle: string }): Observable<TypeClientResponse> {
    const data = {
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */ '1118',
      serviceLibelle: 'Mise à jours de ressources en type-client',
      datas: [datas]
    };

    console.log('data', data);
    return this.restApi.executeIpManager(`${this.basePath}/update`, data)
  }

  delete(typeCliendId: string): Observable<Response> {
    const data = {
      datas: [{
        id: typeCliendId,
      }]
    };
    console.log('data', data);
    return this.restApi.executeIpManager(`${this.basePath}/delete`, data)
  }
}
