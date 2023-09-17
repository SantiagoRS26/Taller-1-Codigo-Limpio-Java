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

/* Procedimientos Almacenados */

/* CRUD de Persona */

-- Crear Persona
CREATE OR REPLACE FUNCTION sp_InsertarPersona(
    cedula VARCHAR(20),
    nombre VARCHAR(255),
    genero VARCHAR(10),
    edad INT,
    direccion VARCHAR(255)
) RETURNS BOOLEAN AS $$
DECLARE
    exito BOOLEAN := FALSE;
BEGIN
    -- Insertar el registro en la tabla Persona
    INSERT INTO Persona (cedula, nombre, genero, edad, direccion)
    VALUES (cedula, nombre, genero, edad, direccion);

    -- Si la inserción se realizó con éxito, establecer exito en TRUE
    exito := TRUE;

    RETURN exito;
EXCEPTION
    -- En caso de error, la función devolverá FALSE automáticamente
    WHEN OTHERS THEN
        RAISE;
END;
$$ LANGUAGE plpgsql;



-- Editar Persona
CREATE OR REPLACE FUNCTION sp_EditarPersona(
    cedula_existente VARCHAR(20),
    nuevo_cedula VARCHAR(20),
    nuevo_nombre VARCHAR(255),
    nuevo_genero VARCHAR(10),
    nuevo_edad INT,
    nuevo_direccion VARCHAR(255)
) RETURNS BOOLEAN AS $$
DECLARE
    exito BOOLEAN := FALSE;
BEGIN
    -- Inicio de la transacción
    BEGIN
        -- Actualizar el registro en la tabla Persona
        UPDATE Persona
        SET cedula = nuevo_cedula,
            nombre = nuevo_nombre,
            genero = nuevo_genero,
            edad = nuevo_edad,
            direccion = nuevo_direccion
        WHERE cedula = cedula_existente;

        -- Comprobar si se actualizó al menos una fila
        GET DIAGNOSTICS exito = ROW_COUNT;

        -- Confirmar la transacción
        COMMIT;
    EXCEPTION
        -- En caso de error, hacer un rollback de la transacción
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;

    -- Retornar el valor booleano que indica si se realizó la actualización
    RETURN exito > 0;
END;
$$ LANGUAGE plpgsql;


-- Eliminar Persona
CREATE OR REPLACE FUNCTION sp_EliminarPersona(
    cedula_param VARCHAR(20)
) RETURNS BOOLEAN AS $$
DECLARE
    exito BOOLEAN := FALSE;
BEGIN
    -- Inicio de la transacción
    BEGIN
        -- Eliminar el registro de la tabla Persona
        DELETE FROM Persona WHERE cedula = cedula_param;

        -- Verificar si se eliminó algún registro
        GET DIAGNOSTICS exito = ROW_COUNT;

        -- Confirmar la transacción
        COMMIT;
    EXCEPTION
        -- En caso de error, hacer un rollback de la transacción
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;

    -- Retornar el valor booleano que indica el resultado de la eliminación
    RETURN exito > 0;
END;
$$ LANGUAGE plpgsql;


-- Buscar Todos los Registros de Persona
CREATE OR REPLACE FUNCTION sp_BuscarTodasLasPersonas() RETURNS SETOF Persona AS $$
BEGIN
    RETURN QUERY
    SELECT *
    FROM Persona;
END;
$$ LANGUAGE plpgsql;


-- Buscar Persona por Cédula
CREATE OR REPLACE FUNCTION sp_BuscarPersonaPorCedula(
    cedula_buscar VARCHAR(20)
) RETURNS SETOF Persona AS $$
BEGIN
    -- Realizar la búsqueda por cédula
    RETURN QUERY 
    SELECT *
    FROM Persona
    WHERE cedula = cedula_buscar;
END;
$$ LANGUAGE plpgsql;


/* CRUD Plataforma */

--Insertar Plataforma

CREATE OR REPLACE FUNCTION sp_InsertarPlataforma(
    plataformaId VARCHAR(50),
    nombre VARCHAR(100),
    tipo VARCHAR(50),
    usuariosConectados INT
) RETURNS BOOLEAN AS $$
DECLARE
    exito BOOLEAN := FALSE;
BEGIN
    -- Inicio de la transacción
    BEGIN
        -- Insertar el registro en la tabla Plataforma
        INSERT INTO Plataforma (plataformaId, nombre, tipo, usuariosConectados)
        VALUES (plataformaId, nombre, tipo, usuariosConectados);

        -- Confirmar la transacción
        COMMIT;

        -- Si la inserción se realizó con éxito, establecer exito en TRUE
        exito := TRUE;
    EXCEPTION
        -- En caso de error, hacer un rollback de la transacción
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;

    -- Retornar el valor booleano que indica el resultado de la inserción
    RETURN exito;
END;
$$ LANGUAGE plpgsql;

-- Editar Plataforma

CREATE OR REPLACE FUNCTION sp_EditarPlataforma(
    plataformaId VARCHAR(50),
    nombre VARCHAR(100),
    tipo VARCHAR(50),
    usuariosConectados INT
) RETURNS BOOLEAN AS $$
DECLARE
    exito BOOLEAN := FALSE;
BEGIN
    -- Inicio de la transacción
    BEGIN
        -- Actualizar el registro en la tabla Plataforma
        UPDATE Plataforma
        SET
            nombre = nombre,
            tipo = tipo,
            usuariosConectados = usuariosConectados
        WHERE
            plataformaId = plataformaId;

        -- Comprobar si se realizó alguna actualización
        GET DIAGNOSTICS exito = ROW_COUNT;

        -- Confirmar la transacción
        COMMIT;
    EXCEPTION
        -- En caso de error, hacer un rollback de la transacción
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;

    -- Retornar el valor booleano que indica el resultado de la actualización
    RETURN exito > 0;
END;
$$ LANGUAGE plpgsql;


-- Eliminar Plataforma
CREATE OR REPLACE FUNCTION sp_EliminarPlataforma(
    plataformaId VARCHAR(50)
) RETURNS BOOLEAN AS $$
DECLARE
    exito BOOLEAN := FALSE;
BEGIN
    -- Inicio de la transacción
    BEGIN
        -- Eliminar el registro de la tabla Plataforma por su ID
        DELETE FROM Plataforma WHERE plataformaId = plataformaId;

        -- Verificar si se eliminó algún registro
        GET DIAGNOSTICS exito = ROW_COUNT;

        -- Confirmar la transacción
        COMMIT;
    EXCEPTION
        -- En caso de error, hacer un rollback de la transacción
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;

    -- Retornar el valor booleano que indica si se eliminó algún registro
    RETURN exito > 0;
END;
$$ LANGUAGE plpgsql;


-- Buscar Todas las Plataformas
CREATE OR REPLACE FUNCTION sp_BuscarTodasLasPlataformas()
RETURNS SETOF Plataforma AS $$
BEGIN
    RETURN QUERY SELECT * FROM Plataforma;
END;
$$ LANGUAGE plpgsql;


-- Buscar por plataformaId
CREATE OR REPLACE FUNCTION sp_BuscarPlataformaPorId(
    plataformaId VARCHAR(50)
)
RETURNS SETOF Plataforma AS $$
BEGIN
    RETURN QUERY SELECT * FROM Plataforma WHERE plataformaId = plataformaId;
END;
$$ LANGUAGE plpgsql;

-- Crear Usuario
CREATE OR REPLACE FUNCTION sp_InsertarUsuario(
    userId VARCHAR(50),
    password VARCHAR(50),
    username VARCHAR(50),
    email VARCHAR(100),
    personaCedula VARCHAR(20)
) RETURNS BOOLEAN AS $$
DECLARE
    exito BOOLEAN := FALSE;
BEGIN
    -- Inicio de la transacción
    BEGIN
        -- Insertar el registro en la tabla Usuario
        INSERT INTO Usuario (userId, password, username, email, personaCedula)
        VALUES (userId, password, username, email, personaCedula);

        -- Confirmar la transacción
        COMMIT;

        -- Si la inserción se realizó con éxito, establecer exito en TRUE
        exito := TRUE;
    EXCEPTION
        -- En caso de error, hacer un rollback de la transacción
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;

    -- Retornar el valor booleano que indica el resultado de la inserción
    RETURN exito;
END;
$$ LANGUAGE plpgsql;

-- Editar Usuario

-- Definir el procedimiento almacenado
CREATE OR REPLACE FUNCTION sp_EditarUsuario(
    userId_to_update VARCHAR(50),
    new_password VARCHAR(50),
    new_username VARCHAR(50),
    new_email VARCHAR(100),
    new_personaCedula VARCHAR(20)
) RETURNS BOOLEAN AS $$
DECLARE
    exito BOOLEAN := FALSE;
BEGIN
    -- Inicio de la transacción
    BEGIN
        -- Actualizar el registro en la tabla Usuario
        UPDATE Usuario
        SET
            password = new_password,
            username = new_username,
            email = new_email,
            personaCedula = new_personaCedula
        WHERE
            userId = userId_to_update;

        -- Verificar si se realizó una actualización exitosa
        GET DIAGNOSTICS exito = ROW_COUNT;

        -- Confirmar la transacción
        COMMIT;
    EXCEPTION
        -- En caso de error, hacer un rollback de la transacción
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;

    -- Retornar el valor booleano que indica si se realizó la actualización
    RETURN exito > 0;
END;
$$ LANGUAGE plpgsql;

-- Eliminar Usuario

CREATE OR REPLACE FUNCTION sp_EliminarUsuario(
    userId VARCHAR(50)
) RETURNS BOOLEAN AS $$
DECLARE
    exito BOOLEAN := FALSE;
BEGIN
    -- Inicio de la transacción
    BEGIN
        -- Eliminar el registro de la tabla Usuario
        DELETE FROM Usuario WHERE userId = userId;

        -- Confirmar la transacción
        COMMIT;

        -- Si la eliminación se realizó con éxito, establecer exito en TRUE
        exito := TRUE;
    EXCEPTION
        -- En caso de error, hacer un rollback de la transacción
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END;

    -- Retornar el valor booleano que indica el resultado de la eliminación
    RETURN exito;
END;
$$ LANGUAGE plpgsql;

--Buscar todos los Usuarios

CREATE OR REPLACE FUNCTION sp_BuscarUsuarios() RETURNS SETOF Usuario AS $$
BEGIN
    RETURN QUERY
    SELECT * FROM Usuario;
END;
$$ LANGUAGE plpgsql;

--Buscar Usuario por userId
-- Definir el procedimiento almacenado
CREATE OR REPLACE FUNCTION sp_BuscarUsuarioPorId(
    usuario_id VARCHAR(50)
) RETURNS SETOF Usuario AS $$
BEGIN
    RETURN QUERY
    SELECT * FROM Usuario
    WHERE userId = usuario_id;
END;
$$ LANGUAGE plpgsql;
