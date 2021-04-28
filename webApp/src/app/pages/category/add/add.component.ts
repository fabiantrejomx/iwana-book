import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../category.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {

  public form: FormGroup = new FormGroup({
    category: new FormControl('', Validators.required)
  });

  constructor(private service: CategoryService) { }

  ngOnInit(): void {
  }

  onClear() {
    this.form.reset();
  }

}
