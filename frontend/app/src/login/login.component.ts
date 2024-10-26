import { Component, OnInit  } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'login-component',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './login.component.html',
  styleUrls: ['../assets/static/vendors/mdi/css/materialdesignicons.min.css',
    '../assets/static/vendors/css/vendor.bundle.base.css',
    '../assets/static/css/style.css', '../assets/static/css/my.css']
})

export class LoginComponent implements OnInit {

  ngOnInit() {
    this.loadScript('../assets/static/js/off-canvas.js');
    this.loadScript('../assets/static/js/hoverable-collapse.js');
    this.loadScript('../assets/static/js/misc.js');
    this.loadScript('../assets/static/js/settings.js');
    this.loadScript('../assets/static/js/todolist.js');
  }

  private loadScript(src: string) {
    const script = document.createElement('script');
    script.src = src;
    script.async = true;
    document.body.appendChild(script);
  }
}
