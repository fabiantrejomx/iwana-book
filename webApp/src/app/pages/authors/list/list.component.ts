import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthorsResponse } from '../authors.response';
import { AuthorsService } from '../authors.service';
import { AuthorDTO } from "../author.dto";
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddComponent } from '../../authors/add/add.component';

export interface DialogData {
  authorId: number;
}



@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  authors: AuthorDTO[];
  displayedColumns: string[] = ['id','name', 'firstName', 'secondName', 'birthday', 'actions'];
  authorId: number;


  constructor(private authorsService: AuthorsService,
    private dialog: MatDialog) { }


  ngOnInit(): void {
    this.getList()
  }

  
  public getList(): void {
    this.authorsService.getAuthors().subscribe((data: AuthorsResponse) =>{
      console.log(data);
      this.authors = data.authors;
    })
  } 

  public onCreate() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(AddComponent,dialogConfig);
  }

  public onEdit(row) {
    this.authorId = row;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    dialogConfig.data = {authorId: this.authorId}
    this.dialog.open(AddComponent,dialogConfig);
  }

  public onDelete(row) {
    this.authorId = row;
    this.authorsService.deleteAuthors(this.authorId).subscribe(response => {
      alert(response ? "Author Deleted" : "Author deleted failed")
    });
  }


  



}

