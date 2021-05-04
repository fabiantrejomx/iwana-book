import { Component, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { Author } from '../model/author/author';
import { Book } from '../model/book/book';
import { Category } from '../model/category/category';
import { BookService } from '../service/book.service';
import { BookFormComponent } from './form/book-form/book-form.component';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  public books:Book[];
  public book:Book;
  public authors:Author[];
  public categories:Category[];
  constructor(private bookService:BookService,private modalService:BsModalService) { }

  ngOnInit(): void {
  this.getBooks();
  }

  public getBooks():void{
    this.bookService.getBooks()
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

  public openCreateModal(): void {
    const bsModalRef = this.modalService.show(BookFormComponent, {
      class: 'modal-lg',
    });
  bsModalRef.content.title = 'Crear Libro';
  bsModalRef.content.onClose = () => {
  bsModalRef.hide();
      this.getBooks();
    };
  }

  public updateBook(book:Book): void {
    const bsModalRef = this.modalService.show(BookFormComponent, {
      class: 'modal-lg',
    });
  bsModalRef.content.title = 'Editar Libro';
  bsModalRef.content.books=book;
  bsModalRef.content.onClose = () => {
  bsModalRef.hide();
      this.getBooks();
    };
  }
}
