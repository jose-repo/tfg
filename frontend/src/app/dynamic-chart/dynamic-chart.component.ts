import { Component, ViewChild } from '@angular/core';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-dynamic-chart',
  templateUrl: './dynamic-chart.component.html',
  styleUrls: ['./dynamic-chart.component.css']
})
export class DynamicChartComponent {
 @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

  public barChartOptions: ChartConfiguration['options'] = {
    elements: {
      line: {
        tension: 0.4,
      },
    },
    // We use these empty structures as placeholders for dynamic theming.
    scales: {
      x: {},
      y: {
      ticks: {stepSize: 1},
        min: 0,
        max: 5,
      },
    },
    plugins: {
      legend: { display: true },
    },
  };
  public barChartLabels: string[] = [
    '2006',
    '2007',
    '2008',
    '2009',
    '2010',
    '2011',
    '2012',
    '2013',
  ];
  public barChartType: ChartType = 'bar';

  public barChartData: ChartData<'bar'> = {
    labels: this.barChartLabels,
    datasets: [
      { data: [1, 1, 1, 0, 0, 0, 0, 0], label: 'Bajo', backgroundColor: [
                                                                      'rgb(60, 179, 113, 0.2)',
                                                                    ],},
      { data: [0, 0, 0, 2, 2, 3, 0, 0], label: 'Medio', backgroundColor: [
                                                                      'rgb(255, 165, 0, 0.2)',
                                                                    ],},
      { data: [0, 0, 0, 0, 0, 0, 4, 5], label: 'Alto', backgroundColor: [
                                                                      'rgba(255, 99, 132, 0.2)',                                                          ],},
    ],
  };

  // events
  public chartClicked({
    event,
    active,
  }: {
    event?: ChartEvent;
    active?: object[];
  }): void {
    console.log(event, active);
  }

  public chartHovered({
    event,
    active,
  }: {
    event?: ChartEvent;
    active?: object[];
  }): void {
    console.log(event, active);
  }

  public randomize(): void {
    this.barChartType = this.barChartType === 'bar' ? 'line' : 'bar';
  }
}

