import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HOST } from "../constant/http.constant";
import { AuthorsRequest } from "./authors.request";


@Injectable({
  providedIn: 'root'
})
export class AuthorsService {

  constructor(private http: HttpClient) { }

  public createAuthors(request: AuthorsRequest): Observable<boolean> {
    return this.http.post<boolean>(HOST + "/author", request)
  }
  
}
