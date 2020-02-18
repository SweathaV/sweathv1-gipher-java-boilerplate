import { Component, OnInit } from '@angular/core';
import { Gipher } from '../model/gipher.model';
import { GipherService } from '../service/gipher.service';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {

  giphers: Array<Gipher>;
  constructor(private gipherService: GipherService, private authenticationService: AuthenticationService) { }

  getSantizeUrl(url:string) {
    return this.gipherService.getSantizeUrl(url);
  }

  ngOnInit() {
    this.gipherService.fetchFavouriteGipher(this.authenticationService.getUserId()).subscribe(
      data => {
        this.giphers = data;
      }, err => {
        console.log(err);
      });
  }
}
