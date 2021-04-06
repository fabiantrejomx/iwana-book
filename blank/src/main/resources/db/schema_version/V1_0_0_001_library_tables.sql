CREATE TABLE books (
  id int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  pages_number smallint(5) NOT NULL,
  authors_id int(20) UNSIGNED NOT NULL,
  editorial varchar(50) NOT NULL,
  isbn int(13) NOT NULL.
  category_id int(20) UNSIGNED  NOT NULL,
  price varchar(10) NOT NULL,
  summary text NOT NULL,
  publication_date date NOT NULL,
  deleted boolean NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX isbn_uk (isbn)
  CONSTRAINT books_authors_id_fk FOREIGN KEY (authors_id) REFERENCES authors (id) ON DELETE CASCADE UPDATE CASCADE
  CONSTRAINT books_category_id_fk FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE authors (
  id int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  first_surname varchar(50) NOT NULL,
  second_surname varchar(50) NOT NULL,
  birthdate date NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE category (
  id int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  category varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;
