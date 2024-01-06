import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from "./model/user";
import {Router} from "@angular/router";
import {AuthenticationService} from "./services/authentication.service";
import {Title} from "@angular/platform-browser";



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Análisis Riesgo de despoblación';

  currentUser: User | undefined;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private titleService: Title
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    this.titleService.setTitle("Análisis Demográfico");
  }
}
