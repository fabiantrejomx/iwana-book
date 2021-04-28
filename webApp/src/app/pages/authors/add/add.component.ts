import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthorsRequest } from '../authors.request';
import { AuthorsService } from '../authors.service';
import { HttpErrorResponse } from "@angular/common/http";


@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {
    minDate: Date;
    maxDate: Date;

  public form: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    firstSurname: new FormControl('', Validators.required),
    secondSurname: new FormControl(''),
    birthdate: new FormControl('', Validators.required)
  })

  constructor(private authorsService: AuthorsService) {

    const currentDate = new Date().getFullYear();
    this.minDate = new Date(currentDate - 572, 0, 1);
    this.maxDate = new Date(currentDate + 1, 0, 0);
   }

  ngOnInit(): void {
  }

  public createAuthors(): void {
    this.authorsService.createAuthors({
      name: this.form.get("name").value,
      firstSurname: this.form.get("firstSurname").value,
      secondSurname: this.form.get("secondSurname").value,
      birthdate: this.form.get("birthdate").value
    })
    .subscribe(response => {
      alert(response ? "Author Created" : "Author creation failed")
    })
  }


  handleError(arg0: (response: boolean) => void, handleError: any) {
    throw new Error('Method not implemented.');
  }

  onClear() {
    this.form.reset();
  }
}
