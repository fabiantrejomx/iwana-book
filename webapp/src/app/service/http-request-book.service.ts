import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
export const apiBaseUrl = `${environment.apiBaseUrl}`;

@Injectable({
  providedIn: 'root'
})
export class HttpRequestBookService {

  constructor(private http: HttpClient) { }

  public getBook(API_URL: string) {
    return this.http.get<any>(apiBaseUrl+ API_URL);
  }

  public deleteBook(API_URL: string) {
    return this.http.delete<any>(apiBaseUrl+ API_URL);
  }

  public putBook(API_URL: string, body) {
    return this.http.put<any>(apiBaseUrl+ API_URL, body);
  }

  public postBook(API_URL: string, body) {
    return this.http.post<any>(apiBaseUrl+ API_URL, body);
  }

  public getOrderBook(API_URL: string) {
    return this.http.get<any>(apiBaseUrl+ API_URL);
  }
}
