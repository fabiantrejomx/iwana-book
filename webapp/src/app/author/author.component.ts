import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthorInterface} from "../interfaces/author-interface";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {HttpRequestAuthorService} from "../service/http-request-author.service";

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.sass']
})

export class AuthorComponent implements OnInit {
  pageActual: number=1;
  authors: AuthorInterface[];
  closeResult = '';
  form: FormGroup;
  author: AuthorInterface;

  constructor(private authorService: HttpRequestAuthorService, private modalService: NgbModal, private  formBuilder: FormBuilder) { }

  getListAuthors(){
    this.authorService.getAuthor('/author/list')
      .subscribe( authors=>{ this.authors=authors
        console.log(this.authors=authors)
      },(error)=>{
        alert('ocurrio un error');
      });
  }

  deleteAuthor(authorID: number){
    this.authorService.deleteAuthor(`/author/${authorID}`)
      .subscribe((authors: AuthorInterface[]) =>{ this.getListAuthors();
      },(error)=>{
        alert('ocurrio un error');
      });
  }

  public putAuthor(authorID: number){
    this.authorService.putAuthor(`/author/${authorID}`,
      {name:this.form.value.name,
        firstName: this.form.value.firstName,
        secondName:this.form.value.secondName,
        birthday: this.form.value.birthday
      })
      .subscribe(request => {
      console.log('Success!',this.form.value);
      this.form.reset();
      this.getListAuthors();
    })
  }

  public postAuthor(){
    this.authorService.postAuthor(`/author`,
      {name:this.form.value.name,
        firstName: this.form.value.firstName,
        secondName:this.form.value.secondName,
        birthday: this.form.value.birthday
      })
      .subscribe(request => {
      console.log('Success!',this.form.value);
      this.form.reset();
      this.getListAuthors();
    })
  }

  openUpdate(contentUpdate, authorID) {
    this.authorService.getAuthor(`/author/${authorID}` )
      .subscribe((author: AuthorInterface) =>{
        this.author=author;
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
    this.getListAuthors()
    this.form = this.formBuilder.group({
      name: ['',[ Validators.required ]]  ,
      firstName: ['',[ Validators.required ]] ,
      secondName: ['',[ Validators.required ]] ,
      birthday: ['', [Validators.required, Validators.pattern(/^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/)]],
    })

  }

}
