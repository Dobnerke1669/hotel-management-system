create table image_data(
    id serial primary key,
    name varchar(255) not null,
    type varchar(255) not null,
    image_data bigint not null
)