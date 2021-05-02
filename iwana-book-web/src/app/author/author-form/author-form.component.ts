import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { Author } from 'src/app/model/author/author';
import { AuthorForm } from 'src/app/model/author/author.form';
import { AuthorService } from 'src/app/service/author.service';

@Component({
  selector: 'app-author-form',
  templateUrl: './author-form.component.html',
  styleUrls: ['./author-form.component.css']
})
export class AuthorFormComponent implements OnInit {

  public title:string;
  public form: FormGroup;
  public authorForm:AuthorForm;
  public onClose: Function;
  public author:Author;
  public isSaving:boolean;
  public id:number;
  
  constructor(
    public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private authorService:AuthorService
  ) { 
    this.form = this.formBuilder.group({
      name: [, [Validators.required]],
      firstName:[,Validators.compose([Validators.required,Validators.maxLength(255)])],
      secondName:[,Validators.compose([Validators.required,Validators.maxLength(255)])],
      birthday:[]
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
    this.authorService
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
    this.authorService
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
