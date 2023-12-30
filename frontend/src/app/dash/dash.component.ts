import {Component, inject} from '@angular/core';
import {Breakpoints, BreakpointObserver} from '@angular/cdk/layout';
import {map} from 'rxjs/operators';
import {StatisticService} from "../services/statistic.service";
import {Statistic} from "../model/entities";
import {ActivatedRoute} from "@angular/router";
import {MatDatepicker} from "@angular/material/datepicker";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-dash',
  templateUrl: './dash.component.html',
  styleUrls: ['./dash.component.css']
})
export class DashComponent {
  constructor(private route: ActivatedRoute) {
    this.route.params.subscribe(routeParams => {
      this.currentAreaID = this.route.snapshot.params['id'];
      this.currentAreaStr = this.route.snapshot.params['name'];
      this.statisticData = this.route.snapshot.data['statisticResolver'];
      this.getRisk();
    });
  }

  public statisticData: Statistic[] = [];
  public currentAreaID: string = this.route.snapshot.params['id'];
  public currentAreaStr: string = this.route.snapshot.params['name'];
  public risk: number = 1;

  dateFrom = new FormControl(new Date());
  serializedDateFrom = new FormControl((new Date()).toISOString());
  dateTo = new FormControl(new Date());
  serializedDateTo = new FormControl((new Date()).toISOString());

  chosenYearHandlerFrom(normalizedYear: Date) {
    const ctrlValue = this.dateFrom.value;
    // @ts-ignore
    ctrlValue.setFullYear(normalizedYear.getFullYear());
    this.dateFrom.setValue(ctrlValue);
  }

  chosenMonthHandlerFrom(normalizedMonth: Date, datepicker: MatDatepicker<Date>) {
    const ctrlValue = this.dateFrom.value;
    // @ts-ignore
    ctrlValue.setMonth(normalizedMonth.getMonth());
    this.dateFrom.setValue(ctrlValue);
    datepicker.close();
  }

  chosenYearHandlerTo(normalizedYear: Date) {
    const ctrlValue = this.dateTo.value;
    // @ts-ignore
    ctrlValue.setFullYear(normalizedYear.getFullYear());
    this.dateTo.setValue(ctrlValue);
  }

  chosenMonthHandlerTo(normalizedMonth: Date, datepicker: MatDatepicker<Date>) {
    const ctrlValue = this.dateTo.value;
    // @ts-ignore
    ctrlValue.setMonth(normalizedMonth.getMonth());
    this.dateTo.setValue(ctrlValue);
    datepicker.close();
  }

  getRisk() {
    this.statisticData = this.route.snapshot.data['statisticResolver'];
    // @ts-ignore
    for (let item of this.statisticData) {
      // @ts-ignore
      item.federalStateDataList?.forEach(federalState => {
        // @ts-ignore
        federalState.Data.forEach(federalStateData => {
          if (federalState.federalStatesExtensionEnum == this.currentAreaID) {
            // @ts-ignore
            this.risk = federalStateData.depopulationRiskLevel * 32;
          }
        });
        federalState.regionDataList?.forEach(region => {
          // @ts-ignore
          if (region?.regionExtensionEnum == this.currentAreaID) {
            this.risk = region.depopulationRiskLevel * 33.3;
          }
        });
      });
    }
  }

  private breakpointObserver = inject(BreakpointObserver);
  /** Based on the screen size, switch from standard to one column per row */
  cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({matches}) => {
      if (matches) {
        return [
          {title: 'Card 1', cols: 1, rows: 1},
          {title: 'Card 2', cols: 1, rows: 1},
          {title: 'Card 3', cols: 1, rows: 1},
          {title: 'Card 4', cols: 1, rows: 1}
        ];
      }

      return [
        {title: 'Card 1', cols: 2, rows: 1},
        {title: 'Card 2', cols: 1, rows: 1},
        {title: 'Card 3', cols: 1, rows: 2},
        {title: 'Card 4', cols: 1, rows: 1}
      ];
    })
  );
}
