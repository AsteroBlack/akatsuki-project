import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamTypeTiroirComponent } from './modal-param-type-tiroir.component';

describe('ModalParamTypeTiroirComponent', () => {
  let component: ModalParamTypeTiroirComponent;
  let fixture: ComponentFixture<ModalParamTypeTiroirComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamTypeTiroirComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamTypeTiroirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});