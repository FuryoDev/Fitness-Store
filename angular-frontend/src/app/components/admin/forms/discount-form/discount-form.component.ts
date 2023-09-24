import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Discount} from "../../../../common/prod-details/discount";
import {Category} from "../../../../common/prod-details/category";
import {ActivatedRoute, Router} from "@angular/router";
import {DiscountService} from "../../../../services/prod-details/discount/discount.service";

@Component({
  selector: 'app-discount-form',
  templateUrl: './discount-form.component.html',
  styleUrls: ['./discount-form.component.sass']
})
export class DiscountFormComponent implements OnInit {

  discount = new Discount();
  discountForm: FormGroup = new FormGroup( {
    name: new FormControl('', Validators.required),
    percentage: new FormControl('', Validators.required)
  })

  constructor(private router: Router,
              private route: ActivatedRoute,
              private discountService: DiscountService) { }

  ngOnInit(): void {
    this.route.params.subscribe(() => {
      this.initDiscountForm();
    })
  }

  initDiscountForm() {
    const hasDiscountId: boolean = this.route.snapshot.paramMap.has('id')
    if(hasDiscountId) {
      const discountId: number = +this.route.snapshot.paramMap.get('id')!;
      this.discountService.getDiscountById(discountId).subscribe(
        data => {
          this.discount = data;
        }
      )
    }
  }

  saveDiscount() {
    this.discountService.saveDiscount(this.discount).subscribe(
      data => {
        this.router.navigate(['/admin-discount']).then(r => console.log(r))
        window.location.reload();
      }
    )
  }

}
