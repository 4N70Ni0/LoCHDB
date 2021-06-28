-- Borrar los FOREIGN KEY CONSTRAINT --
ALTER TABLE tbl_establecimiento DROP CONSTRAINT fk_prop_usr;
ALTER TABLE tbl_ticket DROP CONSTRAINT fk_prop_estab;
ALTER TABLE usr_buscar_estab DROP CONSnTRAINT fk_buscar_usr;
ALTER TABLE usr_buscar_estab DROP CONSTRAINT fk_buscar_estab;
ALTER TABLE usr_coger_ticket DROP CONSTRAINT fk_coger_usr;
ALTER TABLE usr_coger_ticket DROP CONSTRAINT fk_coger_ticket;
-- Borrar las tablas --
DROP TABLE usr_buscar_estab CASCADE;
DROP TABLE usr_coger_ticket CASCADE;
DROP TABLE tbl_usuario CASCADE;
DROP TABLE tbl_establecimiento CASCADE;
DROP TABLE tbl_ticket CASCADE;


CREATE TABLE tbl_usuario (
	cod_usr INT NOT NULL,
	correo VARCHAR(30) NOT NULL,
	contrasenia VARCHAR(20) NOT NULL,
	ubicacion VARCHAR(50) NOT NULL,

	CONSTRAINT pk_usr PRIMARY KEY (cod_usr)
);

CREATE TABLE tbl_establecimiento (
	cod_estab INT NOT NULL,
	nombre VARCHAR(20) NOT NULL,
	personas_actuales INT NOT NULL,
	aforo_maximo INT NOT NULL,
	ubicacion VARCHAR(50) NOT NULL,
	prop_cod_usr INT NOT NULL, -- Propagación de USR a ESTAB. Puede ser NULL.


	CONSTRAINT pk_estab PRIMARY KEY (cod_estab),
	CONSTRAINT fk_prop_usr FOREIGN KEY (prop_cod_usr) REFERENCES tbl_usuario(cod_usr) ON DELETE CASCADE
);

CREATE TABLE tbl_ticket (
	cod_ticket INT NOT NULL,
	prop_cod_estab INT, -- Propagación de ESTAB a TICKET. Puede ser NULL (insertar Tickets que referencien a inexistentes establecimientos)

	CONSTRAINT pk_ticket PRIMARY KEY (cod_ticket),
	CONSTRAINT fk_prop_estab FOREIGN KEY (prop_cod_estab) REFERENCES tbl_establecimiento(cod_estab) ON DELETE CASCADE
);



-- TABLAS PARA RELACIONAR --
CREATE TABLE usr_buscar_estab (
	-- NO PUEDEN SER NULL.
	ube_cod_usr INT NOT NULL,
	ube_cod_estab INT NOT NULL,

	-- Si no se definen las FK como PK, se entenderán como una propagación.
	CONSTRAINT pk_usr_estab PRIMARY KEY (ube_cod_usr, ube_cod_estab),
	CONSTRAINT fk_buscar_usr FOREIGN KEY (ube_cod_usr) REFERENCES tbl_usuario(cod_usr),
	CONSTRAINT fk_buscar_estab FOREIGN KEY (ube_cod_estab) REFERENCES tbl_establecimiento(cod_estab)
);

CREATE TABLE usr_coger_ticket (
	-- NO PUEDEN SER NULL.
	uct_cod_usr INT NOT NULL,
	uct_cod_ticket INT NOT NULL,

	-- Si no se definen las FK como PK, se entenderán como una propagación.
	CONSTRAINT pk_usr_ticket PRIMARY KEY (uct_cod_usr, uct_cod_ticket),
	CONSTRAINT fk_coger_usr FOREIGN KEY (uct_cod_usr) REFERENCES tbl_usuario(cod_usr),
	CONSTRAINT fk_coger_ticket FOREIGN KEY (uct_cod_ticket) REFERENCES tbl_ticket(cod_ticket)
);
