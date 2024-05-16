import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRewardComponent } from './admin-reward.component';

describe('AdminRewardComponent', () => {
  let component: AdminRewardComponent;
  let fixture: ComponentFixture<AdminRewardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRewardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminRewardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
