import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRedeemComponent } from './admin-redeem.component';

describe('AdminRedeemComponent', () => {
  let component: AdminRedeemComponent;
  let fixture: ComponentFixture<AdminRedeemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRedeemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminRedeemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
