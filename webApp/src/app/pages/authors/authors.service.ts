import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HOST } from "../constant/http.constant";
import { AuthorsRequest } from "./authors.request";
import { AuthorsResponse } from "./authors.response";


@Injectable({
  providedIn: 'root'
})
export class AuthorsService {

  constructor(private http: HttpClient) { }

  public createAuthors(request: AuthorsRequest): Observable<boolean> {
    return this.http.post<boolean>(HOST + "/author", request)
  }

  public getAuthors(): Observable<AuthorsResponse>{
    return this.http.get<AuthorsResponse>(HOST + "author/list")
  }

  public updateAuthors(authorId: number, request: AuthorsRequest): Observable<boolean> {
    return this.http.put<boolean>(HOST + `author/${authorId}`, request)
  }

  public deleteAuthors(authorId: number): Observable<boolean>{
    return this.http.delete<boolean>(HOST + `author/${authorId}`)
  }
  
}
