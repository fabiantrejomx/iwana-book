import {AuthorInterface} from "./AuthorInterface";
import {CategoryInterface} from "./CategoryInterface";

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
