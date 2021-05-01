import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Author } from '../model/author/author';
import { ServiceCrudService } from '../service-crud.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private serviceCrud:ServiceCrudService) { }

  public getAuthors(): Observable<Author[]> {
    const url = `${environment.apiBaseUrl}/author`;
    return this.serviceCrud.getAll(url);
  }

  public deleteAuthor(authorID: number) {
    const url = `${environment.apiBaseUrl}/author/${authorID}`;
    return this.serviceCrud.delete(url);
  }
}
