package org.upemor.models.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.upemor.models.entities.Categorias;
import org.upemor.utils.BDConexion;

public class ProductoCategoriaRepository {
    private final Connection conexion;

    public ProductoCategoriaRepository() {
        this.conexion = BDConexion.getInstance().getConexion();
    }

    public List<Categorias> obtenerCategoriasPorProducto(long idProducto) throws SQLException {
        String sql = """
            SELECT c.* FROM Categorias c
            JOIN Productos_Categorias pc ON c.id_categoria = pc.id_categoria
            WHERE pc.id_producto = ?
        """;

        List<Categorias> categorias = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categorias.add(new Categorias(
                    rs.getLong("id_categoria"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                ));
            }
        }
        return categorias;
    }

    // Asocia un producto con varias categorías (elimina las previas y agrega las nuevas)
    public void asociarCategorias(long idProducto, List<Categorias> categorias) {
        try {
            // Elimina relaciones previas
            String delete = "DELETE FROM Productos_Categorias WHERE id_producto = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(delete)) {
                stmt.setLong(1, idProducto);
                stmt.executeUpdate();
            }
            // Inserta nuevas relaciones
            String insert = "INSERT INTO Productos_Categorias (id_producto, id_categoria) VALUES (?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(insert)) {
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
