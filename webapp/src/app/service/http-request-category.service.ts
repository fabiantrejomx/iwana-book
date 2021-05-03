import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
export const apiBaseUrl = `${environment.apiBaseUrl}`;

@Injectable({
  providedIn: 'root'
})
export class HttpRequestCategoryService {

  constructor(private http: HttpClient) { }

  public getCategories(API_URL: string) {
    return this.http.get<any>(apiBaseUrl+ API_URL);
  }

  public getCategory(API_URL: string) {
    return this.http.get<any>(apiBaseUrl+ API_URL);
  }

  public putCategory(API_URL: string, body) {
    return this.http.put<any>(apiBaseUrl+ API_URL, body);
  }

  public postCategory(API_URL: string, body) {
    return this.http.post(apiBaseUrl + API_URL, body);
  }

  public deleteCategory(API_URL: string) {
    return this.http.delete<any>(apiBaseUrl+ API_URL);
  }

}
