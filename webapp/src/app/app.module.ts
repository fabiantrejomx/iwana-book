import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookComponent } from './book/book.component';
import { AuthorComponent } from './author/author.component';
import { CategoryComponent } from './category/category.component';
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {NgxPaginationModule} from 'ngx-pagination';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {ReactiveFormsModule} from "@angular/forms";
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import { RankingComponent } from './ranking/ranking.component';


const router: Routes = [
  {path: 'Book', component: BookComponent},
  {path: 'Author', component: AuthorComponent},
  {path: 'Category', component: CategoryComponent},
  {path: 'ranking', component: RankingComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    AuthorComponent,
    CategoryComponent,
    RankingComponent
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        RouterModule.forRoot(router),
        BrowserAnimationsModule,
        NgxPaginationModule,
        NgbModule,
        ReactiveFormsModule,
      NgMultiSelectDropDownModule.forRoot()
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
