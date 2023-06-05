import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../../services/authentication/token-storage.service";
import {AccountInfoService} from "../../../services/customer/account-info.service";
import {AccountInfo} from "../../../common/users/account-info";
import {FormControl, FormGroup, Validators} from "@angular/forms";



@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.sass']
})
export class PersonalInfoComponent implements OnInit {

  customerForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
  });

  accountInfo: AccountInfo = new AccountInfo();

  constructor(private tokenStorageService: TokenStorageService, private accountInfoService: AccountInfoService) { }

  ngOnInit(): void {
    this.retrieveAccountInfo(this.tokenStorageService.getUser().username);
  }

  retrieveAccountInfo(username: string) {
    this.accountInfoService.retrieveAccountInfo(username).subscribe(
      data => {
        this.accountInfo = data;
      },
      error => {
        console.log(error);
      }
    );
  }

  saveCustomerInfo() {
  }

  get name() {return this.customerForm.get('name'); }
  get lastName() {return this.customerForm.get('lastName'); }

}
