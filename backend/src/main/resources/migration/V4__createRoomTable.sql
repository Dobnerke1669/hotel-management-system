CREATE TABLE room
(
    number   SERIAL PRIMARY KEY,
    room_type   VARCHAR(255) NOT NULL,
    price_per_night DECIMAL(10, 2) NOT NULL
);