import {Component, OnInit} from '@angular/core';
import {HttpRequestBookService} from "./service/http-request-book.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {
  title = 'webapp';
  constructor(private httpService: HttpRequestBookService) { }

  ngOnInit(){

  }

}
