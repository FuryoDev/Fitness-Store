import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TempoComponent} from './components/tempo/tempo.component';
import {HttpClientModule} from "@angular/common/http";
import {ProductService} from "./services/product.service";
import {HeaderComponent} from './components/navigation/header/header.component';
import {FooterComponent} from './components/navigation/footer/footer.component';
import {
  ProductByCategoryComponent
} from './components/shopping/showcase/product-by-category/product-by-category.component';
import {
  SubCategoryFilterComponent
} from './components/shopping/showcase/sub-category-filter/sub-category-filter.component';
import {ProductCardComponent} from './components/shopping/showcase/product-card/product-card.component';
import {ProductPageComponent} from './components/shopping/showcase/product-page/product-page.component';
import {CartPageComponent} from './components/shopping/cart/cart-page/cart-page.component';
import {CustomerPageComponent} from './components/customer/customer-page/customer-page.component';
import {CategoryFormComponent} from './components/admin/forms/category-form/category-form.component';
import {AdminProductsComponent} from './components/admin/admin-products/admin-products.component';
import {ProductFormComponent} from './components/admin/forms/product-form/product-form.component';
import {SubCategoryFormComponent} from './components/admin/forms/sub-category-form/sub-category-form.component';
import {AdminSubcategoriesComponent} from './components/admin/admin-subcategories/admin-subcategories.component';
import {AdminCategoriesComponent} from './components/admin/admin-categories/admin-categories.component';
import {FormsModule} from "@angular/forms";
import {LoginFormComponent} from './components/authentication/login-form/login-form.component';
import {RegisterFormComponent} from './components/authentication/register-form/register-form.component';
import {CheckoutPageComponent} from './components/shopping/checkout-page/checkout-page.component';
import {PersonalInfoComponent} from './components/customer/personal-info/personal-info.component';
import {CustomerOrdersComponent} from './components/customer/customer-orders/customer-orders.component';
import {OrderPageComponent} from './components/customer/order-page/order-page.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {AdminStockComponent} from './components/admin/admin-stock/admin-stock.component';


@NgModule({
  declarations: [
    AppComponent,
    TempoComponent,
    HeaderComponent,
    FooterComponent,
    ProductByCategoryComponent,
    SubCategoryFilterComponent,
    ProductCardComponent,
    ProductPageComponent,
    CartPageComponent,
    CustomerPageComponent,
    CategoryFormComponent,
    AdminSubcategoriesComponent,
    AdminProductsComponent,
    ProductFormComponent,
    SubCategoryFormComponent,
    AdminCategoriesComponent,
    AdminSubcategoriesComponent,
    AdminCategoriesComponent,
    ProductCardComponent,
    SubCategoryFilterComponent,
    LoginFormComponent,
    RegisterFormComponent,
    CheckoutPageComponent,
    PersonalInfoComponent,
    CustomerOrdersComponent,
    OrderPageComponent,
    AdminStockComponent
  ],
  imports: [
    BrowserModule,
    FontAwesomeModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
