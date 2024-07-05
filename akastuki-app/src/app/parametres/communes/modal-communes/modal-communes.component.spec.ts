import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalCommuneComponent } from './modal-communes.component';

describe('ModalCommuneComponent', () => {
  let component: ModalCommuneComponent;
  let fixture: ComponentFixture<ModalCommuneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalCommuneComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalCommuneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});