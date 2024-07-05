import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'reporting-chart-loader',
  templateUrl: './chart-loader.component.html',
  styleUrls: ['./chart-loader.component.scss']
})
export class ChartLoaderComponent implements OnInit, OnDestroy {
  loader: string='.'
  interval: any
  constructor() { }

  ngOnInit(): void {
    this.interval = setInterval(()=>{
      if(this.loader ==='...'){
        this.loader = '.'
      }
      else{
        this.loader+='.'
      }
    }, 1000)
  }
  ngOnDestroy(): void {
    clearInterval(this.interval)
  }
}
