import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  submitMessage: String = '';
  submitSuccessMessage: String = '';
  submitted: Boolean = false;

  constructor(private authenticationService: AuthenticationService) { }

  RegisterForm = new FormGroup({
    userId: new FormControl('', [Validators.required]),
    userPassword: new FormControl('', [Validators.required, Validators.minLength(4)]),
    userEmail: new FormControl('', [Validators.required])
  });

  Submit() {
    this.submitted = true;
    console.log("User is Submitted");
    console.log(this.RegisterForm.value);
    this.authenticationService.registerUser(this.RegisterForm.value).subscribe(
      data => {
        this.submitMessage = "User is registered successfully";
      }, err => {
        if (err.status === 401) {
          this.submitMessage = err.error.message;
        }else if (err.status === 403) {
          this.submitMessage = err.error.message;
        } else if (err.status === 404) {
          this.submitMessage = err.message;
        }else if (err.status === 409) {
          this.submitMessage = "User Already Exists.";
        }
      }
    );

  }
  ngOnInit() {
  }

  usernamehaserror() {
    return this.userId.hasError('required') ? true : false;
  }
  passwordhaserror() {
    return this.userPassword.hasError('minlength') ? true : false;
  }
  useremailhaserror() {
    return this.userEmail.hasError('required') ? true : false;
  }
  
  get userId(){
    return this.RegisterForm.get('userId');
  }
  get userPassword(){
    return this.RegisterForm.get('userPassword');
  }

  set userId(username){
    this.userId.setValue(username);
  }

  set userPassword(password){
    this.userPassword.setValue(password);
  }
  get userEmail(){
    return this.RegisterForm.get('userEmail');
  }

  set userEmail(userEmail){
    this.userId.setValue(userEmail);
  }
}
