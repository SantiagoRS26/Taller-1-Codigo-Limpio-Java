package AcessoDatos.DBContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static final String DB_URL = "jdbc:postgresql://floppy.db.elephantsql.com/gktcnret";
    private static final String USER = "gktcnret";
    private static final String PASSWORD = "TKEL6JLIobSi76QSVShHYE9blr8hVF19";

    private Connection conexion;

    public DBContext() {
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo conectar a la base de datos");
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void close() {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo cerrar la conexión a la base de datos");
        }
    }
}