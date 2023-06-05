import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AccountInfo} from "../../common/users/account-info";

@Injectable({
  providedIn: 'root'
})
export class AccountInfoService {

  private baseUrl = 'http://localhost:6969/api/';

  constructor(private http: HttpClient) { }

  retrieveAccountInfo(username: string) {
    return this.http.get<AccountInfo>(this.baseUrl + 'retrieveAccountInfo?username=' + username);
  }
}
