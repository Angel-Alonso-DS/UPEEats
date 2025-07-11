package org.upemor.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos (MySQL o SQLite) usando Singleton.
 * Permite seleccionar el tipo de base de datos mediante un parámetro entero.
 * DATABASE_TYPE = 1 para MySQL/MariaDB, DATABASE_TYPE = 0 para SQLite.
 */
public class BDConexion {

    /** Instancia única de la clase (patrón Singleton) */
    private static BDConexion instance;
    /** Objeto de conexión JDBC */
    private Connection conexion;

    /**
     * Constructor privado. Inicializa la conexión según el tipo de base de datos.
     * @param DATABASE_TYPE 1 para MySQL/MariaDB, 0 para SQLite
     */
    private BDConexion(int DATABASE_TYPE) {
        try {
            if (DATABASE_TYPE > 1 || DATABASE_TYPE < 0) {
                throw new IllegalArgumentException("Tipo de base de datos no válido: " + DATABASE_TYPE);
            }
            if (DATABASE_TYPE == 1) {
                conexionMySQL();
                return;
            }
            conexionSQLite();
            return;
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }

    /**
     * Establece la conexión con la base de datos MySQL/MariaDB.
     * @throws SQLException si ocurre un error de SQL
     * @throws ClassNotFoundException si no se encuentra el driver
     */
    private void conexionMySQL() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mariadb://localhost:3306/cafeteria";
        String usuario = "root";
        String password = "";
        Class.forName("org.mariadb.jdbc.Driver");
        conexion = DriverManager.getConnection(url, usuario, password);
    }

    /**
     * Establece la conexión con la base de datos SQLite.
     * @throws SQLException si ocurre un error de SQL
     * @throws ClassNotFoundException si no se encuentra el driver
     */
    private void conexionSQLite() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:db/cafeteria.db";
        Class.forName("org.sqlite.JDBC");
        conexion = DriverManager.getConnection(url);
    }


    /**
     * Obtiene la instancia única de la conexión (por defecto SQLite).
     * @return instancia de BDConexion
     */
    public static BDConexion getInstance() {
        if (instance == null) instance = new BDConexion(0);
        return instance;
    }


    /**
     * Obtiene la instancia única de la conexión, permitiendo elegir el tipo de base de datos.
     * @param databaseType 1 para MySQL/MariaDB, 0 para SQLite
     * @return instancia de BDConexion
     */
    public static BDConexion getInstance(int databaseType) {
        if (instance == null) instance = new BDConexion(databaseType);
        return instance;
    }


    /**
     * Cierra la conexión a la base de datos si está abierta.
     * @throws SQLException si ocurre un error al cerrar
     */
    public void closeConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) conexion.close();
    }

    /**
     * Devuelve el objeto Connection actual.
     * @return conexión JDBC activa
     */
    public Connection getConexion() {
        return conexion;
    }
}
