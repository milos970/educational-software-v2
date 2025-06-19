import { Component } from '@angular/core';
import {FormGroup, FormControl, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

profileForm = new FormGroup
({
    username: new FormControl(''),
    password: new FormControl(''),
});


onSubmit() {
    console.warn(this.profileForm.value);
  }

}
