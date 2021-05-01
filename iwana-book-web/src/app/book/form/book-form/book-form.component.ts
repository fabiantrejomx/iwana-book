import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Author } from 'src/app/model/author/author';
import { Book } from 'src/app/model/book/book';
import { Category } from 'src/app/model/category/category';
import { AuthorService } from 'src/app/service/author.service';
import { BookService } from 'src/app/service/book.service';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {

  book: Book=new Book();
  title: string = "crear book";
  errores: string[];
  categories: Category[];
  authors:Author[];
  constructor(private bookService:BookService,private router: Router, 
    private activatedRouter: ActivatedRoute,
    private categoryService:CategoryService,
    private authorService:AuthorService) { }

  ngOnInit(): void {
    this.loadBook();
    this.getAuthors();
    this.getCategories();
  }

  create(): void {
    this.bookService.create(this.book).subscribe(data => {
      this.router.navigate(['book']);
      alert("Succesfully Added Product details")}
      ,
      err => {
        this.errores = err.error.errors as string[];
        console.error('Error' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  loadBook(): void {
    this.activatedRouter.params.subscribe(params => {
      let id = params['id']
      if (id) {
        this.bookService.getBookByID(id).subscribe((book) => this.book = book)
      }
    })
  }

  update(): void{
    this.bookService.update(this.book,this.book.id).subscribe( json => {
      this.router.navigate(['/book'])
    })
  }

  public getAuthors() {
    this.authorService.getAuthors()
      .subscribe((response) => {
        this.authors = response;
      })
  }

  public getCategories(){
    this.categoryService.getCategories()
    .subscribe((response) => {
      this.categories = response;
    });
  }

}
