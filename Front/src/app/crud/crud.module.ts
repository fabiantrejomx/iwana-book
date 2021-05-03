import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CRUDRoutingModule } from './crud-routing.module';
import { BookComponent } from './book/book.component';
import { AuthorComponent } from './author/author.component';
import { CategoryComponent } from './category/category.component';


@NgModule({
  declarations: [
    BookComponent,
    AuthorComponent,
    CategoryComponent
  ],
  imports: [
    CommonModule,
    CRUDRoutingModule
  ]
})
export class CRUDModule { }
