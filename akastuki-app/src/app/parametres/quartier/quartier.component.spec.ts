import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuartierComponent } from './quartier.component';

describe('QuartierComponent', () => {
  let component: QuartierComponent;
  let fixture: ComponentFixture<QuartierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuartierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuartierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
