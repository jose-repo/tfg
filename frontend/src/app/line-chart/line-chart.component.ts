import {Component, OnInit, ViewChild} from '@angular/core';
import {Chart, ChartConfiguration, ChartEvent, ChartType} from 'chart.js';
import {BaseChartDirective} from 'ng2-charts';

import Annotation from 'chartjs-plugin-annotation';
import {StatisticService} from "../services/statistic.service";
import {Statistic} from "../model/Entities";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})
export class LineChartComponent {
  constructor(private service: StatisticService, private route: ActivatedRoute) {
    Chart.register(Annotation);
  }
  public statisticData: Statistic[] =[] ;
  private newLabel? = 'Despoblación';

  getLineChartData(): number[] {
    this.statisticData = this.route.snapshot.data['statisticResolver'];
    let dataArr: number[] = [];
    // @ts-ignore
      for (let item of this.statisticData) {
        //console.log(item);
        // @ts-ignore
        item.federalStateDataList?.forEach(federalState =>
          // @ts-ignore
          federalState.Data.forEach(federalStateData => {
            if (federalState.federalStatesExtensionEnum == "ANDALUCIA") {
              dataArr.push(federalStateData.Valor)
            }
          }));
      }
    return dataArr;

  }

  getLineChartYears(): string[] {
    this.statisticData = this.route.snapshot.data['statisticResolver'];
    let dataArr: string[] = [];
    // @ts-ignore
      for (let item of this.statisticData) {
        //console.log(item);
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
        //data: [12222, 12222, 1222212222, 122221222212222],
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
    //labels: ['2010','2020', '2030', '2040','2010','2020', '2030', '2040', '2010','2020', '2030', '2040', '2010','2020', '2030', '2040','2010','2020', '2030', '2040','2010','2020', '2030', '2040', '2010','2020', '2030', '2040', '2010','2020', '2030', '2040']
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
