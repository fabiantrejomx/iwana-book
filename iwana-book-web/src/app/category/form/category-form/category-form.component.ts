import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { CategoryForm } from 'src/app/model/category/category.form';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.css']
})
export class CategoryFormComponent implements OnInit {
  public title:string;
  public form: FormGroup;
  public category:CategoryForm;
  public onClose: Function;
  public id:number;
  public isSaving:boolean;
  
  constructor(
    public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private categoryService:CategoryService
  ) { 
    this.form = this.formBuilder.group({
      name: [, [Validators.required]]
    });
  }

  ngOnInit(): void {
  }

  public save():void{
    if(this.isSaving){
      this.create();
    }else{
      console.log(this.id)
      this.update();
    }
  }

  public create(): void {
    this.isSaving=true;
    this.categoryService
      .create(this.form.value)
      .subscribe( () => {
        this.isSaving=false;
        this.onClose(true);
            console.log(
              "ha sido registrado."

            );
      },
      err => {
        console.error('codigo error backend ' + err.status);
        console.error(err.error.errors);
      }
    );
  }

  public update():void{
    this.isSaving=false;
    this.categoryService
    .update(
     this.form.value,
     this.id
    )
    .subscribe(
      () => {
        this.onClose(true);
            console.log(
              "ha sido actualizado."

            );
      },
      err => {
        console.error('codigo error backend ' + err.status);
        console.error(err.error.errors);
      }
    );
  }

}
