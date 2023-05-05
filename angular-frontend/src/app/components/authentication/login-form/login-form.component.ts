import { Component, OnInit } from '@angular/core';
import {AccountInfo} from "../../../common/users/account-info";
import {AuthService} from "../../../services/authentication/authentication.service";
import {Router} from "@angular/router";
import {LoginData} from "../../../common/authentication/login-data";
import {TokenStorageService} from "../../../services/authentication/token-storage.service";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.sass']
})
export class LoginFormComponent implements OnInit {

  loginData: LoginData = new LoginData();
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string[] = [];

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  logIn(): void {
    this.authService.login(this.loginData).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      error => {
        console.log(error.error.message);
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
