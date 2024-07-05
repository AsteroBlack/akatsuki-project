import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalGroupeRadiusComponent } from './modal-groupe-radius.component';

describe('ModalGroupeRadiusComponent', () => {
  let component: ModalGroupeRadiusComponent;
  let fixture: ComponentFixture<ModalGroupeRadiusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalGroupeRadiusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalGroupeRadiusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
