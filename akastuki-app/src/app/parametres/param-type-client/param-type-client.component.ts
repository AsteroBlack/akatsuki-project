import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { RestApiClientService } from 'src/app/shared/services/rest-api-client.service';
import { UserService } from 'src/app/shared/services/user.service';
import Swal from 'sweetalert2';
import { ModalParamTypeClientComponent } from './modal-param-type-client/modal-param-type-client.component';

@Component({
  selector: 'app-param-type-client',
  templateUrl: './param-type-client.component.html',
  styleUrls: ['./param-type-client.component.scss']
})
export class ParamTypeClientComponent implements OnInit {
  page: any = {
    index: 0,
    size: 10,
    total: 0
  };
  ParamsTypeClientArrayColumn = [
    'num',
    'libelle',
    'codeSegment',
    // 'isUsed',
    'options',
    // 'datasRole'
  ];
  searchData: any = {
    code: '',
    libelle: '',
    codeSegment: '',
  };
  listeParamsTypeClient: any[] = [];
  constructor(
    private restApi: RestApiClientService,
    private userService: UserService,
    public dialog: MatDialog,
    private toastService: ToastrService,
  ) { }

  ngOnInit(): void {
    this.getParamsTypeClient();
  }

  paginationChange(event: PageEvent){
    console.log('page', event);
    this.page.index = event.pageIndex;
    this.page.size = event.pageSize;
    this.getParamsTypeClient();
  }

  rechercher(){
    this.getParamsTypeClient(this.searchData);
  }

  applyFilter(event: any){
    console.log('filtre', event);
  }

  getParamsTypeClient(param?: any) {
    // const methode = 'costomer-back-end-api-1.0/paramTypeClient/getByCriteria';
    // const methode = 'api-customer-care-fixe/getTypeClientCriteria';
    const methode = 'paramTypeClient/getByCriteria';
    const data = {
      serviceLibelle: `Consultation de la liste des params type carte`,
      index: this.page.index,
      size: this.page.size,
      data: {
        libelle: param && param.libelle ? param.libelle : '',
        codeSegment: param && param.codeSegment ? param.codeSegment : ''
      },
      orderBy: 'desc',
      takeAll: true
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      if (res && !res.hasError) {
        this.listeParamsTypeClient = res && res.items ? res.items : [];
        this.page.total = res.count;
        // this.activedId = 'panel-3';
      } else {
        this.toastService.error('Opération échouée', res && res.status && res.status.message ? res.status.message : 'impossible de recupérer les params type carte');
      }
    }, (err: any) => {
      this.toastService.error('Erreur', 'Problême de communication');
    });
  }

  openModal(param?: any){
    const dialogRef = this.dialog.open(ModalParamTypeClientComponent, {
      width: '600px',
      disableClose: true,
      data: param
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log('The dialog was closed', result);
      this.getParamsTypeClient();
      // this.animal = result;
    });
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
        this.deleteAction(item);
      } else if (result.isDenied) {
        // Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

  deleteAction(param?: any) {
    const methode = 'paramTypeClient/delete';
    const data = {
      
      datas: [{
        id: param && param.id ? param.id : '',
      }]
    };
    console.log('data', data);
    this.restApi.executeRessources(methode, data).subscribe((res: any) => {
      console.log('res', res);
      if (res && !res.hasError) {
        this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
        this.getParamsTypeClient();
      } else {
        this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors de la recupération des données');
        // this.listeUser = [];
      }
    }, (err: any) => {
      this.toastService.error('Opération échouée', 'Problême de communication');
    });
  }



}
