CREATE TABLE books (
  id int(20) NOT NULL,
  title varchar(50) NOT NULL,
  pages_number smallint(5) NOT NULL,
  authors varchar(255) NOT NULL,
  editorial varchar(50) NOT NULL,
  isbn int(13) NOT NULL.
  category varchar(255) NOT NULL,
  price varchar(10) NOT NULL,
  summary text NOT NULL,
  publication_year int(4) NOT NULL,
  deleted boolean NOT NULL.
  PRIMARY KEY (id),
  UNIQUE INDEX isbn_uk (isbn)
) ENGINE=InnoDB;