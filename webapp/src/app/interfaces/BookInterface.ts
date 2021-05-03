import {AuthorInterface} from "./author-interface";
import {CategoryInterface} from "./category-interface";

export interface BookInterface {
  id: number;
  title: string;
  pages: number;
  isbn: string;
  price: number;
  summary: string;
  editorial: string;
  releaseDate: string;
  deleted: boolean;
  authors: AuthorInterface[];
  categories: CategoryInterface[];
}
