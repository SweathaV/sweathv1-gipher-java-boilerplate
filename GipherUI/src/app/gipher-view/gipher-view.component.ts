import { Component, OnInit, Input } from '@angular/core';
import { GipherService} from '../service/gipher.service';
import { Gipher } from '../model/gipher.model';

@Component({
  selector: 'app-gipher-view',
  templateUrl: './gipher-view.component.html',
  styleUrls: ['./gipher-view.component.css']
})
export class GipherViewComponent implements OnInit {
  @Input() public searchedGiphers: Array<Gipher>;
  
  constructor(private gipherService: GipherService) { }

  getSantizeUrl(url:string){
    return this.gipherService.getSantizeUrl(url);
  }

  bookmarkGipher(gipher:Gipher){
    gipher.bookMarkedBy = gipher.userId; 
    this.gipherService.updateGipher(gipher).subscribe(data => {
      this.updateExistingGiphers(data);
    },err =>{
      console.log("bookmarkGipher: "+err);
    });
  }

  favouriteGipher(gipher:Gipher){
    gipher.favouritedBy = gipher.userId;
    this.gipherService.updateGipher(gipher).subscribe(data =>{
      this.updateExistingGiphers(data);
    },err=>{
      console.log("favouriteGipher: "+err);
    });
  }

  updateExistingGiphers(gipher:Gipher){
    for(var i=0;i<this.searchedGiphers.length;i++){
      if(gipher.gipherId == this.searchedGiphers[i].gipherId){
        this.searchedGiphers[i]=gipher;
        break;
      }
    }
  }

  ngOnInit() {
  }

}
