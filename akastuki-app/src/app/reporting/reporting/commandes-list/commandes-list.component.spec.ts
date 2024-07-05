import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommandesListComponent } from './commandes-list.component';

describe('CommandesListComponent', () => {
  let component: CommandesListComponent;
  let fixture: ComponentFixture<CommandesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommandesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommandesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
