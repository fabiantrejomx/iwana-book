import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';



@Injectable({
  providedIn: 'root'
})
export class ServiceCrudService {

  constructor(private http:HttpClient) { }

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

}

