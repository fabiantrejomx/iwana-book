import { Component, OnInit } from '@angular/core';
import { Category } from '../model/category/category';
import { CategoryService } from '../service/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public categories:Category[];
  public category:Category;

  constructor(private categoryService:CategoryService) { }

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
}
