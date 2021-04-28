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

  constructor(private categoryService:CategoryService) { }

  ngOnInit(){
   this.categoryService.getCategories()
   .subscribe((response) => {
    this.categories = response;
  });
  }


  tableColumns  :  string[] = ['id','name'];

}
