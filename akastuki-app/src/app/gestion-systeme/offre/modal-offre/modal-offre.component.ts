import { OfferTypeService } from './../../../core/services/offer-type.service';
import { map } from 'rxjs/operators';
import { OfferOltService } from 'src/app/core/services/offer-olt.service';
import { TypeClientService } from 'src/app/core/services/type-client.service';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, AbstractControl, ValidationErrors } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { OfferService } from 'src/app/core/services/offer.service';
import { PlateformeService } from 'src/app/core/services/plateforme.service';
import { INewOfferBody, IOffre } from 'src/app/shared/interfaces/offre.interface';
import { Plateforme } from 'src/app/shared/interfaces/plateforme.interface';
import { TypeCLient } from 'src/app/shared/interfaces/TypeClient.interface';
import { GroupRadiusService } from 'src/app/core/services/group-radius.service';
import { OfferType } from 'src/app/shared/interfaces/offreType.interface';
import { UserService } from 'src/app/shared/services/user.service';

export enum ActionTypes {
  CREATE,
  UPDATE
}

interface PlateformLink {
  id: string | number;
  label: string;
}

@Component({
  selector: 'app-modal-offre',
  templateUrl: './modal-offre.component.html',
  styleUrls: ['./modal-offre.component.scss']
})
export class ModalOffreComponent implements OnInit {
  formModal: FormGroup | null = null;
  actionTypes = ActionTypes
  plateformes: Plateforme[]
  typeClients: TypeCLient[]
  offerTypes: OfferType[]
  datasGroupePlateformeLabels: string[] = []
  oltList: PlateformLink[]
  radiusGroupList: PlateformLink[]


  //permissions properties
  canCreateOffer = false;
  canUpdateOffer = false;
  unAuthorizedMessage = "Vous n'êtes pas autorisé à réaliser cette action";

  constructor(
    public dialogRef: MatDialogRef<ModalOffreComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { offer: IOffre, actionType: ActionTypes },
    private offreService: OfferService,
    private plateformeServervice: PlateformeService,
    private typeClientService: TypeClientService,
    private offerTypeService: OfferTypeService,
    private offerOltService: OfferOltService,
    private groupeRadiusService: GroupRadiusService,
    private toastService: ToastrService,
    private userService: UserService,
  ) { }

  initForm() {
    if (this.plateformes && this.typeClients && this.oltList && this.radiusGroupList) {
      if (this.data.actionType === ActionTypes.UPDATE) {
        if (this.canUpdateOffer) {
          let offer = this.data.offer
          if (!offer.datasGroupe) {
            offer.datasGroupe = []
          }
          this.formModal = new FormGroup({
            codeOffer: new FormControl(offer.codeOffer, Validators.required),
            codeTypeClient: new FormControl(offer.codeTypeClient, [Validators.required, this.typeClientValidator.bind(this)]),
            description: new FormControl(offer.description, Validators.required),
            // idTypeOffre: new FormControl(offer.idTypeOffre, [this.offerTypeValidator.bind(this)]),
            datasGroupe: new FormArray(offer.datasGroupe?.map((dto, index) => {
              this.datasGroupePlateformeLabels[index] = this.getPlateformeType(dto.idPlateforme)!
              return new FormGroup({
                id: new FormControl(dto.id, Validators.required),
                idPlateforme: new FormControl(dto.idPlateforme, [Validators.required, this.plateformeValidator.bind(this)]),
                libelle: new FormControl(dto.libelle, Validators.required)
              })
            }))
          })
        }
        else {
          this.unAuthorizedModal()
        }
      }
      else {
        if (this.canUpdateOffer) {
          this.formModal = new FormGroup({
            codeOffer: new FormControl('', Validators.required),
            codeTypeClient: new FormControl('', [Validators.required, this.typeClientValidator.bind(this)]),
            description: new FormControl('', Validators.required),
            // idTypeOffre: new FormControl('', [this.offerTypeValidator.bind(this)]),
            datasGroupe: new FormArray([
              new FormGroup({
                idPlateforme: new FormControl('', [Validators.required, this.plateformeValidator.bind(this)]),
                libelle: new FormControl('', Validators.required)
              })
            ])
          })
        }
        else {
          this.unAuthorizedModal()
        }
      }
    }
  }

  typeClientValidator(control: AbstractControl): ValidationErrors | null {
    return this.typeClients.find(typeClient => typeClient.code === control.value)
      ? null
      : { typeClient: control.value }
  }

  offerTypeValidator(control: AbstractControl): ValidationErrors | null {
    if (control.value != '' && control.value != null) {
      return this.offerTypes.find(offertype => offertype.id === control.value)
        ? null
        : { offertype: control.value }
    }
    return null
  }

  plateformeValidator(control: AbstractControl): ValidationErrors | null {
    return this.plateformes.find(plateforme => plateforme.id === control.value)
      ? null
      : { plateforme: control.value }
  }

  getOlt() {
    this.offerOltService.get().pipe(
      map(response => {
        if (response.hasError) {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de recupérer les offres OLT');
          return []
        }
        else {
          return response.items
        }
      }),
      map(offers => offers.map((offer): PlateformLink => ({
        id: offer.id,
        label: offer.template
      })))
    ).subscribe({
      next: offers => {
        this.oltList = offers
        this.initForm()
      },
      error: error => {
        console.error("Erreur de récupération des offre OLT", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
      }
    })
  }

  getRadiusGroup() {
    this.groupeRadiusService.getKeys().pipe(
      map(response => {
        if (response.hasError) {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de recupérer les Groupe Radius');
          return []
        }
        else {
          return response.items
        }
      }),
      map(radiusGroups => radiusGroups?.map((radiusGroup): PlateformLink => ({
        id: radiusGroup.key!,
        label: radiusGroup.key
      })))
    ).subscribe({
      next: radiusGroups => {
        this.radiusGroupList = radiusGroups!
        this.initForm()
      },
      error: error => {
        console.error("Erreur de récupération des Groupes Radius", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
      }
    })
  }

  getTypeClients() {
    this.typeClientService.get().subscribe({
      next: response => {
        if (!response.hasError) {
          this.typeClients = response.items
          this.initForm()
        }
        else {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de recupérer les type clients');
        }
      },
      error: error => {
        console.error("Erreur de récupération des type clients", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
      }
    })
  }

  getOfferTypes() {
    this.offerTypeService.get().subscribe({
      next: response => {
        if (!response.hasError) {
          this.offerTypes = response.items!
          this.initForm()
        }
        else {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de recupérer les type d\'offres');
        }
      },
      error: error => {
        console.error("Erreur de récupération des type d'offres", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
      }
    })
  }

  getPlateformes() {
    this.plateformeServervice.get().subscribe({
      next: (response) => {
        if (!response.hasError) {
          this.plateformes = response.items
          this.initForm()
        } else {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de recupérer les plateformes');
        }
      },
      error: error => {
        console.error("Erreur de récupération des plateformes", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
      }
    })
  }

  get datasGroupe() {
    return this.formModal?.get('datasGroupe') as FormArray
  }

  addGroupeDto() {
    this.datasGroupe.push(
      new FormGroup({
        libelle: new FormControl('', Validators.required),
        idPlateforme: new FormControl('', [Validators.required, this.plateformeValidator.bind(this)])
      })
    )
  }
  removeGroupeDto(index: number) {
    this.datasGroupe.removeAt(index)
  }

  onChangePlateforme(index: number, value: number) {
    this.datasGroupePlateformeLabels[index] = this.getPlateformeType(value)!
  }

  getPlateformeType(id: number) {
    return this.plateformes.find(platefome => platefome.id === id)?.libelle
  }

  getLabelPlateformeLinked(index: number): string {
    switch (this.datasGroupePlateformeLabels[index]) {
      case 'olt':
        return 'offre OLT'
      case 'radius':
        return 'Groupe Radius'
      default:
        return 'Liaison'
    }
  }

  getListPlateformeLinked(index: number): any[] {
    switch (this.datasGroupePlateformeLabels[index]) {
      case 'olt':
        return this.oltList
      case 'radius':
        return this.radiusGroupList
      default:
        return []
    }
  }

  ngOnInit(): void {
    this.definePermissions()

    this.getPlateformes()
    this.getTypeClients()
    // this.getOfferTypes()
    this.getOlt()
    this.getRadiusGroup()
  }

  definePermissions() {
    this.canCreateOffer = this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'OFFRES', 'CREER_OFFRE')
    this.canUpdateOffer = this.userService.checkMenuExiste('GESTION_DES_OFFRES', 'OFFRES', 'MODIFIER_OFFRE')
  }

  showUnauthorizedMessage() {
    this.toastService.error('Erreur', this.unAuthorizedMessage);
  }

  unAuthorizedModal() {
    this.dialogRef.close();
    this.showUnauthorizedMessage()
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit() {
    if (this.data.actionType === ActionTypes.CREATE) {
      if (this.canCreateOffer) {
        this.offreService.create(this.formModal?.value as INewOfferBody).subscribe({
          next: res => {
            if (!res.hasError) {
              this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
              this.dialogRef.close();
            }
            else {
              this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors l\'exécution de l\'opération');
            }
          },
          error: () => {
            this.toastService.error('Opération échouée', 'Problême de communication');
          }
        })
      }
      else {
        this.unAuthorizedModal()
      }
    }
    else {
      if (this.canUpdateOffer) {
        this.offreService.update(this.data.offer.id, this.formModal?.value as INewOfferBody).subscribe({
          next: res => {
            if (!res.hasError) {
              this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
              this.dialogRef.close();
            }
            else {
              this.toastService.error('Opération échouée', res.status && res.status.message ? res.status.message : 'Erreur lors l\'exécution de l\'opération');
            }
          },
          error: () => {
            this.toastService.error('Opération échouée', 'Problême de communication');
          }
        })
      }
      else {
        this.unAuthorizedModal()
      }
    }
  }
}
