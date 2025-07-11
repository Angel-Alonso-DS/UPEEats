package org.upemor.models.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.upemor.models.Repository;
import org.upemor.models.entities.Categorias;
import org.upemor.models.entities.Productos;

/**
 * Repositorio para operaciones CRUD y consultas sobre la entidad Productos.
 * Permite cargar relaciones con categorías.
 */
public class ProductoRepository extends Repository<Productos> {

    /**
     * Inicializa las consultas SQL para operaciones CRUD.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Productos (nombre_producto, imagenURL, descripcion, precio, tiempo_preparacion, disponible, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Productos SET nombre_producto=?, imagenURL=?, descripcion=?, precio=?, tiempo_preparacion=?, disponible=?, fecha_registro=? WHERE id_producto=?";
        eliminarQuery = "DELETE FROM Productos WHERE id_producto=?";
        seleccionarTodoQuery = "SELECT * FROM Productos";
        seleccionarPorIdQuery = "SELECT * FROM Productos WHERE id_producto=?";
    }

    /**
     * Mapea un ResultSet a un objeto Productos.
     * @param rs ResultSet de la consulta
     * @return Objeto Productos
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected Productos mapear(ResultSet rs) throws SQLException {
        return new Productos(
            rs.getLong("id_producto"),
            rs.getString("nombre_producto"),
            rs.getString("imagenURL"),
            rs.getString("descripcion"),
            rs.getDouble("precio"),
            rs.getString("tiempo_preparacion"),
            rs.getBoolean("disponible"),
            rs.getTimestamp("fecha_registro"),
            new ArrayList<>()
        );
    }

    /**
     * Prepara la sentencia para insertar un producto.
     * @param stmt PreparedStatement
     * @param p Producto a insertar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, Productos p) throws SQLException {
        stmt.setString(1, p.getNombreProducto());
        stmt.setString(2, p.getImagenUrl());
        stmt.setString(3, p.getDescripcion());
        stmt.setDouble(4, p.getPrecio());
        stmt.setString(5, p.getTiempoPreparacion());
        stmt.setBoolean(6, p.isDisponible());
        stmt.setTimestamp(7, p.getFechaRegistro());
    }

    /**
     * Prepara la sentencia para actualizar un producto.
     * @param stmt PreparedStatement
     * @param p Producto a actualizar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, Productos p) throws SQLException {
        prepararInsert(stmt, p);
        stmt.setLong(8, p.getId());
    }

    /**
     * Carga las categorías asociadas a un producto.
     * @param producto Producto al que se le cargarán las categorías
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void cargarRelaciones(Productos producto) throws SQLException {
        ProductoCategoriaRepository repo = new ProductoCategoriaRepository();
        List<Categorias> categorias = repo.obtenerCategoriasPorProducto(producto.getId());
        producto.setCategorias(categorias);
    }
    /**
     * Busca productos por nombre (coincidencia parcial, case-insensitive).
     * @param nombre nombre o parte del nombre a buscar
     * @return lista de productos que coinciden
     */
    public List<Productos> buscarPorNombre(String nombre) {
        List<Productos> lista = new ArrayList<>();
        String sql = "SELECT * FROM Productos WHERE LOWER(nombre_producto) LIKE ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre.toLowerCase() + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Productos producto = mapear(rs);
                    cargarRelaciones(producto);
                    lista.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Busca productos por id de categoría.
     * @param idCategoria id de la categoría
     * @return lista de productos que pertenecen a la categoría
     */
    public List<Productos> buscarPorCategoria(long idCategoria) {
        List<Productos> lista = new ArrayList<>();
        String sql = "SELECT p.* FROM Productos p " +
                     "JOIN Productos_Categorias pc ON p.id_producto = pc.id_producto " +
                     "WHERE pc.id_categoria = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, idCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Productos producto = mapear(rs);
                    cargarRelaciones(producto);
                    lista.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Devuelve el último ID insertado en la tabla Productos (SQLite)
    public long obtenerUltimoIdInsertado() {
        long id = 0;
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() as id")) {
            if (rs.next()) {
                id = rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
