import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BooksService } from '../books.service';


@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {

  authors = new FormControl();
  authorsList: string[] = ['Horror', 'Thriller', 'Psycologic'];
  categories = new FormControl();
  categoriesList: string[] = ['Horror', 'Thriller', 'Psycologic'];

  public form: FormGroup = new FormGroup({
    title: new FormControl('', Validators.required),
    pages: new FormControl('', [Validators.required, Validators.min(50), Validators.max(9999)]),
    isbn: new FormControl(''),
    price: new FormControl('', Validators.required),
    editorial: new FormControl('', Validators.required),
    releaseDate: new FormControl(''),
    author: new FormControl('', Validators.required),
    categories: new FormControl('', Validators.required)
  });

  constructor(private service: BooksService) { }

  ngOnInit(): void {}
  
  onClear() {
    this.form.reset();
  }

}
