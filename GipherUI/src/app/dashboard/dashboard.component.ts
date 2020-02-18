import { Component, OnInit, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Gipher } from '../model/gipher.model';
import { GipherService } from '../service/gipher.service';
import { RouterService } from '../service/router.service';
import { Validators } from '@angular/forms';
import {GipherViewComponent} from '../gipher-view/gipher-view.component'

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {  
  searchedGiphers: Array<Gipher>;
  value : string
  submitMessage: String = '';
  submitted : Boolean = false;
  constructor(private gipherService: GipherService, private routerService: RouterService) {}

  searchForm = new FormGroup ({
    query : new FormControl('', [Validators.required])
  });
  
  
  searchSubmit() {
    this.submitted = true;
    this.gipherService.fetchGiphers(this.searchForm.value.query).subscribe(
      data => {
      this.searchedGiphers=data;
    }, err => {
          console.log(err);
          if (err.status === 403) {
            this.submitMessage = err.error.message;
          } else if (err.status === 404) {
            this.submitMessage = err.message;
          }else if(err.status === 401){
            this.submitMessage = "Nothing to display";
          }
        });
  }

  searchhashaserror() {
    return this.query.hasError('required') ? true : false;
  }
  
  get query() {
    return this.searchForm.get('query');
  }

  set query(query) {
    this.query.setValue(query);
  }

  get lf() {
    return this.searchForm.controls;
  }
  
  ngOnInit() {
    this.value = "";
  }

}
