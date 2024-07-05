import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAttributionComponent } from './modal-attribution.component';

describe('ModalAttributionComponent', () => {
  let component: ModalAttributionComponent;
  let fixture: ComponentFixture<ModalAttributionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalAttributionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalAttributionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
