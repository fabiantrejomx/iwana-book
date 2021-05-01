import { Author } from "../author/author";
import { Category } from "../category/category";

export class Book {
  id:number;
  title:string;
  pages:number;
  isbn:string;
  price:number;
  summary:string;
  editorial:string;
  datePublication:string;
  categories:number[];
  authors:number[];
  
}
