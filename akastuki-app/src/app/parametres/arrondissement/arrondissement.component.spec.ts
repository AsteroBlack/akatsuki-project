import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArrodissementComponent } from './arrodissement.component';

describe('ArrodissementComponent', () => {
  let component: ArrodissementComponent;
  let fixture: ComponentFixture<ArrodissementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArrodissementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArrodissementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
