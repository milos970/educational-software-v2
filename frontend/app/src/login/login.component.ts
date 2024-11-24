import { Component, OnInit  } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HttpClient} from "@angular/common/http";
import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'login-component',
  standalone: true,
  imports: [RouterOutlet, FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrls: ['../assets/static/vendors/mdi/css/materialdesignicons.min.css',
    '../assets/static/vendors/css/vendor.bundle.base.css',
    '../assets/static/css/style.css', '../assets/static/css/my.css']
})

export class LoginComponent implements OnInit
{

  ngOnInit() {
  /*  this.loadScript('../assets/static/js/off-canvas.js');
    this.loadScript('../assets/static/js/hoverable-collapse.js');
    this.loadScript('../assets/static/js/misc.js');
    this.loadScript('../assets/static/js/settings.js');
    this.loadScript('../assets/static/js/todolist.js');*/
  }

  private loadScript(src: string) {
    const script = document.createElement('script');
    script.src = src;
    script.async = true;
    document.body.appendChild(script);
  }

   credentials = {
    username: '',
    password: ''
  };

  constructor(private http: HttpClient) {

  }

  private postRequest(url: string) {
    this.http.post(url, this.credentials, {responseType: 'json'}).subscribe(buffer => {
      alert(buffer);
    });}

  onSubmit() {
    const url = "http://localhost:8080/password";
    this.postRequest(url);
    }

}
