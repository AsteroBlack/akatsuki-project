import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModeleCarteComponent } from './modele-carte.component';

describe('ModeleCarteComponent', () => {
  let component: ModeleCarteComponent;
  let fixture: ComponentFixture<ModeleCarteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModeleCarteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModeleCarteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
