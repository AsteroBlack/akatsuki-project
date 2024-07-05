import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupeRadiusComponent } from './groupe-radius.component';

describe('GroupeRadiusComponent', () => {
  let component: GroupeRadiusComponent;
  let fixture: ComponentFixture<GroupeRadiusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupeRadiusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupeRadiusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
