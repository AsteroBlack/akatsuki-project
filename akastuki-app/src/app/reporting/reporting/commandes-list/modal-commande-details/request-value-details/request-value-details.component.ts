import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { CommandeDetails } from '../../../interfaces/command-details.interface';

@Component({
  selector: 'reporting-request-value-details',
  templateUrl: './request-value-details.component.html',
  styleUrls: ['./request-value-details.component.scss']
})
export class RequestValueDetailsComponent {
  @Output() closeDetails = new EventEmitter<boolean>()
  @Input() commandeDetails: CommandeDetails

  showResponseValue(){
    return JSON.stringify(this.commandeDetails.requestValue, undefined, 2)
  }

  convertObjectToString(value: any){
    return JSON.stringify(value, undefined, 2)
  }
}
