import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Author } from '../model/author/author';
import { AuthorForm } from '../model/author/author.form';
import { ServiceCrudService } from '../service-crud.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private serviceCrud:ServiceCrudService,private http:HttpClient) { }

  public getAuthors(): Observable<Author[]> {
    const url = `${environment.apiBaseUrl}/author`;
    return this.serviceCrud.getAll(url);
  }

  public deleteAuthor(authorID: number) {
    const url = `${environment.apiBaseUrl}/author/${authorID}`;
    return this.serviceCrud.delete(url);
  }

  public create(author:AuthorForm): Observable<any> {
    const url = `${environment.apiBaseUrl}/author`;
    return this.http
      .post<Author>(url,author, {
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
