import { Component, OnInit } from '@angular/core';
import {RankingInterface} from "../interfaces/RankingInterface";
import {HttpRequestBookService} from "../service/http-request-book.service";
import {BookInterface} from "../interfaces/BookInterface";

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.sass']
})
export class RankingComponent implements OnInit {

  booksWithRanking: RankingInterface[];

  constructor(
    private bookService: HttpRequestBookService
  ) { }

  getListBooksWithRanking(){
    this.bookService.getBookWithRanking('/book/ranking?limit=3&offset=0')
      .subscribe((booksWithRanking: RankingInterface[]) =>{
        this.booksWithRanking=booksWithRanking;
        console.log(this.booksWithRanking=booksWithRanking);
      },(error)=>{
        alert('Houston, we have a problem');
      });
  }

  ngOnInit(): void {
    this.getListBooksWithRanking()
  }

}
