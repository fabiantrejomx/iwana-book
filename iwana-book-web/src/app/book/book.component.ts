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
  public book:Book;
  constructor(private bookService:BookService) { }

  ngOnInit(): void {
  this.getBooks();
  }

  public getBooks():void{
    this.bookService.getCategories()
    .subscribe((response) => {
     this.books = response;
   });
  }

  public deleteBook(id:number){
    this.bookService.deleteBook(id).subscribe(
      (response)=>{this.book=response;
        this.getBooks();}
    )
  }

}
