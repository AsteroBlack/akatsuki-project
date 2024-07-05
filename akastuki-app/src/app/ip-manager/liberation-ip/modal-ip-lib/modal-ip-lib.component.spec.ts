import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalIpLibComponent } from './modal-ip-lib.component';

describe('ModalIpLibComponent', () => {
  let component: ModalIpLibComponent;
  let fixture: ComponentFixture<ModalIpLibComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalIpLibComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalIpLibComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
