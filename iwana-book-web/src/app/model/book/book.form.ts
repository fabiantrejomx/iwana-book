import { Author, Authors } from "../author/author";
import { Categories, Category } from "../category/category";

export interface BookForm {
readonly title?:string;
readonly pages?:number;
readonly isbn?:string;
readonly price?:number;
readonly summary?:string;
readonly editorial?:string;
readonly datePublication?:string;
readonly authors?:Authors[];
readonly categories?:Categories[];
}