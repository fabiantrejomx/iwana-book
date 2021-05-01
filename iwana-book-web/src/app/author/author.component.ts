import { Component, OnInit } from '@angular/core';
import { Author } from '../model/author/author';
import { AuthorService } from '../service/author.service';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  public authors:Author[];
  public author:Author;
  constructor(private authorService:AuthorService) { }

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

}
