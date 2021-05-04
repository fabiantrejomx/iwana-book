import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { Authors } from 'src/app/model/author/author';
import { BookForm } from 'src/app/model/book/book.form';
import { Categories } from 'src/app/model/category/category';
import { AuthorService } from 'src/app/service/author.service';
import { BookService } from 'src/app/service/book.service';
import { CategoryService } from 'src/app/service/category.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { Book } from 'src/app/model/book/book';


@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {

  public book: BookForm;
  public books:Book;
  public title: string;
  public errores: string[];
  public categories: Categories[];
  public authors:Authors[];
  public selectedAuthors:Authors[];
  public selectedCategories:Categories[];
  public form: FormGroup;
  public onClose:Function;
  public isSaving:boolean;
  public isUpdate:boolean;
  public authorsID=[];
  public categoriesID=[];
  dropdownSettings: any = {};


  constructor(private bookService:BookService,private router: Router, 
    private activatedRouter: ActivatedRoute,
    public bsModalRef: BsModalRef,

    private categoryService:CategoryService,
    private authorService:AuthorService,
    private formBuilder:FormBuilder) { 
      this.form = this.formBuilder.group({
        title: [, [Validators.required]],
        pages:[],
        isbn:[,Validators.compose([Validators.required,Validators.minLength(13)])],
        price:[,Validators.compose([Validators.required,Validators.min(0),Validators.max(999999)])],
        summary:[,Validators.required],
        editorial:[,Validators.required],
        datePublication:[,Validators.required],
        authors:[this.selectedAuthors],
        categories:[this.selectedCategories]
      });
    }

  ngOnInit(): void {
    this.getAuthors();
    this.getCategories();
    this.dropdownSettings = {
      singleSelection: false,
      idField: "id",
      textField: "name",
      selectAllText: "Select All",
      unSelectAllText: "UnSelect All",
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
  }

  ngAfterViInit(){
   setTimeout(()=>{

   })
  }
  public save(){
    if(this.isSaving){
      this.create();
    }else{
      this.update();
    }
     
    
    
  }

  create(): void {
    this.authorsID=this.form.value.authors.map((author) => author.id);
    this.categoriesID=this.form.value.categories.map((category) => category.id);
    this.isSaving=true;
    this.bookService.create(
      { title:this.form.value.title,
        pages:this.form.value.pages,
        isbn:this.form.value.isbn,
        price:this.form.value.price,
        summary:this.form.value.summary,
        editorial:this.form.value.editorial,
        datePublication:this.form.value.datePublication,
        authors:this.authorsID,
        categories:this.categoriesID
      }
      ).subscribe(()=> {
      this.isSaving=false;
        this.onClose(true);
            console.log(
              "ha sido registrado."

            );},
      err => {
        this.errores = err.error.errors as string[];
        console.error('Error' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  public onSelect() {
    this.getAuthors();
  }

  public onDeSelect() {
    this.getAuthors();
  }

  public onSelectAll(items: Authors[]) {
    console.log(items)
    this.selectedAuthors = items;
    this.book = {
      authors: this.selectedAuthors
    };
    this.getAuthors();
  }

  public onSelectCategories() {
    this.getCategories();
  }

  public onDeSelectCategories() {
    this.getCategories();
  }

  public onSelectAllCategories(items: Categories[]) {
    this.selectedCategories = items;
    this.book = {
      categories: this.selectedCategories
    };
    this.getCategories();
  }

 public update(): void{
    this.authorsID=this.form.value.authors.map((author) => author.id);
    this.categoriesID=this.form.value.categories.map((category) => category.id);
    this.isUpdate=true;
    this.bookService.update(
      { title:this.form.value.title,
        pages:this.form.value.pages,
        isbn:this.form.value.isbn,
        price:this.form.value.price,
        summary:this.form.value.summary,
        editorial:this.form.value.editorial,
        datePublication:this.form.value.datePublication,
        authors:this.authorsID,
        categories:this.categoriesID
      },
      this.books.id
      ).subscribe(()=> {
      this.isUpdate=false;
        this.onClose(true);
            console.log(
              "ha sido actualizado."

            );},
      err => {
        this.errores = err.error.errors as string[];
        console.error('Error' + err.status);
        console.error(err.error.errors);
      }
    );   
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
