import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeuilAlerteComponent } from './seuil-alerte.component';

describe('SeuilAlerteComponent', () => {
  let component: SeuilAlerteComponent;
  let fixture: ComponentFixture<SeuilAlerteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeuilAlerteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeuilAlerteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
