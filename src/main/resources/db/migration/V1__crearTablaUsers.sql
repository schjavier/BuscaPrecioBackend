
CREATE TABLE user(
    id bigint not null auto_increment,
    nombre varchar(60) not null,
    email varchar(30) not null,
    password varchar(50) not null,
    rol varchar(10),

    primary key(id)

);