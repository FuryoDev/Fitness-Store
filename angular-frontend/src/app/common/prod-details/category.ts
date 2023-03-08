import {Discount} from "./discount";
import {Stock} from "./stock";
import {SubCategory} from "./sub-category";

export class Category {
  public categoryId!: number;
  public name!: string;
  public sizingType!: string;
  public subCategories!: SubCategory[];
}
