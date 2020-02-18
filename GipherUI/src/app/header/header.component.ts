import { Component } from '@angular/core';
import { RouterService } from '../service/router.service';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  isloggedIn :Boolean;
  isNoteView: Boolean;
  constructor(private routerService: RouterService, private authService: AuthenticationService) {
    this.isNoteView = true;
  }

  logout(){
    this.authService.logout();
    
    this.routerService.routeToLogin();
   
  }
  isUserLoggedIn(){
     this.authService.isUserLoggedIn().subscribe(flag =>{
         this.isloggedIn = flag;
     });
    return this.isloggedIn;
  }

}
