import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParametresGenerauxComponent } from './parametres-generaux.component';

describe('ParametresGenerauxComponent', () => {
  let component: ParametresGenerauxComponent;
  let fixture: ComponentFixture<ParametresGenerauxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParametresGenerauxComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParametresGenerauxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
