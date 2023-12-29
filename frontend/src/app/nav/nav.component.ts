import {Component, inject} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {generate, Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {StatisticService} from "../services/statistic.service";
import {ActivatedRoute} from "@angular/router";
import {Statistic} from "../model/Entities";
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {
  constructor(private router: Router, private service: StatisticService) {
  }

  public statisticData: Statistic[] = [];

  navigateWithParams(codigo:string) {
    this.router.navigate(['/dashboard', codigo]);
  }

  /**
   * Retrieves the list of federal states from the statistic data.
   *
   * @returns {string[]} An array of strings representing the federal states.
   */
  getFederalStates(): string[] {
    if (this.statisticData.length == 0) {
      this.service.findAll().subscribe(
        value => this.statisticData = value
      );
    }
    let dataArr: string[] = [];
    for (let item of this.statisticData) {
      item.federalStateDataList?.forEach(federalState =>
        // @ts-ignore
        dataArr.push(federalState.federalStatesExtensionEnum));
    }
    return dataArr;
  }

  getFlagImg(name:string): string {
    if (this.statisticData.length == 0) {
      this.service.findAll().subscribe(
        value => this.statisticData = value
      );
    }
    let img: string = "";
    for (let item of this.statisticData) {
      item.federalStateDataList?.forEach(federalState => {
        if (name == federalState.federalStatesExtensionEnum) {
          // @ts-ignore
          img = "assets/" + federalState.MetaData[0].Codigo + ".gif";
        }
      });

    }
    return img;
  }

  getRegion(federalStateMenu: string): string[] {
    if (this.statisticData.length == 0) {
      this.service.findAll().subscribe(
        value => this.statisticData = value
      );
    }
    let dataArr: string[] = [];
    for (let item of this.statisticData) {
      item.federalStateDataList?.forEach(federalState => {
        // @ts-ignore
        if (federalStateMenu == federalState.federalStatesExtensionEnum) {
          federalState.regionDataList?.forEach( region => {
            // @ts-ignore
            dataArr.push(region.MetaData[0].Nombre);
          })
        }
      });
    }
    return dataArr;
  }

  private breakpointObserver = inject(BreakpointObserver);
  menuItems = this.getFederalStates();
  municipios = ['Cáceres', 'Badajoz'];
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
}
