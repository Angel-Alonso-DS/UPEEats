package org.upemor.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.upemor.models.Categorias;
import org.upemor.models.Productos;
import org.upemor.repositories.base.Repositorio;

public class ProductosRepositorio extends Repositorio<Productos>{
    /**
     * Inicializa las consultas SQL para operaciones CRUD.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Productos (nombre_producto, imagen, descripcion, precio, tiempo_preparacion, disponible) VALUES (?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Productos SET nombre_producto=?, imagen=?, descripcion=?, precio=?, tiempo_preparacion=?, disponible=? WHERE id_producto=?";
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
            rs.getString("imagen"),
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
    stmt.setString(2, p.getImagen());
    stmt.setString(3, p.getDescripcion());
    stmt.setDouble(4, p.getPrecio());
    stmt.setString(5, p.getTiempoPreparacion());
    stmt.setBoolean(6, p.isDisponible());
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
        stmt.setLong(7, p.getId());
    }

    /**
     * Carga las categorías asociadas a un producto.
     * @param producto Producto al que se le cargarán las categorías
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void cargarRelaciones(Productos producto) throws SQLException {
        ProductoCategoriaRepositorio repo = new ProductoCategoriaRepositorio();
        List<Categorias> categorias = repo.obtenerCategoriasPorProducto(producto.getId());
        producto.setCategorias(categorias);
    }

    /**
     * Busca productos por nombre (coincidencia parcial, case-insensitive).
     * @param nombre nombre o parte del nombre a buscar
     * @return lista de productos que coinciden
     */
    public List<Productos> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM Productos WHERE LOWER(nombre_producto) LIKE ?";
        return ejecutarConsulta(sql, new Object[] {"%" + nombre.toLowerCase() + "%"});
    }

    /**
     * Busca productos por id de categoría.
     * @param idCategoria id de la categoría
     * @return lista de productos que pertenecen a la categoría
     */
    public List<Productos> buscarPorCategoria(long idCategoria) {
        final String sql = "SELECT p.* FROM Productos p " +
                     "JOIN Productos_Categorias pc ON p.id_producto = pc.id_producto " +
                     "WHERE pc.id_categoria = ?";
        
        return ejecutarConsulta(sql, new Object[] {idCategoria});
    }

    /**
     * Buscar los productos por una lista de categorias
     * @param idsCategorias Listado de las categorias relacionadas
     * @return Listado de los productos relacionados con las categorias
     */
    public List<Productos> buscarPorCategorias(List<Long> idsCategorias) {
        Object[] parametros = new Object[idsCategorias.size()];
        String marcadores = "";

        for (int i = 0; i < idsCategorias.size(); i++) {
            parametros[i] = idsCategorias.get(i);
            marcadores += "?";
            
            if (i < idsCategorias.size() - 1) marcadores += ",";
        }
        
        String sql = """
        SELECT DISTINCT p.*
        FROM Productos p
        JOIN Productos_Categorias pc ON p.id_producto = pc.id_producto
        WHERE pc.id_categoria IN (
        """ + 
         marcadores + 
        ")";

        return ejecutarConsulta(sql, parametros);
    }

    /**
     * Busca los productos que esten en el rango de precios
     * @param min Precio minimo
     * @param max Precio maximo
     * @return Lista de productos dentro del rango de precios
     */
    public List<Productos> buscarPorPrecio(double min, double max) {
        final String sql = "SELECT * FROM Productos WHERE precio BETWEEN ? AND ?";
        return ejecutarConsulta(sql, new Object[] {min, max});
    }

    /**
     * Busca los productos disponibles o no disponibles
     * @param disponible true si se buscan disponibles, false si se buscan inactivos
     * @return Listado de los productos
     */
    public List<Productos> buscarPorDisponibilidad(boolean disponible) {
        final String sql = "SELECT * FROM Productos WHERE disponible = ?";
        return ejecutarConsulta(sql, new Object[] {disponible});
    }
}
