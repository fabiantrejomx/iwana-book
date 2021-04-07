# iwana-book
Basic project to demo the use of Spring, Hibernate and JPA.


## Project 

```
mvn clean install -P dev -DskipTests=true
java -jar blank-api.jar

https://vaadin.com/docs/v14/flow/tutorials/in-depth-course/project-setup
```

#test with postman
Postman:
https://drive.google.com/file/d/1mUH0Ijmua9iyJ-ti-zmzrSqn9F5u-Cxg/view?usp=sharing 


## Database

name: test
username: dev
password: demo

## Description of the work performed
 Tasks:
    - Create new books (Post)
    - Update books (Put)
    - Get Books (Get)
    - Delete books (without soft delete)
    
 File:
    Postman collection
    
 Endpoints
    1. Show all books sorted by year of publication in ascending order, the results should be paginated. (10 books in total, leaving 2 books per page)
    2. Books descending by year of publication. (10 books in total, leaving 2 books per page)
    3. Search for those books where X author participated by matching either his first or last name.
    4. Where the price is in the range from priceMinimum to priceMaximum.
    5. Given a number, search for books containing that number of authors.
    6. Search for those books in a date range (StartDate, EndDate) of publication.
    7. Number of books that exist of x category
    8. Filter books by category.
    9. Filter books in ascending and descending order by number of pages contained in the book.

## Description of the work TO DO
    1. Soft delete
    2. Pots review
    3. Calcule record score
    
    
#Git compare VS develop
    https://github.com/fabiantrejomx/iwana-book/compare/SENDY-CRUZ 
 
 


