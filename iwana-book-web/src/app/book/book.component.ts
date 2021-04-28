import { Component, OnInit } from '@angular/core';
import { Book } from '../model/book/book';
import { BookService } from '../service/book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  public books:Book[];
  constructor(private bookService:BookService) { }

  ngOnInit(): void {
    this.bookService.getCategories()
   .subscribe((response) => {
    this.books = response;
  });
  }

}
