import {Component, Input, ViewChild} from '@angular/core';
import {Chart, ChartConfiguration, ChartData, ChartEvent, ChartType} from 'chart.js';
import {BaseChartDirective} from 'ng2-charts';
import {ActivatedRoute} from "@angular/router";
import {Statistic} from "../model/entities";

@Component({
  selector: 'app-dynamic-chart',
  templateUrl: './dynamic-chart.component.html',
  styleUrls: ['./dynamic-chart.component.css']
})
export class DynamicChartComponent {
  constructor(private route: ActivatedRoute) {
    this.route.params.subscribe(routeParams => {
      this.name = this.route.snapshot.params['id'];
      this.barChartLabels = this.getYears();
      this.barChartData = {
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
      ]}
    });
  }

  public name: string | undefined;
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
          if (federalState.federalStatesExtensionEnum == this.name
            && federalStateData.riskLevel == 0) {
            dataArr.push(federalStateData.riskLevel + 0.1)
          } else if (federalState.federalStatesExtensionEnum == this.name
            && federalStateData.riskLevel == 1) {
            dataArr.push(federalStateData.riskLevel)
          } else if (federalState.federalStatesExtensionEnum == this.name) {
            dataArr.push(0)
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
            if (region?.regionExtensionEnum == this.name) {
              region?.Data?.forEach(data => {
                if (data.riskLevel == 0) {
                  dataArr.push(data.riskLevel + 0.1)
                } else if (data.riskLevel == 1) {
                  dataArr.push(data.riskLevel)
                } else {
                  dataArr.push(0)
                }
              })
            }
          }));
      }
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
          if (federalState.federalStatesExtensionEnum == this.name
            && federalStateData.riskLevel > 2) {
            dataArr.push(federalStateData.riskLevel)
          } else if (federalState.federalStatesExtensionEnum == this.name) {
            dataArr.push(0)
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
            if (region?.regionExtensionEnum == this.name) {
              region?.Data?.forEach(data => {
                if (data.riskLevel > 2) {
                  dataArr.push(data.riskLevel)
                } else {
                  dataArr.push(0)
                }
              })
            }
          }));
      }
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
          if (federalState.federalStatesExtensionEnum == this.name
            && (federalStateData.riskLevel == 2)) {
            dataArr.push(federalStateData.riskLevel)
          } else if (federalState.federalStatesExtensionEnum == this.name) {
            dataArr.push(0)
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
            if (region?.regionExtensionEnum == this.name) {
              region?.Data?.forEach(data => {
                if (data.riskLevel == 2) {
                  dataArr.push(data.riskLevel)
                } else {
                  dataArr.push(0);
                }
              })
            }
          }));
      }
    }
    return dataArr;
  }

  getYears(): string[] {
    this.statisticData = this.route.snapshot.data['statisticResolver'];
    let dataArr: string[] = [];
    // @ts-ignore
    for (let item of this.statisticData) {
      // @ts-ignore
      item.federalStateDataList.forEach(federalState =>
        // @ts-ignore
        federalState.Data.forEach(federalStateData => {
          if (federalState.federalStatesExtensionEnum == this.name) {
            dataArr.push("" + federalStateData.Anyo + "");
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
            if (region?.regionExtensionEnum == this.name) {
              region?.Data?.forEach(data => {
                dataArr.push("" + data.Anyo + "");
              })
            }
          }));
      }
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
        max: 3,
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
  @Input() dateTo!: Date | null;
  @Input() dateFrom!: Date | null;
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

