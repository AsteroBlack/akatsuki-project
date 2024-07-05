import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalParamPbSiteComponent } from './modal-param-pb-site.component';

describe('ModalRessourcesComponent', () => {
  let component: ModalParamPbSiteComponent;
  let fixture: ComponentFixture<ModalParamPbSiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalParamPbSiteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalParamPbSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});