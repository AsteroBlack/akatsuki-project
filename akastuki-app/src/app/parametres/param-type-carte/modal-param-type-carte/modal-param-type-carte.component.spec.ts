import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamTypeCarteComponent } from './modal-param-type-carte.component';

describe('ModalParamTypeCarteComponent', () => {
  let component: ModalParamTypeCarteComponent;
  let fixture: ComponentFixture<ModalParamTypeCarteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamTypeCarteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamTypeCarteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});