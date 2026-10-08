package org.upemor.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase Singleton que gestiona la conexión a la base de datos.
 * Intenta primero conectarse a MariaDB; si falla, intenta conectarse a SQLite.
 * Proporciona una única instancia accesible globalmente mediante getInstancia().
 * Esta clase se encarga de cargar los drivers necesarios, establecer la conexión
 * y habilitar claves foráneas en SQLite.
 * 
 * @author angelalonso
 */

public class ConexionBD {
    /** Instancia única de la clase (patrón Singleton) */
    private static final ConexionBD INSTANCIA = new ConexionBD();
    /** Objeto de conexión JDBC activo (MariaDB o SQLite) */
    private Connection conexion;
    /** Indica el tipo de conexión activa: "mariadb" o "sqlite" */
    private String TIPO_CONEXION;

    /**
     * Devuelve la única instancia de la clase (Singleton).
     * @return instancia global de ConexionBD
     */
    public static ConexionBD getInstania() {
        return INSTANCIA;
    }

    /**
     * Devuelve el tipo de conexión activa ("mariadb" o "sqlite").
     * @return tipo de conexión en uso
     */
    public String getTipoConexion() {
        return TIPO_CONEXION;
    }

    /**
     * Devuelve el objeto de conexión JDBC actual.
     * @return conexión activa a la base de datos
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Constructor privado. Intenta establecer conexión con MariaDB.
     * Si falla, intenta conectarse a SQLite.
     */
    private ConexionBD() {
        try {
            conexion = conexionMariaDB();
            TIPO_CONEXION = "mariadb";
            System.out.println("Conexion con MariaDB");
        } catch (Exception exMaria) {
            System.err.println("No se pudo conectar a MariaDB: " + exMaria.getMessage());

            try {
                conexion = conexionSQLite();
                TIPO_CONEXION = "sqlite";
                System.out.println("Conexion con SQLite");
            } catch (Exception exSQL) {
                System.err.println("No se pudo conectar a SQLite: " + exSQL.getMessage());
            }
        }
    }

    /**
     * Establece una conexión con la base de datos MariaDB.
     * 
     * @return objeto Connection conectado a MariaDB
     * @throws SQLException si ocurre un error SQL
     * @throws ClassNotFoundException si no se encuentra el driver JDBC
     */
    private Connection conexionMariaDB() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver"); // Carga el driver de MariaDB
        
        return DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/Cafeteria", // URL de la BD
            "root",                                   // Usuario
            ""                                    // Contraseña (vacía en este caso)
        );
    }

    /**
     * Establece una conexión con la base de datos SQLite.
     * Además, habilita el uso de claves foráneas mediante PRAGMA.
     * 
     * @return objeto Connection conectado a SQLite
     * @throws SQLException si ocurre un error SQL
     * @throws ClassNotFoundException si no se encuentra el driver JDBC
     */
    private Connection conexionSQLite() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC"); // Carga el driver de SQLite

        Connection conn = DriverManager.getConnection("jdbc:sqlite:db/cafeteria.db");
        // Activa el soporte para claves foráneas en SQLite
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }
        
        return conn;
    }

}
