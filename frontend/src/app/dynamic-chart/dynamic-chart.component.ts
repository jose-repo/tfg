import {Component, ViewChild} from '@angular/core';
import {Chart, ChartConfiguration, ChartData, ChartEvent, ChartType} from 'chart.js';
import {BaseChartDirective} from 'ng2-charts';
import {StatisticService} from "../services/statistic.service";
import {ActivatedRoute} from "@angular/router";
import {Statistic} from "../model/Entities";

@Component({
  selector: 'app-dynamic-chart',
  templateUrl: './dynamic-chart.component.html',
  styleUrls: ['./dynamic-chart.component.css']
})
export class DynamicChartComponent {
  constructor(private service: StatisticService, private route: ActivatedRoute) {
  }

  public statisticData: Statistic[] = [];
  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

  getDepopulationRiskLow(): number[] {
    this.statisticData = this.route.snapshot.data['statisticResolver'];
    let dataArr: number[] = [];
    // @ts-ignore
    for (let item of this.statisticData) {
      // @ts-ignore
      item.federalStateDataList?.forEach(federalState =>
        // @ts-ignore
        federalState.Data.forEach(federalStateData => {
          if (federalState.federalStatesExtensionEnum == "ANDALUCIA"
            && federalStateData.riskLevel == 0) {
            dataArr.push(federalStateData.riskLevel + 0.2)
          }
        }));
    }
    return dataArr;
  }

  getDepopulationRiskHigh(): number[] {
    this.statisticData = this.route.snapshot.data['statisticResolver'];
    let dataArr: number[] = [];
    // @ts-ignore
    for (let item of this.statisticData) {
      // @ts-ignore
      item.federalStateDataList?.forEach(federalState =>
        // @ts-ignore
        federalState.Data.forEach(federalStateData => {
          if (federalState.federalStatesExtensionEnum == "ANDALUCIA"
            && federalStateData.riskLevel > 2) {
            dataArr.push(federalStateData.riskLevel)
          }
        }));
    }
    return dataArr;
  }

  getDepopulationRiskMiddle(): number[] {
    this.statisticData = this.route.snapshot.data['statisticResolver'];
    let dataArr: number[] = [];
    // @ts-ignore
    for (let item of this.statisticData) {
      // @ts-ignore
      item.federalStateDataList?.forEach(federalState =>
        // @ts-ignore
        federalState.Data.forEach(federalStateData => {
          if (federalState.federalStatesExtensionEnum == "ANDALUCIA"
            && federalStateData.riskLevel == 1) {
            dataArr.push(federalStateData.riskLevel)
          }
        }));
    }
    return dataArr;
  }

  getYears(): string[] {
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
        max: 4,
      },
    },
    plugins: {
      legend: {display: true},
    },
  };
  public barChartLabels: string[] = this.getYears();
  public barChartType: ChartType = 'bar';

  public barChartData: ChartData<'bar'> = {
    labels: this.barChartLabels,
    datasets: [
      {
        data: this.getDepopulationRiskLow(), label: 'Bajo', backgroundColor: [
          'rgb(60, 179, 113, 0.2)',
        ],
      },
      {
        data: this.getDepopulationRiskMiddle(), label: 'Medio', backgroundColor: [
          'rgb(255, 165, 0, 0.2)',
        ],
      },
      {
        data: this.getDepopulationRiskHigh(), label: 'Alto', backgroundColor: [
          'rgba(255, 99, 132, 0.2)',],
      },
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

