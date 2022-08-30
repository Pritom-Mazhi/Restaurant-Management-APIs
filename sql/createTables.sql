CREATE TABLE items (
    item_id SERIAL PRIMARY KEY,
    name varchar(20) NOT NULL
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    table_no int4 NOT NULL,
    item int4 NOT NULL REFERENCES items(item_id) ON DELETE CASCADE,
    cooking_time int4 NOT NULL
);

