package org.upemor.models.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.upemor.models.Repository;
import org.upemor.models.entities.Categorias;
import org.upemor.models.entities.Productos;

public class ProductoRepository extends Repository<Productos> {

    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Productos (nombre_producto, imagenURL, descripcion, precio, tiempo_preparacion, disponible, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Productos SET nombre_producto=?, imagenURL=?, descripcion=?, precio=?, tiempo_preparacion=?, disponible=?, fecha_registro=? WHERE id_producto=?";
        eliminarQuery = "DELETE FROM Productos WHERE id_producto=?";
        seleccionarTodoQuery = "SELECT * FROM Productos";
        seleccionarPorIdQuery = "SELECT * FROM Productos WHERE id_producto=?";
    }

    @Override
    protected Productos mapear(ResultSet rs) throws SQLException {
        return new Productos(
            rs.getLong("id_producto"),
            rs.getString("nombre_producto"),
            rs.getString("imagenURL"),
            rs.getString("descripcion"),
            rs.getDouble("precio"),
            rs.getTime("tiempo_preparacion"),
            rs.getBoolean("disponible"),
            rs.getTimestamp("fecha_registro"),
            new ArrayList<>()
        );
    }

    @Override
    protected void prepararInsert(PreparedStatement stmt, Productos p) throws SQLException {
        stmt.setString(1, p.getNombreProducto());
        stmt.setString(2, p.getImagenURL());
        stmt.setString(3, p.getDescripcion());
        stmt.setDouble(4, p.getPrecio());
        stmt.setTime(5, p.getTiempoPreparacion());
        stmt.setBoolean(6, p.isDisponible());
        stmt.setTimestamp(7, p.getFechaRegistro());
    }

    @Override
    protected void prepararActualizar(PreparedStatement stmt, Productos p) throws SQLException {
        prepararInsert(stmt, p);
        stmt.setLong(8, p.getId());
    }

    @Override
    protected void cargarRelaciones(Productos producto) throws SQLException {
        ProductoCategoriaRepository repo = new ProductoCategoriaRepository();
        List<Categorias> categorias = repo.obtenerCategoriasPorProducto(producto.getId());
        producto.setCategorias(categorias);
    }
}
