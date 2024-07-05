import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalOffreOltComponent } from './modal-offre-olt.component';

describe('ModalOffreOltComponent', () => {
  let component: ModalOffreOltComponent;
  let fixture: ComponentFixture<ModalOffreOltComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalOffreOltComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalOffreOltComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
