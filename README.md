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
Delete books.

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



##JSON request
https://www.getpostman.com/collections/b1cf6c0cc12ca79d70db

##Git compare vs develop
https://github.com/fabiantrejomx/iwana-book/compare/AYLIN-RIOS

##Endpoint
-- Ordenar por fecha de publicación ascendente

http://localhost:8080/blank-api/api/book/list/ordered/asc?limit=2&offset=2

-- Ordenar por fecha de publicación descendente

http://localhost:8080/blank-api/api/book/list/ordered/desc?limit=2&offset=2

-- Ordenar por paginas asc

http://localhost:8080/blank-api/api/book/list/ordered/pages/asc?limit=2&offset=2

-- Ordenar por paginas desc

http://localhost:8080/blank-api/api/book/list/ordered/pages/desc?limit=2&offset=2

-- Libros por author

http://localhost:8080/blank-api/api/book/list/author?author=authorA

-- Libros por rango de precio

http://localhost:8080/blank-api/api/book/list/price?priceMin=300&priceMax=600

-- Libros por rango de fecha

http://localhost:8080/blank-api/api/book/list/datePublication?startDate=1620-01-01&endDate=1963-01-01

-- Cantidad de libros por categoria

http://localhost:8080/blank-api/api/book/list/countByCategory?category=drama

-- Libros que tienen cierto numero de autores

http://localhost:8080/blank-api/api/book/list/countAuthors?numAuthors=3

-- Book with ranking

http://localhost:8080/blank-api/api/book/list/ordered/ranking?limit=5&offset=2

-- Soft Delete

http://localhost:8080/blank-api/api/book/delete/2
