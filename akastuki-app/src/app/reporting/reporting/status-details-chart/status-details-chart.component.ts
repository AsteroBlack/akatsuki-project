import { Command } from './../interfaces/command.interface';
import { catchError, map, switchMap, tap } from 'rxjs/operators';
import { CommandeListObservableEmission, RangeDates, ReportingService } from './../../reporting.service';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
import { ToastrService } from 'ngx-toastr';
import { Subscription, of, concat, Subject } from 'rxjs';
import { StatusesKeys } from '../interfaces/reporting-status.interface';
import { Plateform } from '../interfaces/plateform.interface';

type ChartData = Array<(number | [string, (number | null)] | null | Highcharts.PointOptionsObject)>

interface SubscriptionInitialValueEmition {
  type: SubscriptionInitialValueEmitionType,
  value: number | RangeDates
}

enum SubscriptionInitialValueEmitionType{
  RANGE_DATE,
  INDEX_PAGE
}

@Component({
  selector: 'app-status-details-chart',
  templateUrl: './status-details-chart.component.html',
  styleUrls: ['./status-details-chart.component.scss']
})
export class StatusDetailsChartComponent implements OnInit, OnDestroy {
  @Input() detailsTitle: string
  @Input() plateformsTitle: string
  @Input() key: StatusesKeys

  highCharts = Highcharts
  chartConstructor = "chart"
  updateFlag: boolean = false;
  oneToOneFlag: boolean = true;
  chartPlateformsOptions: Highcharts.Options = {
    chart: {
      // plotBackgroundColor: ,
      // plotBorderWidth: ,
      plotShadow: false,
      type: 'column'
    },
    title: {
      text: ''
    },
    legend: {
      enabled: false
    },
    xAxis: {
      type: 'category'
    },
    /* tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    }, */
    /* accessibility: {
      point: {
        valueSuffix: '%'
      }
    }, */
    plotOptions: {
      column: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          // format: '<b>{point.name}</b>: {point.percentage:.1f} %'
        }
      }
    }
  }
  loadingPlateforms = false;
  chartCommandsOptions: Highcharts.Options = {
    chart: {
      // plotBackgroundColor: ,
      // plotBorderWidth: ,
      plotShadow: false,
      type: 'column'
    },
    title: {
      text: ''
    },
    legend: {
      enabled: false
    },
    xAxis: {
      type: 'category'
    },
    /* tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    }, */
    /* accessibility: {
      point: {
        valueSuffix: '%'
      }
    }, */
    plotOptions: {
      column: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          // format: '<b>{point.name}</b>: {point.percentage:.1f} %'
        },
        events: {
          // @ts-ignore
          click: this.onSelectStatus.bind(this)
        }
      }
    }
  }
  loadingCommands = false;
  rangeDatesSubscriptions: Subscription[] = []
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
  tabTest= [
    {
      "_id": "RSdns4cBCFhrXk0lQNbC",
      "_index": "commande_202304",
      "date_debut": "24/04/2023 13:15:01",
      "date_fin": "24/04/2023 13:15:03",
      "libelle": "activate",
      "reference": "79ceb2ad-f35f-445d-b3d9-b5820a9655a2",
      "requestValue": {
        "parameters": [
          {
            "key": "offer",
            "value": "BFESS"
          },
          {
            "key": "serialNumber",
            "value": "ZTEGCFA9987F"
          },
          {
            "key": "port",
            "value": "OZAC401/0/7/1:49"
          },
          {
            "key": "nd",
            "value": "22625671450"
          },
          {
            "key": "ontMacLabel",
            "value": "54CE827A417A"
          },
          {
            "key": "accountType",
            "value": "postpaid"
          },
          {
            "key": "ipAddressCount",
            "value": "1"
          }
        ]
      },
      "responseValue": {
        "parameters": [
          {
            "key": "ipAddress",
            "value": "102.180.65.2"
          },
          {
            "key": "mask",
            "value": "255.255.255.0"
          }
        ]
      },
      "status": "SUCCESS",
      "user": "Customer Care Account"
    }
  ]


  constructor(private reportingService: ReportingService, private toastService: ToastrService) {

  }

  ngOnInit(): void {
    this.rangeDatesSubscriptions[0] = this.reportingService.rangeDates$.pipe(
      tap(_ => {
        this.loadingPlateforms = true
      }),
      switchMap(rangeDates => this.reportingService.getPlateformsDatas(rangeDates, this.key).pipe(
        catchError(error => {
          this.toastService.error(error, 'Erreur');
          return of([])
        })
      )),
      map<Plateform[], ChartData>(plateforms => plateforms.map(data => ({
        drilldown: data.key,
        name: data.key,
        y: Number(data.value),
        color: this.reportingService.getColorByStatus(this.key)
      }))),
      tap(_ => {
        this.loadingPlateforms = false
      })
    ).subscribe({
      next: data => {
        this.initPlateformsChart(data)
      }
    })

    this.rangeDatesSubscriptions[1] = this.reportingService.rangeDates$.pipe(
      tap(_ => {
        this.loadingCommands = true
      }),
      switchMap(rangeDates => this.reportingService.getCommandsDatas(rangeDates, this.key).pipe(
        catchError(error => {
          this.toastService.error(error, 'Erreur');
          return of([])
        })
      )),
      map<Command[], ChartData>(commands => commands.map(data => ({
        drilldown: data.key,
        name: data.key,
        y: Number(data.value),
        color: this.reportingService.getColorByStatus(this.key)
      }))),
      tap(_ => {
        this.loadingCommands = false
      })
    ).subscribe({
      next: data => {
        console.log("data",data)
        this.initCommandsChart(data)
      }
    })
  }

  ngOnDestroy(): void {
    this.rangeDatesSubscriptions.forEach(rangeDatesSubscription => {
      rangeDatesSubscription.unsubscribe()
    });
  }

  chartCallback: Highcharts.ChartCallbackFunction = function (chart) {
    // console.log('callback', chart)
  }

  initPlateformsChart(data: ChartData) {
    this.chartPlateformsOptions = {
      ...this.chartPlateformsOptions,
      title: {
        text: this.plateformsTitle
      },
      series: [
        {
          name: 'Plateformes',
          colorByPoint: false,
          type: 'column',
          data: data
        }
      ]
    }
  }
  initCommandsChart(data: ChartData) {
    this.chartCommandsOptions = {
      ...this.chartCommandsOptions,
      title: {
        text: this.detailsTitle
      },
      series: [
        {
          name: 'Commandes',
          colorByPoint: false,
          type: 'column',
          data: data
        }
      ]
    }
  }

  getDatas(libelle: any, status: any){
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
        this.reportingService.setCommandeDatas([])
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
      switchMap(() => this.reportingService.getCommandeListDatas(this.currentRangeDate, this.page.index, this.page.size, libelle, status).pipe(
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
          console.log("commandesData status details comp", data)
        })
        this.loading = false
      },
      error: (error: any) => {
        this.toastService.error(error, "Erreur");
        this.loading = false
      }
    })
  }

      // test(){
      //   this.reportingService.setCommandeDatas(this.tabTest)
      //   this.reportingService.setChartDetails({libelle: 'activate', status: 'SUCCESS'})
      //   this.reportingService.setChartClick(true)
      //   this.reportingService.setNewIndex()
      //   //console.log("faux cris")
      //    // this.reportingService.getCommandeDatas().subscribe((data) =>{
      //    //   const datas= data
      //    //   console.log("chartTest", datas)
      //    // })
      //    this.getDatas('activate', 'SUCCESS')
      // }

  onClickCommand(param: any) {
    console.log('command clicked', param)
  }

  onSelectStatus(event: Highcharts.SeriesClickEventObject) {
    // @ts-ignore
    // this.reportingService.updateSelectedStatus(event.point.id)
    const chartName= event.point.options.name
    const chartStatus= this.reportingService.getStatuByColors(event.point.options.color)
    this.reportingService.setChartDetails({libelle: chartName, status: chartStatus})
    this.reportingService.setChartClick(true)
    this.reportingService.setNewIndex()
    this.getDatas(chartName, chartStatus)
  }

  private getChatSeriesColor() {
    return this.reportingService.getColorByStatus(this.key) ?? '#000000'
  }
}
