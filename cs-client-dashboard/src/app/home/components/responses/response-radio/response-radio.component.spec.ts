import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ResponseRadioComponent} from './response-radio.component';

describe('ResponseRadioComponent', () => {
  let component: ResponseRadioComponent;
  let fixture: ComponentFixture<ResponseRadioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ResponseRadioComponent]
    });
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResponseRadioComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
