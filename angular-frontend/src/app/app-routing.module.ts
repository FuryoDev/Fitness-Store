import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {
  ProductByCategoryComponent
} from "./components/shopping/showcase/product-by-category/product-by-category.component";
import {CartPageComponent} from "./components/shopping/cart/cart-page/cart-page.component";
import {CustomerPageComponent} from "./components/customer/customer-page/customer-page.component";
import {ProductPageComponent} from "./components/shopping/showcase/product-page/product-page.component";
import {AdminProductsComponent} from "./components/admin/admin-products/admin-products.component";
import {ProductFormComponent} from "./components/admin/forms/product-form/product-form.component";
import {CategoryFormComponent} from "./components/admin/forms/category-form/category-form.component";
import {SubCategoryFormComponent} from "./components/admin/forms/sub-category-form/sub-category-form.component";
import {AdminCategoriesComponent} from "./components/admin/admin-categories/admin-categories.component";
import {AdminSubcategoriesComponent} from "./components/admin/admin-subcategories/admin-subcategories.component";
import {LoginFormComponent} from "./components/authentication/login-form/login-form.component";
import {RegisterFormComponent} from "./components/authentication/register-form/register-form.component";
import {CheckoutPageComponent} from "./components/shopping/checkout-page/checkout-page.component";
import {OrderPageComponent} from "./components/customer/order-page/order-page.component";
import {AdminStockComponent} from "./components/admin/admin-stock/admin-stock.component";
import {HomeComponent} from "./components/home/home.component";
import {AdminPaymentMethodComponent} from "./components/admin/admin-payment-method/admin-payment-method.component";
import {
  PaymentMethodFormComponent
} from "./components/admin/forms/payment-method/payment-method-form/payment-method-form.component";
import {AdminDiscountComponent} from "./components/admin/admin-discount/admin-discount.component";
import {DiscountFormComponent} from "./components/admin/forms/discount-form/discount-form.component";
import {
  DiscountToProductComponent
} from "./components/admin/admin-discount/discount-to-product/discount-to-product.component";

const routes: Routes = [
  {
    path: 'products-showcase/:catId',
    component: ProductByCategoryComponent
  },
  {
    path: 'product-page/:prodId',
    component: ProductPageComponent
  },
  {
    path: 'subcategories/:catId',
    component: AdminSubcategoriesComponent
  },
  {
    path: 'admin-products-by-subcategory/:subCatId',
    component: AdminProductsComponent
  },
  {
    path: 'create-category',
    component: CategoryFormComponent
  },
  {
    path: 'edit-category/:id',
    component: CategoryFormComponent
  },
  {
    path: 'create-subcategory/:catId',
    component: SubCategoryFormComponent
  },
  {
    path: 'edit-subcategory/:id',
    component: SubCategoryFormComponent
  },
  {
    path: 'create-product/:subCatId',
    component: ProductFormComponent
  },
  {
    path: 'edit-product/:id',
    component: ProductFormComponent
  },
  {
    path: 'edit-stock/:id',
    component: AdminStockComponent
  },
  {
    path: 'create-payment-method',
    component: PaymentMethodFormComponent
  },
  {
    path: 'edit-payment-method/:id',
    component: PaymentMethodFormComponent
  },
  {
    path: 'admin-dashboard',
    component: AdminCategoriesComponent
  },
  {
    path: 'admin-discount',
    component: AdminDiscountComponent
  },
  {
    path: 'discount-products',
    component: DiscountToProductComponent
  },
  {
    path: 'create-discount',
    component: DiscountFormComponent
  },
  {
    path: 'admin-payment-method',
    component: AdminPaymentMethodComponent
  },
  {
    path: 'login-page',
    component: LoginFormComponent
  },
  {
    path: 'register-page',
    component: RegisterFormComponent
  },
  {
    path: 'cart',
    component: CartPageComponent
  },
  {
    path: 'checkout',
    component: CheckoutPageComponent
  },
  {
    path: 'personal-page/order-page/:id',
    component: OrderPageComponent
  },
  {
    path: 'personal-page',
    component: CustomerPageComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'index',
    component: HomeComponent
  },
  {
    path: '',
    component: HomeComponent
  },
  {
    path: '**',
    redirectTo: 'index',
    pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
