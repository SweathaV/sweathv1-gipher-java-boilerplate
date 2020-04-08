import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

const testdata={
  userId: "test",
  userEmail: "test@test.com",
  userPassword: "test123"
};

describe('AuthenticationService', () => {

  let authService: AuthenticationService;
  let httpTestingController: HttpTestingController;
  const testForRegister = "http://localhost:9084/api/v1/auth/register";
  const testForLogin = "http://localhost:9084/api/v1/auth/login";


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthenticationService]
    });
    authService = TestBed.get(AuthenticationService);
    httpTestingController = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(authService).toBeTruthy();
  });

  it('#registerUser() should fetch proper response from Http call', () => {
    authService.registerUser(testdata).subscribe(res =>{
      console.log(res);
      expect(res).toBe(testdata);
    });
    const httpMockReq = httpTestingController.expectOne(testForRegister);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(testForRegister);

  });

  it('#authenticateUser() should fetch proper response from Http call', () => {
    authService.authenticateUser(testdata).subscribe(res =>{
      console.log(res);
      expect(res).toBe(testdata);
    });
    const httpMockReq = httpTestingController.expectOne(testForLogin);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(testForLogin);

  });
});
