CREATE TABLE ranking {
    id int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    rank int(1) NULL,
    PRIMARY KEY (id)
} ENGINE=InnoDB;

ALTER TABLE books (
    ADD COLUMN ranking_id int(20) UNSIGNED NOT NULL,
    CONSTRAINT books_ranking_id_fk FOREIGN KEY (ranking_id) REFERENCES ranking (id) ON DELETE CASCADE UPDATE CASCADE
)