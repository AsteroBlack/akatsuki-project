import { Moment } from 'moment';
import { GetStatusesResponseApi, Status } from './reporting/interfaces/status.interface';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { RestApiClientService } from '../shared/services/rest-api-client.service';
import { delay, map, retry } from 'rxjs/operators';
import { GetPlateformsResponseApi, Plateform } from './reporting/interfaces/plateform.interface';
import { Command, GetCommandsResponseApi } from './reporting/interfaces/command.interface';
import { StatusesKeys } from './reporting/interfaces/reporting-status.interface';
import { CommandsListCommand, GetCommandsListResponseApi } from './reporting/interfaces/command-list.interface';
import commandeListResponse from "./reporting/commandes-list/response.json"
import commandeDetailsResponse from "./reporting/commandes-list/response_details.json"
import { CommandeDetails, GetCommandeDetailsResponseApi } from './reporting/interfaces/command-details.interface';

export interface RangeDates {
  start: Moment | null;
  end: Moment | null;
}

export interface CommandeListObservableEmission { count: number; commandes: CommandsListCommand[] }

@Injectable({
  providedIn: 'root'
})
export class ReportingService {
  private rangeDates = new BehaviorSubject<RangeDates>({ start: null, end: null })
  rangeDates$ = this.rangeDates.asObservable();
  commandes: BehaviorSubject<[]>= new BehaviorSubject<any>([])
  chart: BehaviorSubject<{libelle: any, status: any}>= new BehaviorSubject<any>({})
  chartClick: BehaviorSubject<boolean>= new BehaviorSubject<boolean>(false)
  newIndex: BehaviorSubject<number>= new BehaviorSubject<any>(0)

  constructor(private restApiClientService: RestApiClientService) { }

  updateRangeDates(rangeDates: RangeDates) {
    this.rangeDates.next(rangeDates)
  }

  getStatuses(rangeDates: RangeDates): Observable<Status[]> {
    const { startDate, endDate } = this.formatRangeDatesFrString(rangeDates)

    let datas: {
      data: {
        startDate?: string;
        endDate?: string;
        status?: StatusesKeys
      }
    } = { data: {} }

    if (startDate) {
      datas.data.startDate = startDate
    }
    if (endDate) {
      datas.data.endDate = endDate
    }

    return this.restApiClientService.executeDashboard<GetStatusesResponseApi>(
      'getInfosStatus',
      datas
    ).pipe(
      retry(3),
      map(statusesResponse => {
        if (!statusesResponse.hasError) {
          return statusesResponse.items
        }
        else {
          throw new Error(statusesResponse.status.message)
        }
      })
    )
  }

  getPlateformsDatas(rangeDates: RangeDates, status: StatusesKeys): Observable<Plateform[]> {
    const { startDate, endDate } = this.formatRangeDatesFrString(rangeDates)

    let datas: {
      data: {
        startDate?: string;
        endDate?: string;
        status?: StatusesKeys
      }
    } = { data: { status } }

    if (startDate) {
      datas.data.startDate = startDate
    }
    if (endDate) {
      datas.data.endDate = endDate
    }

    return this.restApiClientService.executeDashboard<GetPlateformsResponseApi>(
      'getInfosPlateforme',
      datas
    ).pipe(
      retry(3),
      map(statusesResponse => {
        if (!statusesResponse.hasError) {
          return statusesResponse.items
        }
        else {
          throw new Error(statusesResponse.status.message)
        }
      })
    )
  }

  getCommandsDatas(rangeDates: RangeDates, status: StatusesKeys): Observable<Command[]> {
    const { startDate, endDate } = this.formatRangeDatesFrString(rangeDates)

    let datas: {
      data: {
        startDate?: string;
        endDate?: string;
        status?: StatusesKeys
      }
    } = { data: { status } }

    if (startDate) {
      datas.data.startDate = startDate
    }
    if (endDate) {
      datas.data.endDate = endDate
    }

    return this.restApiClientService.executeDashboard<GetCommandsResponseApi>(
      'getInfosCommande',
      datas
    ).pipe(
      retry(3),
      map(statusesResponse => {
        if (!statusesResponse.hasError) {
          return statusesResponse.items
        }
        else {
          throw new Error(statusesResponse.status.message)
        }
      })
    )
  }

  getCommandeListDatas(rangeDates: RangeDates, index: number, size: number, libelle?: string, status?: string): Observable<CommandeListObservableEmission> {
    const { startDate, endDate } = this.formatRangeDatesFrString(rangeDates)
    const mapCallBack = (commandeListResponse: GetCommandsListResponseApi) => {
      if (!commandeListResponse.hasError) {
        console.log("index", index)
        return { count: commandeListResponse.count, commandes: commandeListResponse.items }
      }
      else {
        throw new Error(commandeListResponse.status.message)
      }
    }

    //Mapped Datas
     /* return new Observable<GetCommandsListResponseApi>(subscriber => {
      subscriber.next(commandeListResponse as GetCommandsListResponseApi)
    }).pipe(
      delay(2000),
map(mapCallBack)
    ) */ 


    return this.restApiClientService.executeDashboardCommande<GetCommandsListResponseApi>(
    "commande/getByCriteria",
    {
        index,
    size,
    trackTotalHits: true,
    showMetaDatas: true,
    ...(startDate && endDate && {
    data: {
    date_debut: startDate,
    date_debut_param: {
    operator: "[]",
    start: startDate,
    end: endDate
    },
    libelle: libelle,
    status: status
    }
        })
    }
    ).pipe(
    retry(3),
      map(mapCallBack)
    )
  }

  getCommandeDetails(commande: CommandsListCommand): Observable<CommandeDetails[]> {

    const mapCallBack = (commandeDetailsResponse: GetCommandeDetailsResponseApi) => {
      if (!commandeDetailsResponse.hasError) {
        return commandeDetailsResponse.items
      }
      else {
        throw new Error(commandeListResponse.status.message)
      }
    }

    //Mapped Datas
     /* return new Observable<GetCommandeDetailsResponseApi>(subscriber=>{
      subscriber.next(commandeDetailsResponse as GetCommandeDetailsResponseApi)
    }).pipe(
      delay(1000),
map(mapCallBack)
    ) */

    return this.restApiClientService.executeDashboardCommande<GetCommandeDetailsResponseApi>(
    "stepCommande/getByCriteria",
    {
        trackTotalHits: true,
    showMetaDatas: true,
    data: { commande_id: commande._id }
    }
    ).pipe(
    retry(3),
map(mapCallBack)
    )
  }

  private formatRangeDatesFrString(rangeDates: RangeDates) {
    return {
      startDate: rangeDates.start ? rangeDates.start.format('DD/MM/YYYY HH:mm') : null,
      endDate: rangeDates.end ? rangeDates.end.format('DD/MM/YYYY HH:mm') : null
    }
  }

  getColorByStatus(status: StatusesKeys) {
    switch (status) {
      case 'ERROR':
        return '#cc2525';
      case 'SUCCESS':
        return '#2aa811'
      default:
        return undefined
    }
  }

  getStatuByColors(color: any){
    switch(color){
      case '#cc2525':
        return 'ERROR';
      case '#2aa811':
        return 'SUCCESS'
      default:
        return undefined
    }
  }

  setCommandeDatas(value: any){
    this.commandes.next(value)
  }

  getCommandeDatas(){
    return this.commandes.asObservable()
  }

  setChartDetails(value: {libelle: any, status: any}){
    this.chart.next(value)
  }

  getChartDetails(){
    return this.chart.asObservable()
  }

  setChartClick(value: boolean){
    this.chartClick.next(value)
  }

  getChartClick(){
    return this.chartClick.asObservable()
  }

  getValueChartClick(){
    return this.chartClick.value
  }

  setNewIndex(){
    this.newIndex.next(0)
  }

  getNewIndex(){
    return this.newIndex.asObservable()
  }
}
