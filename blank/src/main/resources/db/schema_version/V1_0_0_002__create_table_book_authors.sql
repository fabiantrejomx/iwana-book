CREATE TABLE IF NOT EXISTS book_author(
    id int NOT NULL,
    id_book int,
    id_author int,
    PRIMARY KEY(id),
    CONSTRAINT author_book_id_fk FOREIGN KEY(id_book) REFERENCES book(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT book_authors_id_fk FOREIGN KEY(id_author) REFERENCES author(id) ON DELETE CASCADE ON UPDATE CASCADE
    )ENGINE=INNODB DEFAULT CHARSET=utf8;