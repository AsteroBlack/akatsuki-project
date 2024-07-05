import {  RangeDates } from './../reporting.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ReportingService } from '../reporting.service';
import moment, { Moment } from 'moment';
import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { ToastrService } from 'ngx-toastr';
import { StatusesKeys } from './interfaces/reporting-status.interface';

const MY_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'DD',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-reporting',
  templateUrl: './reporting.component.html',
  styleUrls: ['./reporting.component.scss'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    { provide: MAT_DATE_LOCALE, useValue: 'fr' },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
  ],
})
export class ReportingComponent implements OnInit {
  rangeDates = new FormGroup({
    start: new FormControl(null, Validators.required),
    end: new FormControl(null, Validators.required),
  });

  statusesDetails: {
    detailsTitle: string;
    plateformsTitle: string;
    key: StatusesKeys,
  }[] = [
      {
        detailsTitle: 'Détails des succès',
        plateformsTitle: 'Cas de succès par plateforme',
        key: 'SUCCESS'
      },
      {
        detailsTitle: 'Détails des erreurs',
        plateformsTitle: 'Cas d\'erreurs par plateforme',
        key: 'ERROR'
      }
    ]

  constructor(private reportingService: ReportingService, private toastService: ToastrService) { }

  ngOnInit(): void {
    this.emitDefaultDate();
  }

  emitDefaultDate(){
    this.rangeDates.get('start')?.setValue(moment().subtract(7, 'days'))
    this.rangeDates.get('end')?.setValue(moment());
    this.onDateChange();
  }

  onDateChange() {
    if (this.rangeDates.valid) {
      this.reportingService.updateRangeDates(this.formatRangeDatesForm())
    }
  }

  formatRangeDatesForm(): RangeDates {
    const values = this.rangeDates.value as { start: Moment, end: Moment }
    let rangeDates: RangeDates = {
      start: null,
      end: null
    }
    if (this.rangeDates.valid) {
      rangeDates = {
        start: values.start,
        end: values.end.hours(23).minutes(59)
      }
    }

    return rangeDates
  }
}
