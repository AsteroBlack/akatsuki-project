import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamArchitectureComponent } from './modal-param-architecture.component';

describe('ModalParamArchitectureComponent', () => {
  let component: ModalParamArchitectureComponent;
  let fixture: ComponentFixture<ModalParamArchitectureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamArchitectureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamArchitectureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});