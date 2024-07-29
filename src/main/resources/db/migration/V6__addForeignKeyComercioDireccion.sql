ALTER TABLE comercios
ADD FOREIGN KEY (direccion_id)
REFERENCES direccion(id);