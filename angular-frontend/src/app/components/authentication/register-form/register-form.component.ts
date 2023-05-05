import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../services/authentication/authentication.service";
import {TokenStorageService} from "../../../services/authentication/token-storage.service";
import {RegistrationData} from "../../../common/authentication/registration-data";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.sass']
})
export class RegisterFormComponent implements OnInit {

  registerData: RegistrationData = new RegistrationData();
  isLoggedIn = false;
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if(this.tokenStorage.getToken()) {
      console.log("Already logged in when in Registration")
      this.isLoggedIn = true;

    }
  }

  register(): void {
    this.authService.register(this.registerData).subscribe(
      data => {
        console.log("Trying to register");
      }
    )
  }

}
