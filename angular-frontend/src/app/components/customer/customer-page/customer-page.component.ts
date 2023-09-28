import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../../services/authentication/token-storage.service";
import {Router} from "@angular/router";
import {HeaderSharedService} from "../../../services/shared/header-shared.service";

@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.sass']
})
export class CustomerPageComponent implements OnInit {

  isPersonalActive: boolean = true;
  isOrdersActive: boolean = false;

  constructor(private tokenStorageService : TokenStorageService,
              private router: Router,
              private headerService: HeaderSharedService) { }

  ngOnInit(): void {
    this.isPersonalActive = true;
    this.isOrdersActive = false
  }

  togglePersonal() {
    this.isOrdersActive = false;
    this.isPersonalActive = true;
  }

  toggleOrders() {
    this.isPersonalActive = false;
    this.isOrdersActive = true;
  }

  logout() {
    this.tokenStorageService.signOut();
    this.router.navigate(['/home']).then(r => console.log(r));
    // window.location.reload();
    this.headerService.reloadHeader();

  }

}
