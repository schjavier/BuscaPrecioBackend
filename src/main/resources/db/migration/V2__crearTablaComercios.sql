CREATE TABLE comercio(
    id bigint not null auto_increment,
    nombre VARCHAR(100) not null,
    direccion_id bigint not null,
    abonado varchar(10),
    fecha_abono date,
    oferta_id bigint,
    usuario_id bigint,

    primary key(id),
    constraint fk_comercio_usuario_id foreign key(usuario_id) references usuario(id) ON DELETE CASCADE

)