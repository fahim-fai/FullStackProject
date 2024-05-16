import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrsEmployeeComponent } from './errs-employee.component';

describe('ErrsEmployeeComponent', () => {
  let component: ErrsEmployeeComponent;
  let fixture: ComponentFixture<ErrsEmployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErrsEmployeeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ErrsEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
