import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvisioningStatusChartComponent } from './provisioning-status-chart.component';

describe('ProvisioningChartComponent', () => {
  let component: ProvisioningStatusChartComponent;
  let fixture: ComponentFixture<ProvisioningStatusChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProvisioningStatusChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvisioningStatusChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
