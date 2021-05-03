import { Component, OnInit, ViewChild } from '@angular/core';
import { BooksService } from '../books.service';
import { BooksDTO } from "../books.dto";
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { BookComponent } from '../../books/book/book.component';
import { AuthorsService } from '../../authors/authors.service';
import { CategoryService } from '../../category/category.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

export interface DialogData {
  bookId: number;
}

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  books: MatTableDataSource<BooksDTO[]>;
  displayedColumns: string[] = ['id','title','pages','isbn','price','editorial','releaseDate','authors','categories','actions']
  bookId: number;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private booksService: BooksService,
    private authorsService: AuthorsService,
    private categoryService: CategoryService,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getList()
  }

  public getList(): void {
   this.booksService.getBook('/book?expand=categories,authors').subscribe(
     (data: [BooksDTO[]]) => {
     console.log(data);
     this.books = new MatTableDataSource(data);
     this.books.paginator = this.paginator;
     this.books.sort = this.sort;
   })
  }

  public onCreate() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width ="60%";
    this.dialog.open(BookComponent,dialogConfig);
  }

  public onEdit(row) {
    this.bookId = row;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    dialogConfig.data = {bookId: this.bookId}
    this.dialog.open(BookComponent,dialogConfig);
  }

  public onDelete(row) {
    this.bookId = row;
    this.booksService.deleteBooks(this.bookId).subscribe(response => {
      alert(response ? "Book Deleted" : "Book deleted failed")
    });
  }
  



}
