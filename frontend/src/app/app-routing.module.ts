import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashComponent} from './dash/dash.component';
import {statisticResolver} from "./services/statistic.resolver";

const routes: Routes = [{path: 'dashboard/:name/:id', component: DashComponent, resolve: { statisticResolver: statisticResolver }}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
