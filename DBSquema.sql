use BlogNotas;

CREATE TABLE prioridad (
	prioridad_id INT NOT NULL ,
    nombre VARCHAR(100),
    PRIMARY KEY(prioridad_id)
);

CREATE TABLE usuario (
	usuario_id INT IDENTITY(1,1) NOT NULL,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    tiempo_registro DATETIME,
    activo TINYINT,
    celular VARCHAR(20),
    contrasena VARCHAR(50),
    ultimo_token_acceso TEXT,
    tiempo_ultimo_acceso DATETIME,
    otp VARCHAR(6),
    tiempo_activacion DATETIME,
    PRIMARY KEY(usuario_id)
);

CREATE TABLE libreta (
	libreta_id INT IDENTITY(1,1) NOT NULL,
    nombre VARCHAR(100),
    color_hexadecimal VARCHAR(20),
    usuario_id INT,
    PRIMARY KEY(libreta_id),
    FOREIGN KEY(usuario_id) REFERENCES usuario(usuario_id)
);

CREATE TABLE nota (
	nota_id INT IDENTITY(1,1) NOT NULL,
    titulo VARCHAR(200),
    contenido TEXT,
    tiempo_creacion DATETIME,
    usuario_creacion_id INT,
    eliminado TINYINT,
    libreta_id INT,
    prioridad_id INT,
    PRIMARY KEY(nota_id),
    FOREIGN KEY(usuario_creacion_id) REFERENCES usuario(usuario_id),
    FOREIGN KEY(libreta_id) REFERENCES libreta(libreta_id),
    FOREIGN KEY(prioridad_id) REFERENCES prioridad(prioridad_id)
);