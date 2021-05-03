import { Component, OnInit } from '@angular/core';
import {CategoryInterface} from "../interfaces/CategoryInterface";
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpRequestCategoryService} from "../service/http-request-category.service";


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.sass']
})
export class CategoryComponent implements OnInit {

  closeResult = '';
  pageActual: number=1;
  categories: CategoryInterface[];
  category: CategoryInterface;
  public form: FormGroup;

  constructor(private categoryService: HttpRequestCategoryService, private modalService: NgbModal, private  formBuilder: FormBuilder) { }


  getListCategories(){
    this.categoryService.getCategory('/category/list')
      .subscribe((categories: CategoryInterface[]) =>{ this.categories=categories; console.log(this.categories=categories);
      },(error)=>{
        alert('Houston, we have a problem');
      });
  }


  deleteCategory(categoryId: number){
    console.log(categoryId);
    this.categoryService.deleteCategory(`/category/${categoryId}` )
      .subscribe((categories: CategoryInterface[]) =>{ this.getListCategories();
      },(error)=>{
        alert('Houston, we have a problem');
      });
  }

  public postCategory(){
    this.categoryService.postCategory(`/category`,
      {name:this.form.value.name}).subscribe(request => {
      console.log('Success post!',this.form.value);
      this.form.reset();
      this.getListCategories();
    })
  }

  public putCategory(categoryId : number){
    this.categoryService.putCategory(`/category/${categoryId}`,
      {name:this.form.value.name}).subscribe(request => {
      console.log('Success put!',this.form.value);
      this.form.reset();
      this.getListCategories();
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

/* MODAL FOR UPDATE*/
  openModalUpdate(contentUpdate, categoryId) {
    console.log("cat", categoryId);
    this.categoryService.getCategoryById(`/category/${categoryId}` )
      .subscribe((category: CategoryInterface) =>{
        this.category=category;
        console.log("f", category)
      },(error)=>{
        alert('ocurrio un error');
      });

    this.modalService.open(contentUpdate, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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

  ngOnInit(): void {
    this.getListCategories()
    this.form = this.formBuilder.group({    name: ['',[ Validators.required ]]  });
  }

}
