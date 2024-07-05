import { AfterContentInit, Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { CompteService } from '../../../core/services/compte.service';

@Component({
  selector: 'app-modal-comptes',
  templateUrl: './modal-comptes.component.html',
  styleUrls: ['./modal-comptes.component.scss']
})
export class ModalComptesComponent implements OnInit, AfterContentInit {
  formModal: FormGroup;
  jsTreeElt: any;
  constructor(
    public dialogRef: MatDialogRef<ModalComptesComponent>,
    private compteService: CompteService,
    private toastService: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.formModal = new FormGroup({
      compte: new FormControl(null, [Validators.required]),
      login: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required])
    });
  }

  ngOnInit(): void {
  }
  ngAfterContentInit() {
    this.jsTreeElt = $('#actionTree');

    if (this.data) {
      console.log('data jjjj', this.data)
      this.formModal.controls.compte.setValue(this.data.userCompte)
      // this.title = "Modifi&&&&&&&&&&&&er un rôle";
    }

  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  onSubmit(param: any) {
    console.log('submit action', param);
    if (this.formModal.value.id) {
      this.update()
    } else {
      this.create()
    }
  }

  create() {
    const createCompteRequest = this.compteService.create(
      {
        userCompte: this.formModal.value.compte,
        login: this.formModal.value.login,
        password: this.formModal.value.password
      }
    ).subscribe({
      next: (response) => {
        if (!response.hasError) {
          this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
          this.dialogRef.close();
        } else {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de recupérer les comptes');
        }
      },
      error: (error) => {
        console.error("Erreur de création de compte", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
        throw new Error(`Erreur sur la création de compte ${JSON.stringify(error)}`)
      }
    })
  }
  update() {
    const createCompteRequest = this.compteService.update(
      {
        userCompte: this.formModal.value.compte,
        login: this.formModal.value.login,
        password: this.formModal.value.password
      }
    ).subscribe({
      next: (response) => {
        if (!response.hasError) {
          this.toastService.success('Opération réussie', 'Opération effectuée avec succès');
          this.dialogRef.close();
        } else {
          this.toastService.error('Oups !', response && response.status && response.status.message ? response.status.message : 'Impossible de recupérer les comptes');
        }
      },
      error: (error) => {
        console.error("Erreur de création de compte", JSON.stringify(error))
        this.toastService.error('Erreur', 'Un problème est survenu');
        throw new Error(`Erreur sur la création de compte ${JSON.stringify(error)}`)
      }
    })
  }

}
