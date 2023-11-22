
import { AppComponent } from './app-component/app-component.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app.routing.module';
import { UserListComponent } from './user-list/user-list.component';
import { RouterModule } from '@angular/router';
import { AppContainerComponent } from './app-container/app-container.component';
import { AppHeaderComponent } from './app-header/app-header.component';


@NgModule({
    declarations: [
        AppComponent,
        AppContainerComponent,
        AppHeaderComponent,
        UserListComponent,

        // Add other components here
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        RouterModule,
    ],
    providers: [],
    bootstrap: [AppComponent],
    exports: [AppComponent] // Add this line to export AppComponent

})
export class AppModule { }
