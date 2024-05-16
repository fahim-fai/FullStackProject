import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RedeemHistoryComponent } from './redeem-history.component';

describe('RedeemHistoryComponent', () => {
  let component: RedeemHistoryComponent;
  let fixture: ComponentFixture<RedeemHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RedeemHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RedeemHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
