import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalIPAddressComponent } from './modal-ip-address.component';

describe('ModalRessourcesComponent', () => {
  let component: ModalIPAddressComponent;
  let fixture: ComponentFixture<ModalIPAddressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalIPAddressComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalIPAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
