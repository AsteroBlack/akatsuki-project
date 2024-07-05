import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeOffreComponent } from './type-offre.component';

describe('TypeOffreComponent', () => {
  let component: TypeOffreComponent;
  let fixture: ComponentFixture<TypeOffreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TypeOffreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeOffreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
