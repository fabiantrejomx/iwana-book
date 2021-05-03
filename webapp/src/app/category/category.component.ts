import { Component, OnInit } from '@angular/core';
import {CategoryInterface} from "../interfaces/category-interface";
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpRequestCategoryService} from "../service/http-request-category.service";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.sass']
})
export class CategoryComponent implements OnInit {
  pageActual: number=1;
  categories: CategoryInterface[];
  closeResult = '';
  public form: FormGroup;
  category: CategoryInterface;

  constructor(private categoryService: HttpRequestCategoryService, private modalService: NgbModal, private  formBuilder: FormBuilder) { }

  getListCategories(){
    this.categoryService.getCategories('/category/list')
      .subscribe( categories=>{ this.categories=categories
        console.log(this.categories=categories)
      },(error)=>{
        alert('ocurrio un error');
      });
  }

  deleteCategory(categoryID: number){
    this.categoryService.deleteCategory(`/category/${categoryID}`)
      .subscribe((categories: CategoryInterface[]) =>{ this.getListCategories();
      },(error)=>{
        alert('ocurrio un error');
      });
  }

  public putCategory(categoryID: number){
    this.categoryService.putCategory(`/category/${categoryID}`,
      {name:this.form.value.name}).subscribe(request => {
      console.log('Success!',this.form.value);
      this.form.reset();
      this.getListCategories();
    })
  }

  public postCategory(){
    this.categoryService.postCategory(`/category`,
      {name:this.form.value.name}).subscribe(request => {
    console.log('Success!',this.form.value);
    this.form.reset();
    this.getListCategories();
      })
  }

  openUpdate(contentUpdate, categoryId) {
    console.log("cat", categoryId);
    this.categoryService.getCategory(`/category/${categoryId}` )
      .subscribe((category: CategoryInterface) =>{
        this.category=category;
        console.log("Category", category)
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


  ngOnInit(): void {
    this.getListCategories()
    this.form = this.formBuilder.group({    name: ['',[ Validators.required ]]  });

  }

}
