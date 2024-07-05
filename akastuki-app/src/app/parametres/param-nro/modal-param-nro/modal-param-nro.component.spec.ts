import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamNroComponent } from './modal-param-nro.component';

describe('ModalParamNroComponent', () => {
  let component: ModalParamNroComponent;
  let fixture: ComponentFixture<ModalParamNroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamNroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamNroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});