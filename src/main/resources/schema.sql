CREATE TABLE if NOT EXISTS item (
    id identity,
    brand_from VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    year_created NUMERIC NOT NULL,
    price NUMERIC NOT NULL,
    created_at timestamp not null,
    quantity NUMERIC NOT NULL
);
