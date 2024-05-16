import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrsAdminComponent } from './errs-admin.component';

describe('ErrsAdminComponent', () => {
  let component: ErrsAdminComponent;
  let fixture: ComponentFixture<ErrsAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErrsAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ErrsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
