import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAccountingComponent } from './detail-accounting.component';

describe('DetailAccountingComponent', () => {
  let component: DetailAccountingComponent;
  let fixture: ComponentFixture<DetailAccountingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailAccountingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAccountingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
