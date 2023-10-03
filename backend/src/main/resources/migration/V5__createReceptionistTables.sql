CREATE TABLE reservation
(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    room_number INTEGER NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_client_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_room_number FOREIGN KEY (room_number) REFERENCES room(number)
);