import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ServiceCrudService } from '../service-crud.service';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private serviceCrud:ServiceCrudService) { }

  getCategories() {
    const url = `${environment.apiBaseUrl}/book`;
   return this.serviceCrud.getAll(url);
  }
}
