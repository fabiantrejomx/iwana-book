import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookComponent } from './book/book.component';
import { AuthorComponent } from './author/author.component';
import { CategoryComponent } from './category/category.component';
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {NgxPaginationModule} from 'ngx-pagination';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RankingComponent } from './ranking/ranking.component';


const router: Routes = [
  {path: 'book', component: BookComponent},
  {path: 'author', component: AuthorComponent},
  {path: 'category', component: CategoryComponent},
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
        AppRoutingModule,
        RouterModule.forRoot(router),
        HttpClientModule,
      FormsModule,
        BrowserAnimationsModule,
        NgxPaginationModule,
        NgbModule,
        ReactiveFormsModule,
    ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
