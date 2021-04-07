CREATE TABLE IF NOT EXISTS Autores (
  id int(3) NOT NULL AUTO_INCREMENT,
  Nombre varchar(20),
  ApeP varchar(25),
  ApeM varchar(25),
  Nacimiento date,
  PRIMARY KEY (id),
  UNIQUE INDEX (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Borrados (
  id int(3) NOT NULL AUTO_INCREMENT,
  Autor int(3) NOT NULL,
  Libro int(3) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX (id),
  CONSTRAINT FKBorrados959831 FOREIGN KEY (Autor) REFERENCES Autores (id),
  CONSTRAINT FKBorrados865498 FOREIGN KEY (Libro) REFERENCES Eliminados (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Categorias (
  id int(2) NOT NULL AUTO_INCREMENT,
  Nombre varchar(25) UNIQUE,
  PRIMARY KEY (id),
  UNIQUE INDEX (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Eliminados (
  id int(3) NOT NULL AUTO_INCREMENT,
  Titulo varchar(70) UNIQUE,
  Paginas int(3),
  Autores int(2),
  Editorial varchar(40),
  isbn varchar(20) UNIQUE,
  Categorias int(2),
  Precio float,
  Resumen varchar(255),
  Publicacion date,
  PRIMARY KEY (id),
  UNIQUE INDEX (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Escritos (
  id int(3) NOT NULL AUTO_INCREMENT,
  Autor int(3) NOT NULL,
  Libro int(3) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX (id),
  CONSTRAINT FKEscritos632496 FOREIGN KEY (Autor) REFERENCES Autores (id),
  CONSTRAINT FKEscritos385214 FOREIGN KEY (Libro) REFERENCES Libros (id) ON DELETE Cascade
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Libros (
  id int(3) NOT NULL AUTO_INCREMENT,
  Titulo varchar(70) UNIQUE,
  Paginas int(3),
  Autores int(2),
  Editorial varchar(40),
  isbn varchar(20) UNIQUE,
  Categorias int(2),
  Precio float,
  Resumen varchar(255),
  Publicacion date,
  PRIMARY KEY (id),
  UNIQUE INDEX (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Pertenecen (
  id int(3) NOT NULL AUTO_INCREMENT,
  Libro int(3) NOT NULL,
  Categoria int(2) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX (id),
  CONSTRAINT FKPertenecen116421 FOREIGN KEY (Libro) REFERENCES Libros (id),
  CONSTRAINT FKPertenecen711164 FOREIGN KEY (Categoria) REFERENCES Categorias (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;