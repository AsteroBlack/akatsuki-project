import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IPlateformeResponseBody } from 'src/app/shared/interfaces/plateforme.interface';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';

@Injectable({
  providedIn: 'root'
})
export class PlateformeService {

  private readonly defaultBody = {
    id: "",
		libelle: "",
		moduleId: "",
		isLocked: "",
		code: "",
		createdAt: "",
		updatedAt: "",
		deletedAt: "",
		createdBy: "",
		updatedBy: "",
		deletedBy: "",
		isDeleted: ""
  }
  private readonly basePath = "plateforme"
  constructor(private restClient: RestApiClientService) { }

  get(): Observable<IPlateformeResponseBody> {
    const bodyDatas = {
      isSimpleLoading: false,
      data: { ...this.defaultBody }
    }
    console.log(bodyDatas)
    return this.restClient.execute(`${this.basePath}/getByCriteria`, bodyDatas)
  }
}
