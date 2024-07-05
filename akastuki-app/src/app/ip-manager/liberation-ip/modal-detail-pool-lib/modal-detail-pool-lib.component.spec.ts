import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalDetailPoolLibComponent } from './modal-detail-pool-lib.component';

describe('ModalDetailPoolLibComponent', () => {
  let component: ModalDetailPoolLibComponent;
  let fixture: ComponentFixture<ModalDetailPoolLibComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalDetailPoolLibComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalDetailPoolLibComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
