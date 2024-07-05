import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalSetupIpLibComponent } from './modal-setup-ip-lib.component';

describe('ModalSetupIpLibComponent', () => {
  let component: ModalSetupIpLibComponent;
  let fixture: ComponentFixture<ModalSetupIpLibComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalSetupIpLibComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalSetupIpLibComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
