import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributsComponent } from './attributs.component';

describe('AttributsComponent', () => {
  let component: AttributsComponent;
  let fixture: ComponentFixture<AttributsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AttributsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
