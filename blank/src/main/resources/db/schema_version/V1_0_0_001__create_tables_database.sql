
CREATE TABLE IF NOT EXISTS categories (
                                          id int NOT NULL,
                                          name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS editoriales (
                                           id int NOT NULL,
                                           name VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS authors (
                                       id int NOT NULL,
                                       name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20),
    second_name VARCHAR(20),
    birthday DATE,
    PRIMARY KEY (id)
    ) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS books (
                                     id int NOT NULL,
                                     title VARCHAR(20),
    num_pages int,
    id_authors int,
    id_categories int,
    id_editorial int,
    isbn int(13),
    price int,
    resume VARCHAR (255),
    date_public DATE,
    PRIMARY KEY (id),
    CONSTRAINT authors_id_fk FOREIGN KEY (id_authors) REFERENCES authors (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT categories_id_fk FOREIGN KEY (id_categories) REFERENCES categories (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT editoriales_id_fk FOREIGN KEY (id_editorial) REFERENCES editoriales (id) ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ranking(
                                      id int NOT NULL,
                                      raking int NOT NULL,
                                      id_author int,
                                      PRIMARY KEY (id),
    CONSTRAINT raking_authors_id_fk FOREIGN KEY (id_author) REFERENCES authors (id) ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=INNODB DEFAULT CHARSET=utf8;