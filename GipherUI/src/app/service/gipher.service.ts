import { Injectable } from '@angular/core';
import { Gipher } from '../model/gipher.model';
import { AuthenticationService } from './authentication.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Injectable()
export class GipherService {
    
    giphers: Array<Gipher>;
    gipherSubject: BehaviorSubject<Array<Gipher>>;
    springEndPoint: string;
    recommendEndPoint: string;
    externalApiEndPoint: string;
    username: string;
    byPassCorsUrl: string;
    hostname: string;
    
    constructor(private authService: AuthenticationService, private httpClient: HttpClient, private sanitizer: DomSanitizer) {
        this.giphers = [];
        this.gipherSubject = new BehaviorSubject(this.giphers);
        this.hostname = location.host;

       console.log("Location.host:", this.hostname);

    if (this.hostname.indexOf(':') > 0) {
      this.hostname = this.hostname.substr(0, this.hostname.indexOf(':'));
    }
    console.log("hostname:", this.hostname);    

    this.springEndPoint = "http://" + this.hostname + ":9085/api/v1/gipher/";
    this.recommendEndPoint = "http://" + this.hostname + ":9086/api/v1/gipherrecommendersystem/";
    this.externalApiEndPoint = "http://" + this.hostname + ":9085/api/v1/gipher/externalapi/";
    console.log("springEndPoint:", this.springEndPoint); 
    console.log("recommendEndPoint:", this.recommendEndPoint);    
    console.log("externalApiEndPoint:", this.externalApiEndPoint);    

    }
    
    fetchGiphers(query:string) {
        const token = this.authService.getBearerToken();
        const headerValue = 'Bearer ' + token;
        const url = this.externalApiEndPoint + `${this.authService.getUserId()}/ ${query}` ;
        console.log("fetchGiphers:", url);  
        return this.httpClient.get<Array<Gipher>>(url, {
            headers: new HttpHeaders().set('Authorization', headerValue)
        })
    }

    updateGipher(gipher:Gipher) {
        const token = this.authService.getBearerToken();
        const headerValue = 'Bearer ' + token;
        const url = this.springEndPoint;
        console.log("updateGipher url:", url); 
        return this.httpClient.put<Gipher>(url+ gipher.gipherId, gipher,{
            headers: new HttpHeaders().set('Authorization', headerValue)
        })
    }

    fetchBookmarkedGipher(userId:string) {
        const token = this.authService.getBearerToken();
        const headerValue = 'Bearer ' + token;
        const url = this.springEndPoint + 'bookmark/'+ `${userId}`;
        console.log("fetchBookmarkedGipher url:", url);
        return this.httpClient.get<Array<Gipher>>(url, {
            headers: new HttpHeaders().set('Authorization', headerValue)
        })
    }

    fetchFavouriteGipher(userId:string) {
        const token = this.authService.getBearerToken();
        const headerValue = 'Bearer ' + token;
        const url = this.springEndPoint + 'favourite/'+ `${userId}`;
        console.log("fetchFavouriteGipher url:", url);
        return this.httpClient.get<Array<Gipher>>(url, {
            headers: new HttpHeaders().set('Authorization', headerValue)
        })
    }
        
      fetchRecommendedGipher(userId:string) {
        const token = this.authService.getBearerToken();
        const headerValue = 'Bearer ' + token;
        const url = this.recommendEndPoint + 'recommend/'+ `${userId}`;
        console.log("fetchRecommendedGipher url:", url);
        return this.httpClient.get<Array<Gipher>>(url, {
            headers: new HttpHeaders().set('Authorization', headerValue)
        })
    }

    getSantizeUrl(url:string){
        return this.sanitizer.bypassSecurityTrustResourceUrl(url);
      }
}
