import {Resolve, ResolveFn} from '@angular/router';
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {StatisticService} from "./statistic.service";

@Injectable()
export class statisticResolver implements Resolve<Observable<any>> {
  constructor(private dataService: StatisticService) {}

  resolve(): Observable<any> {
    return this.dataService.findAll();
  }
}
