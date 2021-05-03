import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
export const apiBaseUrl = `${environment.apiBaseUrl}`;

@Injectable({
  providedIn: 'root'
})
export class HttpRequestRankingService {

  constructor(private http: HttpClient) { }

  public getBookWithRanking(API_URL: string) {
    return this.http.get<any>(apiBaseUrl + API_URL);
  }
}
