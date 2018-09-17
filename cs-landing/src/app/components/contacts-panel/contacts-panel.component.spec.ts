import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactsPanelComponent } from './contacts-panel.component';

describe('ContactsPanelComponent', () => {
  let component: ContactsPanelComponent;
  let fixture: ComponentFixture<ContactsPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContactsPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactsPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
