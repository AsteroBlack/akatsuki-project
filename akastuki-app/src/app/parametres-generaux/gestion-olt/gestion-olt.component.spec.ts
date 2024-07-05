import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionOltComponent } from './gestion-olt.component';

describe('GestionOltComponent', () => {
  let component: GestionOltComponent;
  let fixture: ComponentFixture<GestionOltComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionOltComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionOltComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
