import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PastEmployeesComponent } from './past-employees.component';

describe('PastEmployeesComponent', () => {
  let component: PastEmployeesComponent;
  let fixture: ComponentFixture<PastEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PastEmployeesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PastEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
