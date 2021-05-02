import { Component, OnInit } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Author } from '../model/author/author';
import { AuthorService } from '../service/author.service';
import { AuthorFormComponent } from './author-form/author-form.component';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  public authors:Author[];
  public author:Author;
  private bsModalRef: BsModalRef;

  constructor(private authorService:AuthorService, private modalService:BsModalService) { }

  ngOnInit(): void {
    this.getAuthors();
  }

  public getAuthors() {
    this.authorService.getAuthors()
      .subscribe((response) => {
        this.authors = response;
      })
  }

  public deleteAuthor(id: number) {
    this.authorService.deleteAuthor(id).subscribe(
      (response) => {
        this.author = response;
        this.getAuthors();
      }
    )
  }


  public openCreateModal(): void {
    this.bsModalRef = this.modalService.show(AuthorFormComponent, {
      class: 'modal-lg',
    });
    this.bsModalRef.content.title = 'Crear Autor';
    this.bsModalRef.content.onClose = () => {
      this.bsModalRef.hide();
      this.getAuthors();
    };
  }

  public updateAuthor(author:Author){
    console.log(author);
    console.log(author.id);
    const bsModalRef = this.modalService.show(AuthorFormComponent);
    bsModalRef.content.title =
      "Editar Autor" 
    bsModalRef.content.authorForm=author;
    bsModalRef.content.id=author.id;
    bsModalRef.content.onClose = (success) => {
      if (success) {
        bsModalRef.hide();
      }
      this.getAuthors();
  }}

}
