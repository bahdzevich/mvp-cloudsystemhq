import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ResponseCheckboxComponent} from './response-checkbox.component';

describe('ResponseCheckboxComponent', () => {
  let component: ResponseCheckboxComponent;
  let fixture: ComponentFixture<ResponseCheckboxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ResponseCheckboxComponent]
    });
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResponseCheckboxComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
