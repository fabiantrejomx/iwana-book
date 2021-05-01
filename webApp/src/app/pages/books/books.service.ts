import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HOST } from "../constant/http.constant";
import { BooksDTO } from "./books.dto";
import { BooksRequest } from "./books.request";
import { BooksResponse } from "./books.response";


@Injectable({
    providedIn: 'root'
})
export class BooksService {

    constructor(private http: HttpClient) { }

    public createBooks(request: BooksRequest): Observable<boolean> {
      return this.http.post<boolean>(HOST + "book", request)
    }
  
    public getBooks(): Observable<[BooksDTO[]]>{
      return this.http.get<[BooksDTO[]]>(HOST + "book/list")
    }
  
    public updateBooks(booksId: number, request: BooksRequest): Observable<boolean> {
      return this.http.put<boolean>(HOST + `book/${booksId}`, request)
    }
  
    public deleteBooks(booksId: number): Observable<boolean>{
      return this.http.delete<boolean>(HOST + `book/${booksId}`)
    }

}
