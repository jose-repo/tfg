import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BubbleChartCComponent } from './bubble-chart-c.component';

describe('BubbleChartCComponent', () => {
  let component: BubbleChartCComponent;
  let fixture: ComponentFixture<BubbleChartCComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BubbleChartCComponent]
    });
    fixture = TestBed.createComponent(BubbleChartCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
