import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthorComponent } from './author/author.component';
import { BookComponent } from './book/book.component';
import { BookFormComponent } from './book/form/book-form/book-form.component';
import { CategoryComponent } from './category/category.component';


const routes: Routes = [
{path: 'category', component: CategoryComponent},
{path: 'book', component: BookComponent},
{path: 'author', component:AuthorComponent},
{path: 'book/form', component:BookFormComponent},
{path: 'book/form/:id', component: BookFormComponent}

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
