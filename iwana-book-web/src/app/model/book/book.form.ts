import { Authors } from "../author/author";
import { Categories } from "../category/category";

export interface BookForm {
readonly title?:string;
readonly pages?:number;
readonly isbn?:string;
readonly price?:number;
readonly summary?:string;
readonly editorial?:string;
readonly datePublication?:string;
readonly categories?:Categories[];
readonly authors?:Authors[];
}