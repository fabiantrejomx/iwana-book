import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { BsModalRef } from 'ngx-bootstrap/modal';
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

  public book: Book=new Book();
  public title: string;
  public errores: string[];
  public categories: Category[];
  public authors:Author[];
  public form: FormGroup;
  public onClose:Function;
  public isSaving:boolean;
  public bsModalRef:BsModalRef;

  constructor(private bookService:BookService,private router: Router, 
    private activatedRouter: ActivatedRoute,
    private categoryService:CategoryService,
    private authorService:AuthorService,
    private formBuilder:FormBuilder) { 
      this.form = this.formBuilder.group({
        name: [, [Validators.required]],
        firstName:[,Validators.compose([Validators.required,Validators.maxLength(255)])],
        secondName:[,Validators.compose([Validators.required,Validators.maxLength(255)])],
        birthday:[]
      });
    }

  ngOnInit(): void {
    this.loadBook();
    this.getAuthors();
    this.getCategories();
  }

  public save(){
    if(this.isSaving){
      this.create();
    }else{
      this.update();
    }
    
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
