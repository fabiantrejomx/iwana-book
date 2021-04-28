import { Injectable, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ServiceCrudService } from '../service-crud.service';
import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:HttpClient, private serviceCrud:ServiceCrudService) { }

  getCategories() {
    const url = `${environment.apiBaseUrl}/category/categories`;
   return this.serviceCrud.getAll(url);
  }
}
