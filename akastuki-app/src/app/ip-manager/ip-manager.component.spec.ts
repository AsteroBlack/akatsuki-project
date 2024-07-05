import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IpManagerComponent } from './ip-manager.component';

describe('IpManagerComponent', () => {
  let component: IpManagerComponent;
  let fixture: ComponentFixture<IpManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IpManagerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IpManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
