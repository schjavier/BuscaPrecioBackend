
CREATE TABLE ofertas(
    id bigint not null auto_increment,
    descripcion varchar(300) not null,
    precio float not null,

    primary key(id)

);