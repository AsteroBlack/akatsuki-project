import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamPbComponent } from './modal-param-pb.component';

describe('ModalParamPbComponent', () => {
  let component: ModalParamPbComponent;
  let fixture: ComponentFixture<ModalParamPbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamPbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamPbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
