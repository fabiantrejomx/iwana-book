import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Book } from '../model/book/book';
import { ServiceCrudService } from '../service-crud.service';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  httpHeaders: HttpHeaders | { [header: string]: string | string[]; };
  constructor(private serviceCrud:ServiceCrudService,private http:HttpClient) { }

  public getBooks(): Observable<Book[]> {
    const url = `${environment.apiBaseUrl}/book?expand=categories,authors`;
    return this.serviceCrud.getAll(url);
  }

  public deleteBook(bookID: number) {
    const url = `${environment.apiBaseUrl}/book/${bookID}`;
    return this.serviceCrud.delete(url);
  }

  public getBookByID(bookID:number){
    const url = `${environment.apiBaseUrl}/book/${bookID}`;
    return this.serviceCrud.getBook(url);
  }

  public update(book: Book,bookID:number): Observable<any> {
    const url = `${environment.apiBaseUrl}/book/${bookID}`;
    return this.http
      .put<any>(url, book, {
        headers: this.httpHeaders,
      })
      .pipe(
        catchError((e) => {
          if (e.status == 400) {
            return throwError(e);
          }
          console.error(e.error.mensaje);
          return throwError(e);
        })
      );
  }

  create(book: Book): Observable<any> {
    const url = `${environment.apiBaseUrl}/book`
    return this.http
      .post<Book>(url, book, {
        headers: this.httpHeaders,
      })
      .pipe(
        map((response: any) => response),
        catchError((error) => {
          if (error.status === 400) {
            return throwError(error);
          }
        })
      );
  }
}
