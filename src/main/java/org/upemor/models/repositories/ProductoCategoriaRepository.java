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
}
