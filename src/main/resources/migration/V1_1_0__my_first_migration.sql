CREATE TABLE IF NOT EXISTS catalog(
 id BIGINT PRIMARY KEY,
 catalog_name VARCHAR(100) NOT NULL,
 parent BIGINT,
 child BIGINT,
 level int
)