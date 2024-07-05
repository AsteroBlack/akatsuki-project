import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OffreOltComponent } from './offre-olt.component';

describe('OffreOltComponent', () => {
  let component: OffreOltComponent;
  let fixture: ComponentFixture<OffreOltComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OffreOltComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OffreOltComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
