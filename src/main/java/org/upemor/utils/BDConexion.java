package org.upemor.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexion {
    private static BDConexion instance;
    private Connection conexion;

    private BDConexion(int DATABASE_TYPE) {
        try {
            if (DATABASE_TYPE > 1) {
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

    private void conexionMySQL() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/cafeteria";
        String usuario = "root";
        String password = "";
        Class.forName("org.mariadb.jdbc.Driver");
        conexion = DriverManager.getConnection(url, usuario, password);
    }

    private void conexionSQLite() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:db/cafeteria.db";
        Class.forName("org.sqlite.JDBC");
        conexion = DriverManager.getConnection(url);
    }

    public static BDConexion getInstance() {
        if (instance == null) instance = new BDConexion(0);
        return instance;
    }

    public static BDConexion getInstance(int databaseType) {
        if (instance == null) instance = new BDConexion(databaseType);
        return instance;
    }

    public void closeConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) conexion.close();
    }

    public Connection getConexion() {
        return conexion;
    }
}
