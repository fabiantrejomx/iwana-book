import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceCrudService {

  constructor(private http:HttpClient) { }
  public get (url:string){
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

