import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAllocationComponent } from './admin-allocation.component';

describe('AdminAllocationComponent', () => {
  let component: AdminAllocationComponent;
  let fixture: ComponentFixture<AdminAllocationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAllocationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAllocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
