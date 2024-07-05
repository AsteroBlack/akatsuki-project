import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalIpAdresseLibComponent } from './modal-ip-adresse-lib.component';

describe('ModalIpAdresseLibComponent', () => {
  let component: ModalIpAdresseLibComponent;
  let fixture: ComponentFixture<ModalIpAdresseLibComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalIpAdresseLibComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalIpAdresseLibComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
