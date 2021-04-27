import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { ServiceCrudService } from '../service-crud.service';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:HttpClient, private serviceCrud:ServiceCrudService) { }

  getCategories() {
    const url = `${environment.apiBaseUrl}/category`;
   this.http.get(url);
  }
}
