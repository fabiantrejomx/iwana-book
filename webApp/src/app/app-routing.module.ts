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
 { 
   path: 'edit', 
   loadChildren: () => import('./pages/books/edit/edit.module').then(m => m.EditModule) 
  },
 {
   path: 'delete',
   loadChildren: () => import('./pages/books/delete/delete.module').then(m => m.DeleteModule) 
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
 { 
   path: 'edit', 
   loadChildren: () => import('./pages/authors/edit/edit.module').then(m => m.EditModule) 
  },
 {
   path: 'delete',
   loadChildren: () => import('./pages/authors/delete/delete.module').then(m => m.DeleteModule) 
  },
]
const categoryRoutes: Routes = [
  {
    path: 'list', 
    loadChildren: () => import('./pages/category/list/list.module').then(m => m.ListModule)
  },
  {
    path: 'add',
    loadChildren: () => import('./pages/category/add/add.module').then(m => m.AddModule)
  },
 { 
   path: 'edit', 
   loadChildren: () => import('./pages/category/edit/edit.module').then(m => m.EditModule) 
  },
 {
   path: 'delete',
   loadChildren: () => import('./pages/category/delete/delete.module').then(m => m.DeleteModule) 
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
