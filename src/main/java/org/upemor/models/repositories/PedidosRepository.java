/**
 * Repositorio para operaciones CRUD sobre la entidad Pedidos.
 * Permite mapear, insertar y actualizar pedidos.
 */
package org.upemor.models.repositories;

import java.sql.*;

import org.upemor.models.Repository;
import org.upemor.models.entities.Pedidos;

public class PedidosRepository extends Repository<Pedidos> {
    private UsuarioRepository ur;

    public PedidosRepository() {
        ur = new UsuarioRepository();
    }

    /**
     * Inicializa las consultas SQL para operaciones CRUD.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Pedidos (id_usuario, tiempo_estimado, tiempo_entrega, estado, comentario, total, fecha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Pedidos SET id_usuario=?, tiempo_estimado=?, tiempo_entrega=?, estado=?, comentario=?, total=?, fecha=? WHERE id_pedido=?";
        eliminarQuery = "DELETE FROM Pedidos WHERE id_pedido=?";
        seleccionarTodoQuery = "SELECT * FROM Pedidos";
        seleccionarPorIdQuery = "SELECT * FROM Pedidos WHERE id_pedido=?";
    }

    /**
     * Mapea un ResultSet a un objeto Pedidos.
     * @param rs ResultSet de la consulta
     * @return Objeto Pedidos
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected Pedidos mapear(ResultSet rs) throws SQLException {
        return new Pedidos(
            rs.getLong("id_pedido"),
            ur.obtenerPorId(rs.getLong("id_usuario")),
            new java.sql.Timestamp(rs.getLong("tiempo_estimado")),
            new java.sql.Timestamp(rs.getLong("tiempo_entrega")),
            rs.getString("estado"),
            rs.getString("comentario"),
            rs.getDouble("total"),
            new java.sql.Timestamp(rs.getLong("fecha"))
        );
    }

    /**
     * Prepara la sentencia para insertar un pedido.
     * @param stmt PreparedStatement
     * @param p Pedido a insertar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, Pedidos p) throws SQLException {
        stmt.setObject(1, p.getUsuario());
        stmt.setLong(2, p.getTiempoEstimado().getTime());
        stmt.setLong(3, p.getTiempoEntrega().getTime());
        stmt.setString(4, p.getEstado());
        stmt.setString(5, p.getComentario());
        stmt.setDouble(6, p.getTotal());
        stmt.setLong(7, p.getFecha().getTime());
    }

    /**
     * Prepara la sentencia para actualizar un pedido.
     * @param stmt PreparedStatement
     * @param p Pedido a actualizar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, Pedidos p) throws SQLException {
        prepararInsert(stmt, p);
        stmt.setLong(8, p.getId());
    }
    /**
     * Obtiene el último id insertado en la tabla Pedidos.
     * @return id del último pedido insertado
     */
    public long obtenerUltimoIdInsertado() {
        long id = -1;
        try (Statement stmt = this.conexion.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid();")) {
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}