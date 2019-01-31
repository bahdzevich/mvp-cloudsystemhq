import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ResponseTextComponent} from './response-text.component';

describe('ResponseTextComponent', () => {
  let component: ResponseTextComponent;
  let fixture: ComponentFixture<ResponseTextComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ResponseTextComponent]
    });
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResponseTextComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
