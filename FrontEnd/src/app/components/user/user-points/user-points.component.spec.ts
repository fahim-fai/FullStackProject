import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPointsComponent } from './user-points.component';

describe('UserPointsComponent', () => {
  let component: UserPointsComponent;
  let fixture: ComponentFixture<UserPointsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserPointsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserPointsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
