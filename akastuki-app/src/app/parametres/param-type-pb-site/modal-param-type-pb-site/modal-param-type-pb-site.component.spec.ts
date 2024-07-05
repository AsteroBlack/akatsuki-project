import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamTypePbSiteComponent } from './modal-param-type-pb-site.component';

describe('ModalParamTypePbSiteComponent', () => {
  let component: ModalParamTypePbSiteComponent;
  let fixture: ComponentFixture<ModalParamTypePbSiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamTypePbSiteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamTypePbSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});