import { Component, OnInit } from '@angular/core';
import {BookInterface} from "../interfaces/BookInterface";
import {CategoryInterface} from "../interfaces/CategoryInterface";
import {AuthorInterface} from "../interfaces/AuthorInterface";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {HttpRequestAuthorService} from "../service/http-request-author.service";
import {HttpRequestCategoryService} from "../service/http-request-category.service";
import {HttpRequestBookService} from "../service/http-request-book.service";
@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.sass']
})
export class BookComponent implements OnInit {

  closeResult = '';
  pageActual: number=1;
  books: BookInterface[];
  book: BookInterface;
  categories: CategoryInterface[];
  category: CategoryInterface;
  authors: AuthorInterface[];
  author: AuthorInterface;
  public form: FormGroup;
  listAuthors = [];
  listCategories = [];

  constructor(
    private bookService: HttpRequestBookService,
    private authorService: HttpRequestAuthorService,
    private categoryService: HttpRequestCategoryService,
    private modalService: NgbModal,
    private  formBuilder: FormBuilder) { }

  getAuthors(){
    this.authorService.getAuthors("/author/list")
      .subscribe(authors=>{ this.authors=authors
        console.log(this.authors=authors);
      })
  }
  getCategories(){
    this.categoryService.getCategory("/category/list")
      .subscribe(categories=>{ this.categories=categories
        console.log(this.categories=categories);
      })
  }

  getListBooks(){
    this.bookService.getBooks('/book?expand=categories,authors')
      .subscribe((books: BookInterface[]) =>{ this.books=books; console.log(this.books=books);
      },(error)=>{
        alert('Houston, we have a problem');
      });
  }

  deleteBook(bookId: number){
    console.log(bookId);
    this.bookService.deleteBook(`/book/${bookId}` )
      .subscribe((books: BookInterface[]) =>{ this.getListBooks();
      },(error)=>{
        alert('Houston, we have a problem');
      });
  }

  public postBook(){
    this.form.value.authors.map((id)=> this.listAuthors.push(parseInt(id)) )
    this.form.value.categories.map((id)=>this.listCategories.push(id))

    console.log(
      this.form.value.authors,
      this.form.value.categories,

    );
    this.bookService.postBook(`/book`,
      {
        title:this.form.value.title,
        pages:this.form.value.pages,
        isbn:this.form.value.isbn,
        price:this.form.value.price,
        summary:this.form.value.summary,
        editorial:this.form.value.editorial,
        datePublication:this.form.value.releaseDate,
        authors: this.listAuthors,
        categories: this.listCategories,
      }).subscribe(request => {
      console.log('Success post!',this.form.value);
      this.form.reset();
      this.getListBooks();
    })
  }

  public putBook(bookId : number){
    this.form.value.authors.map((id)=> this.listAuthors.push(parseInt(id)) )
    this.form.value.categories.map((id)=>this.listCategories.push(id))

    console.log(
      this.form.value.authors,
      this.form.value.categories,

    );
    this.bookService.putBook(`/book/${bookId}`,
      {
        title:this.form.value.title,
        pages:this.form.value.pages,
        isbn:this.form.value.isbn,
        price:this.form.value.price,
        summary:this.form.value.summary,
        editorial:this.form.value.editorial,
        datePublication:this.form.value.releaseDate,
        authors: this.listAuthors,
        categories: this.listCategories,
      }).subscribe(request => {
      console.log('Success post!',this.form.value);
      this.form.reset();
      this.getListBooks();
    })
  }

  /* MODAL FOR ADD*/
  openModalAdd(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }


  /*MODAL UPDATE*/
  openUpdate(contentUpdate, bookID) {
    this.bookService.getBook(`/book/${bookID}?expand=authors,categories` )
      .subscribe((book: BookInterface) =>{
        this.book=book;
        console.log(book.categories);
      },(error)=>{
        alert('Houston, we have a problem');
      });
    this.modalService.open(contentUpdate, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  getListOrderBooks(limit: number, offset: number, order: string, pages: string){
    console.log(this.form.value.limit, offset, order, pages)
    this.bookService.getOrderBook(`/book?expand=categories,authors&limit=${this.form.value.limit}&offset=${offset}&sortingOrder=${order}&sortField=${pages}`)
      .subscribe( books=>{ this.books=books
        console.log(this.books=books)
      },(error)=>{
        alert('ocurrio un error');
      });
  }

  getOrderedPagination(): void {
    console.log(this.form.value.limit,this.form.value.offset, this.form.value.order, this.form.value.pages);
    this.getListOrderBooks(this.form.value.limit, this.form.value.offset, this.form.value.order, this.form.value.pages);
  }

  ngOnInit() {
    this.getListBooks()
    this.getAuthors()
    this.getCategories()
    this.form = this.formBuilder.group({
      title: ['',[ Validators.required ]]  ,
      pages: ['',[ Validators.required, Validators.min(5)]] ,
      isbn: ['',[ Validators.required, Validators.pattern(/^([0-9]{4}-[0-9]{4}-[0-9]{1})$/g)]] ,
      price: ['',[ Validators.required , Validators.min(5),Validators.pattern(/^[-+]?[0-9]*\.?[0-9]$/)]] ,
      summary: ['',[ Validators.required ]] ,
      editorial: ['',[ Validators.required ]] ,
      releaseDate: ['', [Validators.required, Validators.pattern(/^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/)]],
      authors: ['',[ Validators.required ]],
      categories: ['',[ Validators.required ]],
      limit:['',[ Validators.required ]],
      offset:['',[ Validators.nullValidator]],
      order: ['',[ Validators.nullValidator]],
    });


  }
}

