import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvisionningComponent } from './provisionning.component';

describe('ProvisionningComponent', () => {
  let component: ProvisionningComponent;
  let fixture: ComponentFixture<ProvisionningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProvisionningComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvisionningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
