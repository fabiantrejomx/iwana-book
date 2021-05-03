import { Component, OnInit } from '@angular/core';
import {RankingInterface} from "../interfaces/ranking-interface";
import {HttpRequestRankingService} from "../service/http-request-ranking.service";

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.sass']
})
export class RankingComponent implements OnInit {

  booksWithRanking: RankingInterface[];

  constructor( private rankingService: HttpRequestRankingService) { }

  getListBooksWithRanking(){
    this.rankingService.getBookWithRanking('/book/ranking?limit=3&offset=0')
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
