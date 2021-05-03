import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorComponent } from './author/author.component';
import { BookComponent } from './book/book.component';
import { CategoryComponent } from './category/category.component';

const routes: Routes = [
  {path: '',
  children: [
  {path: "author", component: AuthorComponent},
  {path: "book", component: BookComponent},
  {path: "category", component: CategoryComponent},
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CRUDRoutingModule { }
