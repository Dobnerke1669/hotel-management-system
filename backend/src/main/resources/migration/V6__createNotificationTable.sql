CREATE TABLE notification_cleaning
(
    id          SERIAL PRIMARY KEY,
    details     VARCHAR(255) NOT NULL,
    room_number INTEGER NOT NULL,
    done        BOOLEAN NOT NULL,
    cleaner_id  INTEGER,
    CONSTRAINT fk_cleaner_id FOREIGN KEY (cleaner_id) REFERENCES users(id),
    CONSTRAINT fk_room_number FOREIGN KEY (room_number) REFERENCES room(number)
);