import { Injectable } from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HeaderSharedService {
  constructor() { }

  private reloadHeaderSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  reloadHeader$ = this.reloadHeaderSubject.asObservable();

  reloadHeader() {
    this.reloadHeaderSubject.next(true);
  }
}
