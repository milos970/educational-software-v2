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
    const username = this.profileForm.get('username')?.value;
      const password = this.profileForm.get('password')?.value;

      console.log('Meno:', username);
      console.log('Heslo:', password);
  }

}
