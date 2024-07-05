import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamColorComponent } from './modal-param-color.component';

describe('ModalParamColorComponent', () => {
  let component: ModalParamColorComponent;
  let fixture: ComponentFixture<ModalParamColorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamColorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamColorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});