import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusDetailsChartComponent } from './status-details-chart.component';

describe('StatusDetailsChartComponent', () => {
  let component: StatusDetailsChartComponent;
  let fixture: ComponentFixture<StatusDetailsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StatusDetailsChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StatusDetailsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
