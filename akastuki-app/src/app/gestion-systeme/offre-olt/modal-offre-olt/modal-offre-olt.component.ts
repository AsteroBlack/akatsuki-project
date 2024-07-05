import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { OfferOltService } from 'src/app/core/services/offer-olt.service';
import { INewOfferOLTBody, IOffreOLT } from 'src/app/shared/interfaces/offreOLT.interface';
import { UserService } from 'src/app/shared/services/user.service';

export enum ActionTypes {
  CREATE,
  UPDATE
}

@Component({
  selector: 'app-modal-offre-olt',
  templateUrl: './modal-offre-olt.component.html',
  styleUrls: ['./modal-offre-olt.component.scss']
})
export class ModalOffreOltComponent implements OnInit {
  formModal: FormGroup | null = null;
  actionTypes = ActionTypes

  //permissions properties
  canCreateOfferOLT = false;
  canUpdateOfferOLT = false;
  unAuthorizedMessage = "Vous n'êtes pas autorisé à réaliser cette action";

  constructor(
    public dialogRef: MatDialogRef<ModalOffreOltComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { offer: IOffreOLT, actionType: ActionTypes },
    private offerOltService: OfferOltService,
    private toastService: ToastrService,
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.definePermissions()
    this.initForm()
  }

  definePermissions() {
    this.canCreateOfferOLT = this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'PARAMETRES_GENERAUX', 'CREER_OLT')
    this.canUpdateOfferOLT = this.userService.checkMenuExiste('INFRASTRUCTURE_PHYSIQUE', 'PARAMETRES_GENERAUX', 'MODIFIER_OLT')
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

  initForm() {
    if (this.data.actionType === ActionTypes.UPDATE) {
      if(this.canUpdateOfferOLT){
        const offer = this.data.offer
        this.formModal = new FormGroup({
          template: new FormControl(offer.template, Validators.required),
          rx: new FormControl(offer.rx, Validators.required),
          tx: new FormControl(offer.tx, Validators.required),
        })
      }
      else{
        this.unAuthorizedModal()
      }
    }
    else {
      if(this.canCreateOfferOLT){
        this.formModal = new FormGroup({
          template: new FormControl('', Validators.required),
          rx: new FormControl('', Validators.required),
          tx: new FormControl('', Validators.required),
        })
      }
      else{
        this.unAuthorizedModal()
      }
    }
  }

  onSubmit() {
    if (this.data.actionType === ActionTypes.CREATE) {
      if(this.canCreateOfferOLT){
        this.offerOltService.create(this.formModal?.value as INewOfferOLTBody).subscribe({
          next: res=>{
            if(!res.hasError){
              this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
              this.dialogRef.close();
            }
            else{
              this.toastService.error('Oups !', res && res.status && res.status.message ? res.status.message : 'Impossible de realiser cette action');
            }
          },
          error: ()=>{
            this.toastService.error('Erreur', 'Un problème est survenu');
          }
        })
      }
      else{
        this.unAuthorizedModal()
      }
    }
    else {
      if(this.canUpdateOfferOLT){
        this.offerOltService.update(this.data.offer.id, this.formModal?.value as INewOfferOLTBody).subscribe({
        next: res=>{
          if(!res.hasError){
            this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
            this.dialogRef.close();
          }
          else{
            this.toastService.error('Oups !', res && res.status && res.status.message ? res.status.message : 'Impossible de realiser cette action');
          }
        },
        error: ()=>{
          this.toastService.error('Erreur', 'Un problème est survenu');
        }
      })
      }
      else{
        this.unAuthorizedModal()
      }
    }
  }
}
