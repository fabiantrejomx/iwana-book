import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { Author, Authors } from 'src/app/model/author/author';
import { Book } from 'src/app/model/book/book';
import { BookForm } from 'src/app/model/book/book.form';
import { Categories, Category } from 'src/app/model/category/category';
import { AuthorService } from 'src/app/service/author.service';
import { BookService } from 'src/app/service/book.service';
import { CategoryService } from 'src/app/service/category.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';


@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {

  public book: BookForm;
  public title: string;
  public errores: string[];
  public categories: Categories[];
  public authors:Authors[]=[];
  public selectedAuthors:Author[];
  public selectedCategories:Category[];
  public form: FormGroup;
  public onClose:Function;
  public isSaving:boolean;
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

  public save(){
    if(this.isSaving){
      this.create();
    }else{
      this.update();
    }
    
  }

  create(): void {
    this.isSaving=true;
    this.bookService.create(this.form.value).subscribe(()=> {
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

  public onSelectAllCategories(items: Authors[]) {
    this.selectedCategories = items;
    this.book = {
      categories: this.selectedCategories
    };
    this.getCategories();
  }

  update(): void{
    
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
