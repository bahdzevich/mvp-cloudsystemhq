import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartnersPanelComponent } from './partners-panel.component';

describe('PartnersPanelComponent', () => {
  let component: PartnersPanelComponent;
  let fixture: ComponentFixture<PartnersPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartnersPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartnersPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
