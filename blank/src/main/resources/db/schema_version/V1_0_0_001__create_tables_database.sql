CREATE TABLE IF NOT EXISTS category
(
    id int NOT NULL,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY(id)
    )ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS editor(
    id int NOT NULL,
    name VARCHAR(40) NOT NULL,
    PRIMARY KEY(id)
    )ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS author
(
    id  int NOT NULL,
    name  VARCHAR(20) NOT NULL,
    last_name VARCHAR(20),
    second_name VARCHAR(20),
    birthday DATE,
    PRIMARY KEY(id)
    )ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS book
(
    id int NOT NULL,
    title VARCHAR(20),
    num_pages int,
    id_author int,
    id_category int,
    id_editor int,
    isbn int(13),
    price int,
    resume VARCHAR(255),
    date_public DATE,
    PRIMARY KEY(id),
    CONSTRAINT author_id_fk FOREIGN KEY(id_author) REFERENCES author(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT category_id_fk FOREIGN KEY(id_category) REFERENCES category(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT editor_id_fk FOREIGN KEY(id_editor) REFERENCES editor(id) ON DELETE CASCADE ON UPDATE CASCADE
    )ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ranking
(
    id int NOT NULL,
    raking int NOT NULL,
    id_author int,
    PRIMARY KEY(id),
    CONSTRAINT raking_authors_id_fk FOREIGN KEY(id_author) REFERENCES author(id) ON DELETE CASCADE ON UPDATE CASCADE
    )ENGINE=INNODB DEFAULT CHARSET=utf8;