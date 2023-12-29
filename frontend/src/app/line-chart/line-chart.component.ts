import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {Chart, ChartConfiguration, ChartEvent, ChartType} from 'chart.js';
import {BaseChartDirective} from 'ng2-charts';

import Annotation from 'chartjs-plugin-annotation';
import {Statistic} from "../model/Entities";
import {ActivatedRoute} from "@angular/router";
import _default from "chart.js/dist/core/core.interaction";
import dataset = _default.modes.dataset;

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})
export class LineChartComponent {
  constructor(private route: ActivatedRoute) {
    Chart.register(Annotation);
    this.route.params.subscribe(routeParams => {
      this.name = this.route.snapshot.params['id'];
      this.chart?.update();
      this.lineChartData = {
        datasets: [
          {
            data: this.getLineChartData(),
            label: 'Población',
            backgroundColor: 'rgba(19,34,54,0.6)',
            borderColor: 'rgba(148,159,177,1)',
            pointBackgroundColor: 'rgba(148,159,177,1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(148,159,177,0.8)',
            fill: 'origin',
          }
        ],
        labels: this.getLineChartYears()
      }
    });
  }
  public statisticData: Statistic[] =[] ;
  private newLabel? = 'Despoblación';
  public name: string | undefined;

  getLineChartData(): number[] {
    this.statisticData = this.route.snapshot.data['statisticResolver'];
    let dataArr: number[] = [];
    // @ts-ignore
      for (let item of this.statisticData) {
        // @ts-ignore
        item.federalStateDataList?.forEach(federalState =>
          // @ts-ignore
          federalState.Data.forEach(federalStateData => {
            if (federalState.federalStatesExtensionEnum == this.name) {
              dataArr.push(federalStateData.Valor)
            }
          }));
      }
    if (dataArr.length == 0) {
      for (let item of this.statisticData) {
        // @ts-ignore
        item.federalStateDataList?.forEach(federalState =>
          // @ts-ignore
            federalState.regionDataList?.forEach(region =>
            {
              // @ts-ignore
              if (region.MetaData[0].Nombre == this.name) {
                region?.Data?.forEach(data => {
                  dataArr.push(data.Valor)
                })
              }
          }));
      }
    }
    return dataArr;

  }

  getLineChartYears(): string[] {
    this.statisticData = this.route.snapshot.data['statisticResolver'];
    let dataArr: string[] = [];
    // @ts-ignore
      for (let item of this.statisticData) {
        // @ts-ignore
        item.federalStateDataList.forEach(federalState =>
          // @ts-ignore
          federalState.Data.forEach(federalStateData => {
            if (federalState.federalStatesExtensionEnum == "ANDALUCIA") {
              dataArr.push("" + federalStateData.Anyo + "");
            }
          }));
      }
    return dataArr;
  }

  public lineChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: this.getLineChartData(),
        label: 'Población',
        backgroundColor: 'rgba(19,34,54,0.6)',
        borderColor: 'rgba(148,159,177,1)',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)',
        fill: 'origin',
      }
    ],
    labels: this.getLineChartYears(),
  };

  public lineChartOptions: ChartConfiguration['options'] = {
    elements: {
      line: {
        tension: 0.5,
      },
    },
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      y: {
        position: 'left',
      },
      y1: {
        position: 'right',
        grid: {
          color: 'rgba(255,0,0,0.3)',
        },
        ticks: {
          color: 'white',
        },
      },
    },

    plugins: {
      legend: {display: true},
      annotation: {
        annotations: [
          {
            type: 'line',
            scaleID: 'x',
            value: '',
            borderColor: 'orange',
            borderWidth: 2,
            label: {
              display: true,
              position: 'center',
              color: 'orange',
              content: '',
              font: {
                weight: 'bold',
              },
            },
          },
        ],
      },
    },
  };

  public lineChartType: ChartType = 'line';

  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;

  private static generateNumber(i: number): number {
    return Math.floor(Math.random() * (i < 2 ? 100 : 1000) + 2);
  }

  public randomize(): void {
    for (let i = 0; i < this.lineChartData.datasets.length; i++) {
      for (let j = 0; j < this.lineChartData.datasets[i].data.length; j++) {
        this.lineChartData.datasets[i].data[j] =
          LineChartComponent.generateNumber(i);
      }
    }
    this.chart?.update();
  }

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

  public hideOne(): void {
    const isHidden = this.chart?.isDatasetHidden(1);
    this.chart?.hideDataset(1, !isHidden);
  }

  public pushOne(): void {
    this.lineChartData.datasets.forEach((x, i) => {
      const num = LineChartComponent.generateNumber(i);
      x.data.push(num);
    });
    this.lineChartData?.labels?.push(
      `Label ${this.lineChartData.labels.length}`
    );

    this.chart?.update();
  }

  public changeColor(): void {
    this.lineChartData.datasets[2].borderColor = 'green';
    this.lineChartData.datasets[2].backgroundColor = `rgba(0, 255, 0, 0.3)`;

    this.chart?.update();
  }

  public changeLabel(): void {
    const tmp = this.newLabel;
    this.newLabel = this.lineChartData.datasets[2].label;
    this.lineChartData.datasets[2].label = tmp;

    this.chart?.update();
  }
}
