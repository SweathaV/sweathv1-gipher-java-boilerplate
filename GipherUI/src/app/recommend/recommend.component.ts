import { Component, OnInit } from '@angular/core';
import { Gipher } from '../model/gipher.model';
import { GipherService } from '../service/gipher.service';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-recommend',
  templateUrl: './recommend.component.html',
  styleUrls: ['./recommend.component.css']
})
export class RecommendComponent implements OnInit {

  giphers: Array<Gipher>;
  constructor(private gipherService: GipherService, private authenticationService: AuthenticationService) { }

  getSantizeUrl(url:string) {
    return this.gipherService.getSantizeUrl(url);
  }

  ngOnInit() {
    console.log('inside recommend component')
    this.gipherService.fetchRecommendedGipher(this.authenticationService.getUserId()).subscribe(
      data => {
        this.giphers = data;
      }, err => {
        console.log(err);
      });
  }

}


