import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/authentication/authentication.service";
import {TokenStorageService} from "../../../services/authentication/token-storage.service";
import {RegistrationData} from "../../../common/authentication/registration-data";
import {LoginData} from "../../../common/authentication/login-data";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.sass']
})
export class RegisterFormComponent implements OnInit {

  registerData: RegistrationData = new RegistrationData();
  isLoggedIn = false;

  constructor(private router: Router, private authService: AuthService, private tokenStorage: TokenStorageService) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      console.log("Already logged in when in Registration")
      this.isLoggedIn = true;
    }
  }

  register(): void {
    if (this.isDateValid()) {
      this.authService.register(this.registerData).subscribe(
        data => {
          this.router.navigate(['/login-page']).then(r => console.log(r));
        }
      )
    }
    else {
      console.log('OUAOAUAOUAOUAOAUOA')
    }
  }

  isDateValid() {
    const currentDate = new Date();
    const selectedDate = new Date(this.registerData.birthDay);

    return selectedDate <= currentDate;
  }


}
