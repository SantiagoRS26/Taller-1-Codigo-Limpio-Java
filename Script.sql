CREATE TABLE Persona (
    cedula VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(255),
    genero VARCHAR(10),
    edad INT,
    direccion VARCHAR(255)
);

CREATE TABLE Usuario (
    userId VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50),
    username VARCHAR(50),
    email VARCHAR(100),
    personaCedula VARCHAR(20),
    FOREIGN KEY (personaCedula) REFERENCES Persona(cedula)
);

CREATE TABLE Plataforma (
    plataformaId VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100),
    tipo VARCHAR(50),
    usuariosConectados INT
);

CREATE TABLE UsuarioRegistrado (
    usuarioRegistradoId VARCHAR(50) PRIMARY KEY,
    fechaRegistro TIMESTAMP,
    nivelAcceso VARCHAR(50),
    usuarioId VARCHAR(50),
    plataformaId VARCHAR(50),
    FOREIGN KEY (usuarioId) REFERENCES Usuario(userId),
    FOREIGN KEY (plataformaId) REFERENCES Plataforma(plataformaId)
);

CREATE TABLE LoginUsuario (
    loginUsuarioId VARCHAR(50) PRIMARY KEY,
    fechaLogin TIMESTAMP,
    usuarioRegistradoId VARCHAR(50),
    FOREIGN KEY (usuarioRegistradoId) REFERENCES UsuarioRegistrado(usuarioRegistradoId)
);

/* Ingresar Datos */

-- Insertar persona 1
INSERT INTO Persona (cedula, nombre, genero, edad, direccion)
VALUES ('123456789', 'Juan Pérez', 'Masculino', 35, 'Calle 123, Bogotá');

-- Insertar persona 2
INSERT INTO Persona (cedula, nombre, genero, edad, direccion)
VALUES ('987654321', 'María González', 'Femenino', 28, 'Avenida Principal, Ciudad de México');

-- Insertar persona 3
INSERT INTO Persona (cedula, nombre, genero, edad, direccion)
VALUES ('456789123', 'Carlos Rodríguez', 'Masculino', 42, 'Calle Central, Buenos Aires');

-- Insertar persona 4
INSERT INTO Persona (cedula, nombre, genero, edad, direccion)
VALUES ('789123456', 'Luisa Sánchez', 'Femenino', 24, 'Avenida 456, Madrid');

-- Insertar persona 5
INSERT INTO Persona (cedula, nombre, genero, edad, direccion)
VALUES ('654321987', 'Pedro López', 'Masculino', 30, 'Calle 789, Sao Paulo');

/* Usuarios */

-- Insertar usuario 1
INSERT INTO Usuario (userId, password, username, email, personaCedula)
VALUES ('user123', 'claveSegura1', 'juanperez35', 'juan.perez@example.com', '123456789');

-- Insertar usuario 2
INSERT INTO Usuario (userId, password, username, email, personaCedula)
VALUES ('mariag', 'contraseña123', 'mariagonzalez', 'maria.gonzalez@example.com', '987654321');


/* Plataforma */

-- Insertar plataforma 1
INSERT INTO Plataforma (plataformaId, nombre, tipo, usuariosConectados)
VALUES ('facebook', 'Facebook', 'Red Social', 250);

-- Insertar plataforma 2
INSERT INTO Plataforma (plataformaId, nombre, tipo, usuariosConectados)
VALUES ('whatsapp', 'WhatsApp', 'Mensajería', 300);

-- Insertar plataforma 3
INSERT INTO Plataforma (plataformaId, nombre, tipo, usuariosConectados)
VALUES ('fortnite', 'Fortnite', 'Videojuego', 400);

-- Insertar plataforma 4
INSERT INTO Plataforma (plataformaId, nombre, tipo, usuariosConectados)
VALUES ('spotify', 'Spotify', 'Streaming de música', 600);

-- Insertar plataforma 5
INSERT INTO Plataforma (plataformaId, nombre, tipo, usuariosConectados)
VALUES ('netflix', 'Netflix', 'Streaming de video', 300);