import { Component, OnInit } from '@angular/core';
import { GipherService } from '../service/gipher.service';
import { Gipher } from '../model/gipher.model';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-bookmark',
  templateUrl: './bookmark.component.html',
  styleUrls: ['./bookmark.component.css']
})
export class BookmarkComponent implements OnInit {
  giphers: Array<Gipher>;
  constructor(private gipherService: GipherService, private authenticationService: AuthenticationService) { }

  getSantizeUrl(url:string) {
    return this.gipherService.getSantizeUrl(url);
  }

  ngOnInit() {
    this.gipherService.fetchBookmarkedGipher(this.authenticationService.getUserId()).subscribe(
      data => {
        this.giphers = data;
      }, err => {
        console.log(err);
      });
  }
}
