import { Component, OnInit } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Category } from '../model/category/category';
import { CategoryService } from '../service/category.service';
import { CategoryFormComponent } from './form/category-form/category-form.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public categories:Category[];
  public category:Category;
  private bsModalRef: BsModalRef;


  constructor(private categoryService:CategoryService,private modalService:BsModalService) { }

  ngOnInit(){
   this.getCategories();
  }

  public getCategories(): void {
    this.categoryService.getCategories()
      .subscribe((response) => {
        this.categories = response;
      });
  }


  public deleteCategory(id: number) {
    this.categoryService.deleteCategory(id)
      .subscribe((response) => {
        this.category = response;
        this.getCategories();
      });
  }

  public openCreateModal(): void {
    this.bsModalRef = this.modalService.show(CategoryFormComponent, {
      class: 'modal-lg',
    });
    this.bsModalRef.content.title = 'Crear Category';
    this.bsModalRef.content.onClose = () => {
      this.bsModalRef.hide();
      this.getCategories();
    };
  }

  public editCategoryModal(id:number): void {
    this.bsModalRef = this.modalService.show(CategoryFormComponent, {
      class: 'modal-lg',
    });
    this.bsModalRef.content.title = 'Editar Category';
    this.bsModalRef.content.id=id;
    this.bsModalRef.content.onClose = () => {
      this.bsModalRef.hide();
      this.getCategories();
    };
  }
}
