import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
export const USER_NAME = "username";
export const TOKEN_NAME = "jwt_token";

@Injectable()
export class AuthenticationService {
  private hostname: string;
  private SpringRegisterEndPoint: string;
  private SpringLoginEndPoint: string;
  private SpringAuthEndPoint:string;
  isLoggedIn: Boolean = false;
  isLoggedInSubject: BehaviorSubject<Boolean>;
  
  constructor(private httpclient: HttpClient) {
    this.isLoggedInSubject = new BehaviorSubject(this.isLoggedIn);

    this.hostname = location.host;
    console.log("Location.host:", this.hostname);

    if (this.hostname.indexOf(':') > 0) {
      this.hostname = this.hostname.substr(0, this.hostname.indexOf(':'));
    }
    console.log("hostname:", this.hostname);      
    this.SpringRegisterEndPoint="http://" + this.hostname + ":9084/api/v1/auth/register"
    this.SpringLoginEndPoint="http://" + this.hostname + ":9084/api/v1/auth/login";
    this.SpringAuthEndPoint = "http://" + this.hostname + ":9084/api/v1/auth/authenticate";
    console.log("SpringRegisterEndPoint:", this.SpringRegisterEndPoint); 
    console.log("SpringLoginEndPoint:", this.SpringLoginEndPoint);    
    console.log("SpringAuthEndPoint:", this.SpringAuthEndPoint);      
  }

  registerUser(data) {
    
    const url = this.SpringRegisterEndPoint;
    return this.httpclient.post(url, data);
    //return this.httpclient.post('http://localhost:9084/api/v1/auth/register', data);
  }

  authenticateUser(data) {
    console.log("Printing Data in authenticateUser()", data);
    const url = this.SpringLoginEndPoint;
    console.log("Printing Data in authenticateUser()", data.userId);
    //sessionStorage.setItem(USER_NAME, data.userId);
    return this.httpclient.post(url, data);
 //   return this.httpclient.post('http://localhost:9084/api/v1/auth/login', data);
  }

  setBearerToken(token) {
    localStorage.setItem('bearerToken', token);
  }

  getBearerToken() {
    return localStorage.getItem('bearerToken');
  }

  setUserId(userId) {
    localStorage.setItem('userId', userId);
  }

  getUserId() {
    return localStorage.getItem('userId');
  }

  isUserAuthenticated(token): Promise<boolean> {
    //http://localhost:9084/api/v1/auth/authenticate
    console.log('Received token in authService', token);
    const url = this.SpringAuthEndPoint;
    if (null != token) {
      const obs: Observable<any> = this.httpclient.post(url, {}, {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
      });
      return obs.pipe(map(response => {
        this.isLoggedIn = response['isAuthenticated'];
        this.isLoggedInSubject.next(this.isLoggedIn);
        return response['isAuthenticated'];
      })).toPromise();
    }
    else{
      return Promise.resolve(false);
    }
  }

  logout() {
    localStorage.removeItem('bearerToken');
    localStorage.removeItem('userId');
    localStorage.removeItem('query');
    this.isLoggedInSubject.next(false);
  }

  isUserLoggedIn() {
    return this.isLoggedInSubject;
  }

}
