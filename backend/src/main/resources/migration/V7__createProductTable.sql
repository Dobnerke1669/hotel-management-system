create table orders (
                      id  SERIAL PRIMARY KEY,
                      total_price NUMERIC NOT NULL,
                      room_number INTEGER NOT NULL,
                      done BOOLEAN NOT NULL
);

create table product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    category VARCHAR(255) NOT NULL,
    image_id INTEGER NOT NULL,
    price NUMERIC NOT NULL,
    description VARCHAR(255),
    available BOOLEAN NOT NULL
);

create table ordered_items (
                                 id SERIAL PRIMARY KEY,
                                 product_id INTEGER NOT NULL,
                                 order_id INTEGER NOT NULL,
                                 quantity INTEGER NOT NULL,
                                 total_price NUMERIC NOT NULL,
                                 CONSTRAINT fk_product
                                     FOREIGN KEY (product_id)
                                         REFERENCES product(id),
                                 CONSTRAINT fk_orders
                                     FOREIGN KEY (order_id)
                                         REFERENCES orders(id)
)

