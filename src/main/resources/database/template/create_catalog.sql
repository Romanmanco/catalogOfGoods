CREATE TABLE catalog
(
    id           BIGINT NOT NULL
        CONSTRAINT catalog_pkey
            PRIMARY KEY,
    level        VARCHAR(255),
    catalog_name VARCHAR(255),
    parent       BIGINT
);

ALTER TABLE catalog
    OWNER TO postgres;