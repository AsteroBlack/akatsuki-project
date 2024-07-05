import { ModalCommandeDetailsComponent } from './modal-commande-details/modal-commande-details.component';
import { Component, OnInit, ViewChild, Pipe, OnDestroy } from '@angular/core';

import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

import { CommandsListCommand } from '../interfaces/command-list.interface';
import { CommandeListObservableEmission, RangeDates, ReportingService } from '../../reporting.service';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';
import { Observable, Subject, Subscription, concat, of } from 'rxjs';
import { catchError, map, switchMap, tap } from 'rxjs/operators';

interface SubscriptionInitialValueEmition {
  type: SubscriptionInitialValueEmitionType,
  value: number | RangeDates
}

enum SubscriptionInitialValueEmitionType{
  RANGE_DATE,
  INDEX_PAGE
}

@Component({
  selector: 'app-commandes-list',
  templateUrl: './commandes-list.component.html',
  styleUrls: ['./commandes-list.component.scss']
})
export class CommandesListComponent implements OnInit, OnDestroy {
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;
  commandesDataSource = new MatTableDataSource<CommandsListCommand>([]);
  showedCommandeId: null | string = null
  commandesColumns = [
    "commande"
  ]
  loading: boolean;
  pageSizeOptions = [10, 20, 50]
  page = {
    index: 0,
    size: this.pageSizeOptions[0],
    total: 0
  }
  currentRangeDate: RangeDates;
  pageChangeSubject = new Subject<number>();
  getCommandeListDatasSubscription: Subscription;
  sharedDatas= []
  
  constructor(private reportingService: ReportingService, private toastService: ToastrService, private dialog: MatDialog) { 
     reportingService.getNewIndex().subscribe((value) =>{
       this.page.index= value
     })

    // reportingService.getChartClick().subscribe((value) =>{
    //   this.page.index= value.index
    // })
  }

  ngOnInit(): void {
    this.getDatas()
    console.log("rechargement")
  }

  ngOnDestroy(): void {
    this.getCommandeListDatasSubscription.unsubscribe();
  }

  showCommandeDetails(commande: CommandsListCommand) {
    this.dialog.open(ModalCommandeDetailsComponent, {
      width: '600px',
      disableClose: true,
      data: {
        commande
      }
    });
  }

  showCommande(commande: CommandsListCommand) {
    return this.showedCommandeId === commande._id
  }

  toggleVisibilityCommande(commande: CommandsListCommand) {
    if (commande._id === this.showedCommandeId) {
      this.showedCommandeId = null
    }
    else {
      this.showedCommandeId = commande._id
    }
  }

  checkCommandeHaveRequests(commande: CommandsListCommand) {
    return (typeof commande.requestValue != "string" && commande.requestValue?.parameters && commande.requestValue.parameters.length >= 1) ? true : false
  }

  checkCommandeHaveResponses(commande: CommandsListCommand) {
    return (typeof commande.responseValue != "string" && commande.responseValue?.parameters && commande.responseValue.parameters.length >= 1) ? true : false
  }

  convertObjectToString(value: {}|string){
    return JSON.stringify(value)
  }

  getDatas(libelle?: any, status?: any){
    console.log('Im getting data')

    this.getCommandeListDatasSubscription = concat(
      this.reportingService.rangeDates$.pipe(
        map((res): SubscriptionInitialValueEmition=>({
          type: SubscriptionInitialValueEmitionType.RANGE_DATE,
          value: res
        }))
      ),
      this.pageChangeSubject.pipe(
        map((res): SubscriptionInitialValueEmition=>({
          type: SubscriptionInitialValueEmitionType.INDEX_PAGE,
          value: res
        }), console.log("test"))
      )
    ).pipe(
      tap(response=>{
        console.log('yop', response)
        this.commandesDataSource = new MatTableDataSource<CommandsListCommand>([])
        this.loading = true;

        switch(response.type){
          case SubscriptionInitialValueEmitionType.RANGE_DATE:
          this.currentRangeDate = response.value as RangeDates;
          break;
          case SubscriptionInitialValueEmitionType.INDEX_PAGE:
          this.page.index = response.value as number;
          break;
        }
      }),
      switchMap(() => this.reportingService.getCommandeListDatas(
        this.currentRangeDate, 
        this.page.index, 
        this.page.size, 
        libelle, 
        status)
        .pipe(
        tap(res => {
          console.log(`libelle_1: ${libelle}, status_1: ${status}`)
        }),
        catchError(error => {
          this.toastService.error(error, 'Erreur récupération liste des commandes');
          return of<CommandeListObservableEmission>({count: 0, commandes: []})
        })
      ))
    ).subscribe({
      next: (res: CommandeListObservableEmission) => {
        this.page.total = res.count
        this.reportingService.setCommandeDatas(res.commandes)
        this.reportingService.getCommandeDatas().subscribe((data) =>{
          console.log(`libelle_2: ${libelle}, status_2: ${status}`)
          console.log("commandesData commande list comp", data)
          this.sharedDatas= data
          this.commandesDataSource = new MatTableDataSource<CommandsListCommand>(this.sharedDatas)
        })
        this.loading = false
      },
      error: (error: any) => {
        this.toastService.error(error, "Erreur");
        this.loading = false
      }
    })
  }

  reset(){
    this.reportingService.setChartClick(false)
    //this.page.index= 0
  }

  paginationChange(event: PageEvent) {
    console.log('page', event)
    console.log("page index", event.pageIndex)
    const chartClick= this.reportingService.getValueChartClick()
    if(chartClick == true){
      console.log("value false", chartClick)
      this.page.index= 1
      console.log("prev and next", event.previousPageIndex, event.pageIndex) 
    }else{
      console.log("value true", chartClick)
      this.page.index= event.pageIndex
    }
    console.log("index base", this.reportingService.getValueChartClick())

    //this.pageChangeSubject.next(event.pageIndex)
    this.reportingService.getChartDetails().subscribe((chart) => {
      this.getDatas(chart.libelle, chart.status)
      console.log("chart", chart)
    })
    this.reset()
  }

}
