package org.upemor.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private static final String DATABASE_TYPE = "sqlite";

    private DBConnection() {
        try {
            switch (DATABASE_TYPE.toLowerCase()) {
                case "mysql":
                    connectMySQL();
                    break;
                case "sqlite":
                    connectSQLite();
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de base de datos no válido: " + DATABASE_TYPE);
            }
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }

    private void connectMySQL() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/cafeteria";
        String user = "root";
        String password = "";
        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
    }

    private void connectSQLite() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:db/cafeteria.db";
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(url);
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public void closeConecction() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
