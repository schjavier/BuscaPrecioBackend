CREATE TABLE comercios(
    id bigint not null auto_increment,
    nombre VARCHAR(100) not null,
    direccion_id bigint not null,
    abonado varchar(10),
    fecha_abono date,
    oferta_id bigint,
    user_id bigint,

    primary key(id),
    constraint fk_comercio_user_id foreign key(user_id) references user(id)

)