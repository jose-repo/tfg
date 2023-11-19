import { Component } from '@angular/core';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';

@Component({
  selector: 'app-bubble-chart-c',
  templateUrl: './bubble-chart-c.component.html',
  styleUrls: ['./bubble-chart-c.component.css']
})
export class BubbleChartCComponent {
public bubbleChartOptions: ChartConfiguration['options'] = {
    scales: {
      x: {
        min: 1990,
        max: 2020,
        ticks: {},
      },
      y: {
        min: 4000,
        max: 0,
        ticks: {},
      },
    },
  };
  public bubbleChartType: ChartType = 'bubble';
  public bubbleChartLegend = true;

  public bubbleChartData: ChartData<'bubble'> = {
    labels: [],
    datasets: [
      {
        data: [
          { x: 1990, y:18 , r: 6},
          { x: 1995, y:20 , r: 8},
          { x: 2000, y:22 , r: 10},
          { x: 2005, y:24 , r: 11},
          { x: 2010, y:18 , r: 12},
          { x: 2015, y:18 , r: 13},
          { x: 2020, y:18 , r: 13},
        ],
        label: 'Riesgo Medio',
        backgroundColor: [
                    'red',
                    'green',
                    'orange',
        ],
        borderColor: 'blue',
        hoverBackgroundColor: 'purple',
        hoverBorderColor: 'red',
      },
    ],
  };

  // events
  public chartClicked({
    event,
    active,
  }: {
    event: ChartEvent;
    active: object[];
  }): void {
    console.log(event, active);
  }

  public chartHovered({
    event,
    active,
  }: {
    event: ChartEvent;
    active: object[];
  }): void {
    console.log(event, active);
  }

  private rand(max: number): number {
    return Math.trunc(Math.random() * max);
  }

  private randomPoint(maxCoordinate: number): {
    r: number;
    x: number;
    y: number;
  } {
    const x = this.rand(maxCoordinate);
    const y = this.rand(maxCoordinate);
    const r = this.rand(30) + 5;
    return { x, y, r };
  }

  public randomize(): void {
    const numberOfPoints = this.rand(5) + 5;
    this.bubbleChartData.datasets[0].data = new Array(numberOfPoints).map((r) =>
      this.randomPoint(30)
    );
  }
}
