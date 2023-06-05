import { Component, OnInit } from '@angular/core';
import {PaymentMethod} from "../../../../../common/shopping/payment-method";
import {PaymentMethodService} from "../../../../../services/shopping/payment-method/payment-method.service";
import {ActivatedRoute} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-payment-method-form',
  templateUrl: './payment-method-form.component.html',
  styleUrls: ['./payment-method-form.component.sass']
})
export class PaymentMethodFormComponent implements OnInit {

  paymentMethodForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    image: new FormControl('', Validators.required)
  });
  imageFile: File | null = null
  paymentMethod: PaymentMethod = new PaymentMethod();
  errorMessage: string | undefined;
  constructor(private paymentMethodService: PaymentMethodService,
              private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.route.params.subscribe(() =>  {
      this.initPaymentMethodForm();
    })
  }

  initPaymentMethodForm() {
    const hasPaymentMethodId: boolean = this.route.snapshot.paramMap.has('id')
    if(hasPaymentMethodId) {
      const paymentMethodId: number = +this.route.snapshot.paramMap.get('id')!;
      this.paymentMethodService.getPaymentMethodById(paymentMethodId).subscribe(
        data => {
          this.paymentMethod = data;
        }
      )
    }
  }

  savePaymentMethod() {
    this.paymentMethodService.savePaymentMethod(this.paymentMethod, this.imageFile).subscribe(
      data => {
        console.log(data);
      },
      error => {
        this.errorMessage = error.error.message;
      }
    );
  }

  onImageSelected(event: any) {
    this.imageFile = event.target.files[0];
  }
  get name() {return this.paymentMethodForm.get('name'); }
  get image() {return this.paymentMethodForm.get('image'); }
}
