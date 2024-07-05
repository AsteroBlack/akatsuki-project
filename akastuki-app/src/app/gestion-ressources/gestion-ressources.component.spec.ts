import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionRessourcesComponent } from './gestion-ressources.component';

describe('GestionRessourcesComponent', () => {
  let component: GestionRessourcesComponent;
  let fixture: ComponentFixture<GestionRessourcesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionRessourcesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionRessourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
