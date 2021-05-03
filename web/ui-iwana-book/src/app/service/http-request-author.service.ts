import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

import {environment} from '../../environments/environment';
export const apiBaseUrl = `${environment.apiBaseUrl}`;


@Injectable({
  providedIn: 'root'
})
export class HttpRequestAuthorService {

  constructor(private http: HttpClient) { }

  /* Service for author*/

  /*GET*/
  public getAuthors(API_URL: string) {
    console.log(apiBaseUrl)
    console.log(API_URL)
    return this.http.get<any>(apiBaseUrl + API_URL);
  }

  /*DELETE*/
  public deleteAuthor(API_URL: string) {
    return this.http.delete<any>(apiBaseUrl+ API_URL);
  }

  /*CREATE*/
  public postAuthor(API_URL: string, body) {
    return this.http.post<any>(apiBaseUrl+ API_URL, body);
  }

  /*GET BY ID*/
  public getAuthor(API_URL: string) {
    return this.http.get<any>(apiBaseUrl+ API_URL);
  }

  /*PUT*/
  public putAuthor(API_URL: string, body) {
    return this.http.put<any>(apiBaseUrl+ API_URL, body);
  }
}
