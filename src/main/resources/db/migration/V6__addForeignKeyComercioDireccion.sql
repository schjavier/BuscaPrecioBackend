ALTER TABLE comercio
ADD FOREIGN KEY (direccion_id)
REFERENCES direccion(id);