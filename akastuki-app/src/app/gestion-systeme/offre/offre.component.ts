import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { OfferService } from 'src/app/core/services/offer.service';
import { IOffre } from 'src/app/shared/interfaces/offre.interface';
import Swal from 'sweetalert2';
import { ActionTypes, ModalOffreComponent } from './modal-offre/modal-offre.component';
import { UserService } from 'src/app/shared/services/user.service';
@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.scss']
})
export class OffreComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator | null  = null;
  offerActionTypes = ActionTypes
  offersDataSource = new MatTableDataSource<IOffre>([]);
  offersColumns = [
    'offer'
  ]
  offres: IOffre[] = []
  searchData = {
    codeOffer: '',
    codeTypeClient: ''
  };
  showedOffreId: null | number = null
  page = {
    index: 0,
    size: 2,
    total: 0
  };
  modulesArrayColumn = [
    'num',
    'userCompte',
    'securityPassword',
    'securityToken',
    'createdAt',
    'options'
  ];

  //permissions properties
  canCreateOffer = false;
  canUpdateOffer = false;
  canDeleteOffer = false;
  unAuthorizedMessage = "Vous n'êtes pas autorisé à réaliser cette action";

  constructor(
    public dialog: MatDialog,
    private offreService: OfferService,
    private toastService: ToastrService,
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.definePermissions()
    this.getOffres()
  }

  definePermissions(){
    this.canCreateOffer = this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'OFFRES', 'CREER_OFFRE')
    this.canUpdateOffer = this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'OFFRES', 'MODIFIER_OFFRE')
    this.canDeleteOffer = this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'OFFRES', 'SUPPRIMER_OFFRE')
  }

  showUnauthorizedMessage(){
    this.toastService.error('Erreur', this.unAuthorizedMessage);
  }

  getOffres() {
    this.offreService.get(this.searchData, this.page.index, this.page.size).subscribe({
      next: (response) => {
        if (!response.hasError) {
          this.offres = response.items
          this.offersDataSource  = new MatTableDataSource<IOffre>(response.items)
          this.offersDataSource.paginator  = this.paginator
          // this.page.total = response.count
        } else {
          this.toastService.error('Oups !', (response && response.status && response.status.message) ? response.status.message : 'Impossible de recupérer les comptes');
        }
      },
      error: (error) => {
        console.error("Erreur de récupération des comptes", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
        throw new Error(`Erreur sur la récupération des comptes ${JSON.stringify(error)}`)
      }
    })
    // this.restApi.execute(methode, data).subscribe((res: any) => {
    //   if (res && !res.hasError) {
    //     this.listeComptes = res && res.items ? res.items : [];

    //     console.log('illl', this.listeComptes)
    //     // this.total = res.count;
    //     // this.activedId = 'panel-3';
    //   } else {
    //     this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'Impossible de recupérer les comptes');
    //   }
    // }, (err) => {
    //   this.toastService.error('Erreur', 'Problême de communication');
    // });
  }

  rechercher() {
    this.getOffres()
  }

  applyFilter(event: any) {
    console.log('filtre', event);
  }

  openModal(actionType: ActionTypes, offer?: IOffre) {
    const dialogRef = this.dialog.open(ModalOffreComponent, {
      width: '600px',
      disableClose: true,
      data: {
        offer,
        actionType
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      this.getOffres();
      // this.animal = result;
    });
  }
  paginationChange(event: PageEvent) {
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.getOffres();
  }
  askBeforeDelete(offreId: number) {
    if(this.canDeleteOffer){
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
    else{
      this.showUnauthorizedMessage();
    }
  }

  delete(offreId: number) {
    if(this.canDeleteOffer){
      console.log('param sup', offreId)
      const deleteCompteRequest = this.offreService.delete(offreId).subscribe({
        next: (response) => {
          if (!response.hasError) {
            this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
            this.getOffres();
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
    else{
      this.showUnauthorizedMessage();
    }
  }

  toggleOffreDto(offreId: number){
    if(offreId === this.showedOffreId){
      this.showedOffreId = null
    }
    else{
       this.showedOffreId = offreId
    }
  }

  showOffre(offre: IOffre){
    return this.showedOffreId === offre.id
  }

  formatDescriptionForTable(offer: IOffre){
    if(offer.description){
      let descContent = offer.description.slice(0, 60)
      if(offer.description.length>60){
        descContent+='...'
      }
      return descContent
    }
    else{
      return ''
    }
  }

}
