import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Statistic} from "../model/entities";

@Injectable({
  providedIn: 'root'
})
export class StatisticService {

  private statisticUrl: string;

  constructor(private http: HttpClient) {
    this.statisticUrl = '/api/population-statistic';
  }

  public findAll(): Observable<Statistic[]> {
    return this.http.get<Statistic[]>(this.statisticUrl);
  }
}
