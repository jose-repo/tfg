import {Component, inject, ViewChild} from '@angular/core';
import {Breakpoints, BreakpointObserver} from '@angular/cdk/layout';
import {map} from 'rxjs/operators';
import {StatisticService} from "../services/statistic.service";
import {Statistic} from "../model/entities";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDatepicker} from "@angular/material/datepicker";
import {FormControl} from "@angular/forms";
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-dash',
  templateUrl: './dash.component.html',
  styleUrls: ['./dash.component.css']
})
export class DashComponent {
  // @ts-ignore
  @ViewChild('dp') datepicker: MatDatepicker<Date>;
  // @ts-ignore
  @ViewChild('dpTo') datepickerTo: MatDatepicker<Date>;
  constructor(private route: ActivatedRoute, private authenticationService: AuthenticationService, private router: Router) {
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

  dateFrom = new FormControl(new Date(1995, 11, 12));
  serializedDateFrom = new FormControl((new Date()).toISOString());
  dateTo = new FormControl(new Date(2024, 11, 12));
  serializedDateTo = new FormControl((new Date()).toISOString());

  chosenYearHandlerFrom(normalizedYear: Date) {
    this.dateFrom = new FormControl(new Date(1995, 11, 12));
    const ctrlValue = this.dateFrom.value;
    // @ts-ignore
    ctrlValue.setFullYear(normalizedYear.getFullYear());
    this.dateFrom.setValue(ctrlValue);
    this.datepicker.close();
  }

  chosenYearHandlerTo(normalizedYear: Date) {
    this.dateTo = new FormControl(new Date(2024, 11, 12));
    const ctrlValue = this.dateTo.value;
    // @ts-ignore
    ctrlValue.setFullYear(normalizedYear.getFullYear());
    this.dateTo.setValue(ctrlValue);
    this.datepickerTo.close();
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

  logout() {
    this.router.navigate(['/dashboard']);
    this.authenticationService.logout();
  }

  getCurrentUser() {
    let currentUser = localStorage.getItem("currentUser")
    // @ts-ignore
    return JSON.parse(currentUser).name;
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
  protected readonly localStorage = localStorage;
  protected readonly JSON = JSON;
}
