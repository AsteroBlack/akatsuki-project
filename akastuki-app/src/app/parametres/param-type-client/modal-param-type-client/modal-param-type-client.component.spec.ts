import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamTypeClientComponent } from './modal-param-type-client.component';

describe('ModalParamTypeClientComponent', () => {
  let component: ModalParamTypeClientComponent;
  let fixture: ComponentFixture<ModalParamTypeClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamTypeClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamTypeClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});