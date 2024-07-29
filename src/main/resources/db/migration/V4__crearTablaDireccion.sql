
CREATE TABLE direccion(
    id bigint not null auto_increment,
    calle varchar(60) not null,
    barrio varchar(60) not null,
    provincia varchar(50) not null,

    primary key(id)

);