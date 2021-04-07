# iwana-book
Basic project to demo the use of Spring, Hibernate and JPA.


## Project 

```
mvn clean install -P dev -DskipTests=true
java -jar blank-api.jar

https://vaadin.com/docs/v14/flow/tutorials/in-depth-course/project-setup
```

## Database

name: test
username: dev
password: demo

##Description of the work performed

Shares:
Post of books.
Get the books
Put the books.
Delete books //TODO soft delete.

Following endpoints:

* Show all books sorted by year of publication in ascending order, the results should be paginated. (10 books in total, leaving 2 books per page)
* Descending books by year of publication. (10 books in total, leaving 2 books per page)
* Search for those books where X author participated by matching either his name or surname.
* Where the price is in the range from priceMinimum to priceMaximum.
* Given a number, search for books containing that number of authors.
* Search for books in a date range (startDate, endDate) of publication.
* Number of books that exist of x category
* Filter books by category.
* Filter books ascending and descending by number of pages contained in the book.

##TO DO
*Post review
*Calcule record score




##JSON request
https://www.getpostman.com/collections/b1cf6c0cc12ca79d70db
