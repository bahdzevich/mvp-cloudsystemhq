import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainPanelInfoComponent } from './main-panel-info.component';

describe('MainPanelInfoComponent', () => {
  let component: MainPanelInfoComponent;
  let fixture: ComponentFixture<MainPanelInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainPanelInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainPanelInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
