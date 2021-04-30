import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthorsService } from '../authors.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AddModule } from '../../authors/add/add.module';
import { DialogData } from '../list/list.component';


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

  constructor(private authorsService: AuthorsService,
    private dialogRef: MatDialogRef<AddModule>,
    public dialogRefPut: MatDialogRef<AddComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {

    const currentDate = new Date().getFullYear();
    this.minDate = new Date(currentDate - 572, 0, 1);
    this.maxDate = new Date(currentDate + 1, 0, 0);
   }

  ngOnInit(): void {
  }

  public onSubmit(): void {
    if(!this.data) {
      this.authorsService.createAuthors({
        name: this.form.get("name").value,
        firstSurname: this.form.get("firstSurname").value,
        secondSurname: this.form.get("secondSurname").value,
        birthdate: this.form.get("birthdate").value
      })
      .subscribe(response => {
        alert(response ? "Author Created" : "Author creation failed")
      })
      this.form.reset();
      this.onClose();
    }
      else {
        this.authorsService.updateAuthors(this.data.authorId, {
          name: this.form.get("name").value,
          firstSurname: this.form.get("firstSurname").value,
          secondSurname: this.form.get("secondSurname").value,
          birthdate: this.form.get("birthdate").value
        })
        .subscribe(response => {
          alert(response ? "Author Updated" : "Author updated failed")
        })
        this.onClose();
      }
  }

  onClose() {
    this.form.reset();
    this.dialogRef.close();
  }

  onClear() {
    this.form.reset();
  }
}
