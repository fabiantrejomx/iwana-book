import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const bookRoutes: Routes = [
  {
    path: 'list', 
    loadChildren: () => import('./pages/books/list/list.module').then(m => m.ListModule)
  },
  {
    path: 'add',
    loadChildren: () => import('./pages/books/add/add.module').then(m => m.AddModule)
  },
]
const authorsRoutes: Routes = [
  {
    path: 'list', 
    loadChildren: () => import('./pages/authors/list/list.module').then(m => m.ListModule)
  },
  {
    path: 'add',
    loadChildren: () => import('./pages/authors/add/add.module').then(m => m.AddModule)
  },
  
]
const categoryRoutes: Routes = [
  {
    path: 'list', 
    loadChildren: () => import('./pages/category/list/list.module').then(m => m.ListModule)
  },
  {
    path: 'category',
    loadChildren: () => import('./pages/category/category/category.module').then(m => m.CategoryModule)
  },
]

const routes: Routes = [
  {
    path: 'books',
    children: bookRoutes
  },
  {
    path: 'authors',
    children: authorsRoutes
  },
  {
    path: 'category',
    children: categoryRoutes
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
