import { Component, OnInit } from '@angular/core';
import {BookInterface} from "../interfaces/BookInterface";
import {AuthorInterface} from "../interfaces/author-interface";
import {CategoryInterface} from "../interfaces/category-interface";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpRequestCategoryService} from "../service/http-request-category.service";
import {HttpRequestBookService} from "../service/http-request-book.service";
import {HttpRequestAuthorService} from "../service/http-request-author.service"; // module

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})

export class BookComponent implements OnInit {
  pageActual: number = 1;
  books: BookInterface[];
  authors: AuthorInterface[];
  categories: CategoryInterface[];
  category: CategoryInterface;
  book: BookInterface;
  form: FormGroup;
  closeResult = '';
  selectedItems = [];
  authorsArr = [];
  categoriesArr = [];

  constructor(private bookService: HttpRequestBookService, private categoryService: HttpRequestCategoryService, private authorService: HttpRequestAuthorService, private modalService: NgbModal, private  formBuilder: FormBuilder) {
  }


  getListBooks() {
    this.bookService.getBook('/book?expand=categories,authors')
      .subscribe(books => {
        this.books = books
        console.log(this.books = books)
      }, (error) => {
        alert('ocurrio un error');
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

postBook(){
  this.form.value.authors.map((id)=> this.authorsArr.push(parseInt(id)) )
  this.form.value.categories.map((id)=>this.categoriesArr.push(id))

  this.bookService.postBook(`/book`,
    {title:this.form.value.title,
      pages: this.form.value.pages,
      isbn:this.form.value.isbn,
      price:this.form.value.price,
      summary:this.form.value.summary,
      editorial:this.form.value.editorial,
      datePublication: this.form.value.releaseDate,
      authors:this.authorsArr,
      categories: this.categoriesArr,
    })
    .subscribe(request => {
      console.log('Success!',this.form.value);
      this.getListBooks();
    })
}

putBook(bookID: number){
  this.form.value.authors.map((id)=> this.authorsArr.push(parseInt(id)) )
  this.form.value.categories.map((id)=>this.categoriesArr.push(id))

  this.bookService.putBook(`/book/${bookID}`,
    {title:this.form.value.title,
      pages: this.form.value.pages,
      isbn:this.form.value.isbn,
      price:this.form.value.price,
      summary:this.form.value.summary,
      editorial:this.form.value.editorial,
      datePublication: this.form.value.releaseDate,
     authors : this.authorsArr,
      categories:this.categoriesArr,
    })
    .subscribe(request => {
      console.log('Success!',this.form.value);
      this.getListBooks();
    })
}

  deleteBook(bookID: number){
    this.bookService.deleteBook(`/book/${bookID}`)
      .subscribe((books: BookInterface[]) =>{ this.getListBooks();
      },(error)=>{
        alert('ocurrio un error');
      });
  }



  openUpdate(contentUpdate, bookID) {
    this.bookService.getBook(`/book/${bookID}?expand=authors,categories` )
      .subscribe((book: BookInterface) =>{
        this.book=book;
        console.log(book.categories)
      },(error)=>{
        alert('ocurrio un error');
      });
    this.modalService.open(contentUpdate, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  openPost(contentAdd) {

    this.modalService.open(contentAdd, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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


  getAuthors(){
    this.authorService.getAuthors("/author/list")
      .subscribe(authors=>{ this.authors=authors
        console.log(this.authors=authors);
      })
  }

  getCategories(){
    this.categoryService.getCategories("/category/list")
      .subscribe(categories=>{ this.categories=categories
        console.log(this.categories=categories);
      })
  }

  getOrderedPagination(): void {
    //console.log(this.form.value.limit,this.form.value.offset, this.form.value.order, this.form.value.pages);
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
      authors: ['',[ Validators.required ]] ,
      categories: ['',[ Validators.required ]] ,
      limit:['',[ Validators.required ]],
      offset:['',[ Validators.nullValidator]],
      order: ['',[ Validators.nullValidator]],
    })

  }


}
