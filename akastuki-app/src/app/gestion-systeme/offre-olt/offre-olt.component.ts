import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { OfferOltService } from 'src/app/core/services/offer-olt.service';
import { IOffreOLT } from 'src/app/shared/interfaces/offreOLT.interface';
import Swal from 'sweetalert2';
import { ActionTypes, ModalOffreOltComponent } from './modal-offre-olt/modal-offre-olt.component';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-offre-olt',
  templateUrl: './offre-olt.component.html',
  styleUrls: ['./offre-olt.component.scss']
})
export class OffreOltComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;

  searchData = {
    template: '',
    rx: '',
    tx: ''
  };
  offersColumns = [
    'num',
    'offer',
    'rx',
    'tx',
    'options',
  ];
  /* page = {
    index: 1,
    size: 10,
    total: 0
  }; */
  // offers: IOffreOLT[] = []
  offersDataSource = new MatTableDataSource<IOffreOLT>([])
  offerActionTypes = ActionTypes

  //permissions properties
  canCreateOfferOLT = false;
  canUpdateOfferOLT = false;
  canDeleteOfferOLT = false;
  unAuthorizedMessage = "Vous n'êtes pas autorisé à réaliser cette action";

  constructor(
    public dialog: MatDialog,
    private offerOltService: OfferOltService,
    private toastService: ToastrService,
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.definePermissions()
    this.getOffers()
  }

  definePermissions() {
    this.canCreateOfferOLT = this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'PARAMETRES_GENERAUX', 'CREER_OLT')
    this.canUpdateOfferOLT = this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'PARAMETRES_GENERAUX', 'MODIFIER_OLT')
    this.canDeleteOfferOLT = this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'PARAMETRES_GENERAUX', 'SUPPRIMER_OLT')
  }

  showUnauthorizedMessage() {
    this.toastService.error('Erreur', this.unAuthorizedMessage);
  }

  getOffers() {
    this.offerOltService.get(this.searchData).subscribe({
      next: (response) => {
        if (!response.hasError) {
          // this.offers = response.items
          this.offersDataSource = new MatTableDataSource<IOffreOLT>(response.items);
          this.offersDataSource.paginator = this.paginator
        } else {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de recupérer les comptes');
        }
      },
      error: (error) => {
        console.error("Erreur de récupération des comptes", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
      }
    })
  }

  openEditModal(actionType: ActionTypes, offer?: IOffreOLT) {
    const dialogRef = this.dialog.open(ModalOffreOltComponent, {
      width: '600px',
      disableClose: true,
      data: {
        offer,
        actionType
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      this.getOffers();
      // this.animal = result;
    });
  }

  askBeforeDelete(offreId: number) {
    if (this.canDeleteOfferOLT) {
      Swal.fire({
        title: '<div style="color:#fff">Attention!</div>',
        html: '<div style="color:#fff">Vous êtes sur le point de supprimer cet element. Continuer?</div>',
        showCancelButton: true,
        confirmButtonColor: '#dc3545',
        confirmButtonText: `Supprimer`,
        cancelButtonText: `Annuler`,
        background: '#000',

      }).then((result: any) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
          // Swal.fire('Saved!', '', 'success')
          this.delete(offreId);
        } else if (result.isDenied) {
          // Swal.fire('Changes are not saved', '', 'info')
        }
      })
    }
    else {
      this.showUnauthorizedMessage()
    }
  }

  delete(offreId: number) {
    console.log('param sup', offreId)
    if (this.canDeleteOfferOLT) {
      this.offerOltService.delete(offreId).subscribe({
        next: (response) => {
          if (!response.hasError) {
            this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
            this.getOffers();
          } else {
            this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de supprimer cette offre');
          }
        },
        error: (error) => {
          console.error("Erreur de suppression d'offre", JSON.stringify(error))
          this.toastService.error('Erreur', 'Un problème est survenu');
          throw new Error(`Erreur sur la suppression d'offre ${JSON.stringify(error)}`)
        }
      })
    }
    else {
      this.showUnauthorizedMessage()
    }
  }

  applyFilter(event: any) {
    console.log('filtre', event);
  }

  rechercher() {
    this.getOffers()
  }
  /*  paginationChange(event: PageEvent) {
     console.log('page', event);
     this.page.index = event.pageIndex;
     this.getOffers();
   } */
}
