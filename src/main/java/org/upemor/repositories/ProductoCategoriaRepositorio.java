package org.upemor.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.upemor.config.ConexionBD;
import org.upemor.models.Categorias;

/**
 * Repositorio que gestiona la relación muchos a muchos entre Productos y Categorías.
 */
public class ProductoCategoriaRepositorio {
    private final Connection conexion;

    public ProductoCategoriaRepositorio() {
        this.conexion = ConexionBD.getInstania().getConexion();
    }

    /**
     * Obtiene las categorías asociadas a un producto específico.
     *
     * @param idProducto ID del producto
     * @return Lista de categorías relacionadas
     * @throws SQLException Si ocurre un error de SQL
     */
    public List<Categorias> obtenerCategoriasPorProducto(long idProducto) throws SQLException {
        final String sql = """
            SELECT c.* FROM Categorias c
            JOIN Productos_Categorias pc ON c.id_categoria = pc.id_categoria
            WHERE pc.id_producto = ?
        """;

        List<Categorias> categorias = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, idProducto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categorias.add(new Categorias(
                        rs.getLong("id_categoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getTimestamp("fecha_creacion")
                    ));
                }
            }
        }

        return categorias;
    }

    /**
     * Asocia un producto a una lista de categorías.
     * Primero elimina las asociaciones existentes, luego inserta las nuevas.
     *
     * @param idProducto ID del producto
     * @param categorias Lista de categorías a asociar
     */
    public void asociarCategorias(long idProducto, List<Categorias> categorias) {
        final String insertar = "INSERT INTO Productos_Categorias (id_producto, id_categoria) VALUES (?, ?)";

        try {
            // Inserta nuevas relaciones
            try (PreparedStatement stmt = conexion.prepareStatement(insertar)) {
                for (Categorias cat : categorias) {
                    stmt.setLong(1, idProducto);
                    stmt.setLong(2, cat.getId());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
