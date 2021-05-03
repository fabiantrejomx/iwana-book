import {Component, OnInit} from '@angular/core';
import {HttpRequestBookService} from "./service/http-request-book.service";
import {environment} from "../environments/environment";
export const apiBaseUrl = `${environment.apiBaseUrl}`;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit{
  title = 'ui-iwana-book';

  constructor(private httpService: HttpRequestBookService) { }

  ngOnInit(): void {

  }
}


