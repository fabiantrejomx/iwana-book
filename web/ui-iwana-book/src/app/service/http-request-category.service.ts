import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from '../../environments/environment';
export const apiBaseUrl = `${environment.apiBaseUrl}`;

@Injectable({
  providedIn: 'root'
})
export class HttpRequestCategoryService {

  constructor(private http: HttpClient) { }

  /* Service for category*/

  /*GET*/
  public getCategory(API_URL: string) {
    console.log(apiBaseUrl)
    console.log(API_URL)
    return this.http.get<any>(apiBaseUrl + API_URL);
  }

  /*GET BY ID*/
  public getCategoryById(API_URL: string) {
    return this.http.get<any>(apiBaseUrl+ API_URL);
  }

  /*DELETE*/
  public deleteCategory(API_URL: string) {
    return this.http.delete<any>(apiBaseUrl+ API_URL);
  }

  /*CREATE*/
  public postCategory(API_URL: string, body) {
    return this.http.post(apiBaseUrl+ API_URL, body);
  }

  /*PUT*/
  public putCategory(API_URL: string, body) {
    return this.http.put<any>(apiBaseUrl+ API_URL, body);
  }

}
