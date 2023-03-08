import {ProductInfo} from "../shopping/product-info";
import {Category} from "./category";

export class SubCategory {
  subCategoryId!: number;
  name!: string;
  productInfos!: ProductInfo[];
  category!: Category;
}
