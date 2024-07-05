import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CommandsListCommand } from '../../interfaces/command-list.interface';
import { ReportingService } from 'src/app/reporting/reporting.service';
import { ToastrService } from 'ngx-toastr';
import { NgxTimelineEvent, NgxTimelineEventChangeSideInGroup } from '@frxjs/ngx-timeline';
import { CommandeDetails } from '../../interfaces/command-details.interface';
import moment from 'moment';

/**
 * Iterface for @frxjs/ngx-timeline configurations.
 * Get more infos [here](https://github.com/emanuelefricano93/frxjs-Ngx-Timeline#configuration)
 */
interface TimeLineConfigInterface {
  langCode: 'en' | 'it' | 'fr' | 'de' | 'es' | 'sl' | 'pt';
  enableAnimation: boolean;
  changeSideInGroup: NgxTimelineEventChangeSideInGroup;
  events: NgxTimelineEvent[];
}

interface CustomNgxTimelineEvent extends NgxTimelineEvent {
  status?: string
}

@Component({
  selector: 'app-modal-commande-details',
  templateUrl: './modal-commande-details.component.html',
  styleUrls: ['./modal-commande-details.component.scss']
})
export class ModalCommandeDetailsComponent implements OnInit {
  changeSideInGroup: NgxTimelineEventChangeSideInGroup;
  timelineConfig: TimeLineConfigInterface = {
    langCode: 'fr',
    enableAnimation: false,
    changeSideInGroup: NgxTimelineEventChangeSideInGroup.ON_DIFFERENT_DAY,
    events: []
  }
  loading: boolean = false;

  showDetails = false;
  commandeDetails: CommandeDetails[]
  detailToShow: CommandeDetails

  constructor(
    private dialogRef: MatDialogRef<ModalCommandeDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) private data: { commande: CommandsListCommand },
    private reportingService: ReportingService,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getCommandeDetails()
  }

  onSelectDetail(id: string) {
    const detailToShow = this.commandeDetails.find(commandeDetail => commandeDetail._id === id)
    if (detailToShow) {
      this.detailToShow = detailToShow
      this.showDetails = true;
    }
    else {
      console.log("Details ID not found", `Details with ID:${id} not found in this commande details`)
    }
  }

  onBack() {
    this.showDetails = false;
  }

  getCommandeDetails() {
    this.loading = true;
    this.reportingService.getCommandeDetails(this.data.commande).subscribe({
      next: (details) => {
        this.loading = false;
        this.commandeDetails = details
        const detailsTimelineEvent: CustomNgxTimelineEvent[] = details.map(detail => {
          try {
            const formatedDate = this.formatDate(detail.date_debut)
            return {
              timestamp: formatedDate,
              title: detail.libelle,
              description: `${detail.status}`,
              id: detail._id,
              status: detail.status
            }
          }
          catch (error) {
            this.toastService.error("Datetime Bad format", `The commande detail with id ${detail._id} haven't a good datetime format dd/mm/yyy hh:mm:ss his datetime is ${detail.date_debut}`)
            return { id: null }
          }
        }).filter(detail => detail.id !== null)

        this.timelineConfig.events = detailsTimelineEvent
      },
      error: (error) => {
        this.loading = false;
        this.toastService.error("Erreur", error)
      }
    })
  }

  formatDate(initialDateTime: string): Date {
    try {
      const formatedDate = moment(initialDateTime, "DD/MM/YYYY hh:mm:ss").format("YYYY-MM-DDTHH:mm:ss");
      return new Date(formatedDate);
    }
    catch (e) {
      throw new Error("Bad datetime format");
    }
  }

  formatFullDateTime(datetime: string){
    return moment(datetime).locale("fr").format("dddd DD MMMM YYYY HH:mm:ss")
  }

  formatDateInMonthYear(datetime: string){
    const stringDate = moment(datetime).locale("fr").format("MMMM YYYY");
    return stringDate.charAt(0).toUpperCase() + stringDate.slice(1)
  }

  formatHourDateTime(hour: string){
    return moment(hour).locale("fr").format("HH:mm:ss")
  }

  onCLose() {
    this.dialogRef.close()
  }

}
