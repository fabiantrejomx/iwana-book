import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from '../../environments/environment';
export const apiBaseUrl = `${environment.apiBaseUrl}`;

@Injectable({
  providedIn: 'root'
})
export class HttpRequestBookService {

  constructor(private http: HttpClient) {
  }

  /* Service for book*/

  /*GET*/
  public getBooks(API_URL: string) {
    console.log(apiBaseUrl)
    console.log(API_URL)
    return this.http.get<any>(apiBaseUrl + API_URL);
  }

  /*GET BY ID*/
  public getBook(API_URL: string) {
    return this.http.get<any>(apiBaseUrl + API_URL);
  }

  /*DELETE*/
  public deleteBook(API_URL: string) {
    return this.http.delete<any>(apiBaseUrl + API_URL);
  }

  /*PUT*/
  public putBook(API_URL: string, body) {
    return this.http.put<any>(apiBaseUrl + API_URL, body);
  }

  /*POST*/
  public postBook(API_URL: string, body) {
    return this.http.post(apiBaseUrl + API_URL, body);
  }

  public getBookById(API_URL: string) {
    return this.http.get<any>(apiBaseUrl + API_URL);
  }

  public getBookWithRanking(API_URL: string) {
    return this.http.get<any>(apiBaseUrl + API_URL);
  }

  public getOrderBook(API_URL: string) {
    return this.http.get<any>(apiBaseUrl + API_URL);
  }
}
