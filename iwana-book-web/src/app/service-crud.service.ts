import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Book } from './model/book/book';
import { map, catchError } from 'rxjs/operators';
import { Router, RouterLink } from '@angular/router';



@Injectable({
  providedIn: 'root'
})
export class ServiceCrudService {
  httpHeaders: HttpHeaders | { [header: string]: string | string[]; };

  constructor(private http:HttpClient,private router:Router) { }

  public getAll(url: string): Observable<any> {
    return this.http
      .get(url, { responseType: "json" })
      .map((response: HttpResponse<any>) => {
        return response;
      })
      .catch((error: any) =>
        Observable.throw(error)
      );
  } 

  public delete(url: string): Observable<any> {
    return this.http
      .delete(url);
  }
 

  getBook(url:string): Observable<Book> {
    return this.http.get<Book>(url).pipe(
      catchError((e) => {
        this.router.navigate(['book']);
        console.error(e.error.mensaje);
        return throwError(e);
      })
    );
  }

}

