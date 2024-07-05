import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamCassetteComponent } from './modal-param-cassette.component';

describe('ModalParamCassetteComponent', () => {
  let component: ModalParamCassetteComponent;
  let fixture: ComponentFixture<ModalParamCassetteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamCassetteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamCassetteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});