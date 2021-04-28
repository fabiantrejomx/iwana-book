import { Injectable, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ServiceCrudService } from '../service-crud.service';
import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Category } from '../model/category/category';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:HttpClient, private serviceCrud:ServiceCrudService) { }

  getCategories():Observable<Category[]> {
    const url = `${environment.apiBaseUrl}/category/categories`;
   return this.serviceCrud.getAll(url);
  }
}
