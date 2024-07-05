import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionSystemeComponent } from './gestion-systeme.component';

describe('GestionSystemeComponent', () => {
  let component: GestionSystemeComponent;
  let fixture: ComponentFixture<GestionSystemeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionSystemeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionSystemeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
