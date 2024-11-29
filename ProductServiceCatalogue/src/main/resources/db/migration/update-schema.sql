CREATE TABLE category
(
    id              BIGINT   NOT NULL,
    created_at      datetime NOT NULL,
    last_updated_at datetime NOT NULL,
    state           SMALLINT NULL,
    name            VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BIGINT   NOT NULL,
    created_at      datetime NOT NULL,
    last_updated_at datetime NOT NULL,
    state           SMALLINT NULL,
    title           VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    image_url       VARCHAR(255) NULL,
    amount DOUBLE NULL,
    category_id     BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);