// app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './app/user-list/user-list.component';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppContainerComponent } from './app/app-container/app-container.component';
import { AppHeaderComponent } from './app/app-header/app-header.component';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppComponent } from './app/app-component/app-component.component';
import { UserService } from './app/services/user.service';
import { UserWeeklyViewComponent } from './app/user-weekly-view/user-weekly-view.component';
import { CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';
import { AppChartComponent } from './app/app-chart/app-chart.component';
import { UserStatusComponent } from './app/user-status/user-status.component';
import { FormsModule } from '@angular/forms';
import { FilterDataPipe } from './app/user-weekly-view/filterData.pipe'; // Fix the import path
import { AboutViewComponent } from './app/about-view/about-view.component';

const routes: Routes = [
  { path: 'Occupation', component: UserListComponent },
  { path: 'Week', component: UserWeeklyViewComponent  },
  { path: 'active', component: UserStatusComponent  },
  { path: 'about', component: AboutViewComponent },

  // Add other routes as needed
];

@NgModule({
  declarations: [
    AppComponent,
    AppContainerComponent,
    AppHeaderComponent,
    UserListComponent,
    AppChartComponent,
    UserWeeklyViewComponent,
    FilterDataPipe,
    

    // Add other components here
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes, { useHash: true }),
    CanvasJSAngularChartsModule,
    FormsModule,


    ],
  providers: [UserService],
  bootstrap: [AppComponent],

})
export class AppModule { }

platformBrowserDynamic().bootstrapModule(AppModule)


