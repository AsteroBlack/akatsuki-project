import { Status } from '../interfaces/status.interface';
import { catchError, map, retry, switchMap, tap } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';

import { ReportingService } from '../../reporting.service';
import * as Highcharts from 'highcharts';
import { ToastrService } from 'ngx-toastr';
import { Subscription, of } from 'rxjs';

@Component({
  selector: 'app-provisioning-status-chart',
  templateUrl: './provisioning-status-chart.component.html',
  styleUrls: ['./provisioning-status-chart.component.scss']
})
export class ProvisioningStatusChartComponent implements OnInit {
  highCharts = Highcharts
  chartConstructor = "chart"
  updateFlag: boolean = false;
  oneToOneFlag: boolean = true;
  chartOptions: Highcharts.Options = {
    chart: {
      // plotBackgroundColor: ,
      // plotBorderWidth: ,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Nombre de provisionning par status'
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
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          // format: '<b>{point.name}</b>: {point.percentage:.1f} %'
        },
        events: {
          // @ts-ignore
          // click: this.onSelectStatus.bind(this)
        }
      }
    }
  }
  loading = false
  rangeDatesSubscription: Subscription

  chartCallback: Highcharts.ChartCallbackFunction = function (chart) {
    // console.log('callback', chart)
  }

  constructor(private reportingService: ReportingService, private toastService: ToastrService) { }

  ngOnInit(): void {
    this.rangeDatesSubscription = this.reportingService.rangeDates$.pipe(
      tap(_ => {
        this.loading = true
      }),
      switchMap(rangeDates => this.reportingService.getStatuses(rangeDates).pipe(
        catchError(error => {
          this.toastService.error(error, 'Erreur');
          return of([])
        })
      )),
      map<Status[], Array<(number | [string, (number | null)] | null | Highcharts.PointOptionsObject)>>(statuses => statuses.map(status => ({
        id: status.key,
        name: status.key,
        y: Number(status.value),
        color: this.reportingService.getColorByStatus(status.key)
      }))),
      tap(_ => {
        this.loading = false
      })
    ).subscribe({
      next: dataStatuses => {
        this.initChart(dataStatuses)
      }
    })
  }

  ngOnDestroy(): void {
    this.rangeDatesSubscription.unsubscribe()
  }

  onSelectStatus(event: Highcharts.SeriesClickEventObject) {
    // @ts-ignore
    this.reportingService.updateSelectedStatus(event.point.id)
  }

  //Init Chart with its series datas
  initChart(data: Array<(number | [string, (number | null)] | null | Highcharts.PointOptionsObject)>) {
    this.chartOptions = {
      ...this.chartOptions,
      series: [
        {
          name: 'Nombre',
          colorByPoint: true,
          type: 'pie',
          data: data
        }
      ]
    }
  }
}
