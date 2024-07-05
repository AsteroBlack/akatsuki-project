import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalOltComponent } from './modal-olt.component';

describe('ModalOltComponent', () => {
  let component: ModalOltComponent;
  let fixture: ComponentFixture<ModalOltComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalOltComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalOltComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});