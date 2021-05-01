import { Injectable, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ServiceCrudService } from '../service-crud.service';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { Category } from '../model/category/category';
import { map, catchError } from 'rxjs/operators';
import { CategoryForm } from '../model/category/category.form';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http:HttpClient, private serviceCrud:ServiceCrudService) { }

  getCategories():Observable<Category[]> {
    const url = `${environment.apiBaseUrl}/category/categories`;
   return this.serviceCrud.getAll(url);
  }

  public deleteCategory(categoryID:number){
    const url = `${environment.apiBaseUrl}/category/${categoryID}`;
    return this.serviceCrud.delete(url);
  }

  public create(category:CategoryForm): Observable<any> {
    const url = `${environment.apiBaseUrl}/category`;
    return this.http
      .post<Category>(url,category, {
        headers: this.httpHeaders,
      })
      .pipe(
        map((response: any) => response),
        catchError((error) => {
          if (error.status === 400) {
            return throwError(error);
          }
        })
      );
  }
}
