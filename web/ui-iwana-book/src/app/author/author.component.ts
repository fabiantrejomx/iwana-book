import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthorInterface} from "../interfaces/AuthorInterface";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {HttpRequestAuthorService} from "../service/http-request-author.service";

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.sass']
})
export class AuthorComponent implements OnInit {

  closeResult = '';
  pageActual: number=1;
  authors: AuthorInterface[];
  author: AuthorInterface;
  public form: FormGroup;

  constructor(
    private authorService: HttpRequestAuthorService,
    private modalService: NgbModal,
    private  formBuilder: FormBuilder) { }

  getListAuthors(){
    this.authorService.getAuthors('/author/list')
      .subscribe((authors: AuthorInterface[]) =>{ this.authors=authors; console.log(this.authors=authors);
      },(error)=>{
        alert('Houston, we have a problem');
      });
  }

  deleteAuthor(authorId: number){
    console.log(authorId);
    this.authorService.deleteAuthor(`/author/${authorId}` )
      .subscribe((authors: AuthorInterface[]) =>{ this.getListAuthors();
      },(error)=>{
        alert('Houston, we have a problem');
      });
  }

  public postAuthor(){
    this.authorService.postAuthor(`/author`,
      {
        name:this.form.value.name,
        firstName:this.form.value.firstName,
        secondName:this.form.value.secondName,
        birthday:this.form.value.birthday
      }).subscribe(request => {
      console.log('Success post!',this.form.value);
      this.form.reset();
      this.getListAuthors();
    })
  }

  public putAuthor(authorID: number){
    this.authorService.putAuthor(`/author/${authorID}`,
      {name:this.form.value.name,
        firstName: this.form.value.firstName,
        secondName:this.form.value.secondName,
        birthday: this.form.value.birthday
      })
      .subscribe(request => {
        console.log('Success put!',this.form.value);
        this.form.reset();
        this.getListAuthors();
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

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  /*MODAL UPDATE*/
  openUpdate(contentUpdate, authorID) {
    this.authorService.getAuthor(`/author/${authorID}` )
      .subscribe((author: AuthorInterface) =>{
        this.author=author;
      },(error)=>{
        alert('Houston, we have a problem');
      });
    this.modalService.open(contentUpdate, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  ngOnInit(): void {
    this.getListAuthors()
    this.form = this.formBuilder.group({
      name: ['',[ Validators.required ]],
      firstName: ['',[ Validators.required ]],
      secondName: ['',[ Validators.required ]],
      birthday: ['', [Validators.required, Validators.pattern(/^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/)]],
    });
  }

}
