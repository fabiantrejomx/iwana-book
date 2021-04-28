import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Book } from '../model/book/book';
import { ServiceCrudService } from '../service-crud.service';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private serviceCrud:ServiceCrudService) { }

  public getCategories(): Observable<Book[]> {
    const url = `${environment.apiBaseUrl}/book`;
    return this.serviceCrud.getAll(url);
  }

  public deleteBook(bookID: number) {
    const url = `${environment.apiBaseUrl}/book/${bookID}`;
    return this.serviceCrud.delete(url);
  }
}
