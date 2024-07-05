import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { ModalComptesComponent } from './modal-comptes/modal-comptes.component';
import Swal from 'sweetalert2';
import { ICompte } from 'src/app/shared/interfaces/compte.interface';
import { CompteService } from '../../core/services/compte.service';

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.scss']
})
export class ComptesComponent implements OnInit {

  page: any = {
    index: 1,
    size: 10,
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
  searchData: any = {
    userCompte: ''
  };
  listeComptes: ICompte[] = [];

  constructor(
    private restApi: RestApiClientService,
    public dialog: MatDialog,
    private toastService: ToastrService,
    private compteService: CompteService
  ) { }

  ngOnInit(): void {
    this.getComptes()
  }

  getComptes() {
    const methode = 'compte/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des comptes`,
      ndCode: '',
      customerCode: '',
      user: /*this.userGetData ? this.userGetData.id : */'1118',
      data: {
        libelle: ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    const getCompteRequest = this.compteService.get().subscribe({
      next: (response) => {
        if (!response.hasError) {
          this.listeComptes = response.items
        } else {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de recupérer les comptes');
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

  }

  applyFilter(event: any) {
    console.log('filtre', event);
  }

  openModal(param?: any) {
    const dialogRef = this.dialog.open(ModalComptesComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      this.getComptes();
      // this.animal = result;
    });
  }

  paginationChange(event: PageEvent) {
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.getComptes();
  }
  askBeforeDelete(item: any) {
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
        this.delete(item);
      } else if (result.isDenied) {
        // Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

  delete(param: any) {
    console.log('param sup', param)
    const deleteCompteRequest = this.compteService.delete(param).subscribe({
      next: (response) => {
        if (!response.hasError) {
          this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
          this.getComptes();
        } else {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de supprimer le compte');
        }
      },
      error: (error) => {
        console.error("Erreur de suppression de compte", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
        throw new Error(`Erreur sur la suppression de compte ${JSON.stringify(error)}`)
      }
    })
  }

}
