
package org.upemor.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.upemor.models.Pedidos;
import org.upemor.repositories.base.Repositorio;

/**
 * Repositorio para la gestión de operaciones CRUD sobre la entidad Pedidos.
 * <p>
 * Esta clase se encarga de mapear los datos entre la base de datos y el modelo Pedidos,
 * así como de ejecutar consultas específicas relacionadas con pedidos, como búsquedas por usuario o folio.
 * Hereda de la clase base Repositorio, que provee la estructura general para repositorios.
 */
public class PedidosRepositorio extends Repositorio<Pedidos> {

    /**
     * Repositorio auxiliar para obtener información de usuarios relacionada a los pedidos.
     */
    private static final UsuariosRepositorio usuariosRPS = new UsuariosRepositorio();


    /**
     * Inicializa las consultas SQL básicas para operaciones CRUD sobre la tabla Pedidos.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Pedidos (id_usuario, folio, tiempo_estimado, tiempo_entrega, estado, comentario, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        actualizarQuery = "UPDATE Pedidos SET tiempo_estimado=?, tiempo_entrega=?, estado=?, comentario=?, total=? WHERE id_pedido=?";
        eliminarQuery = "DELETE FROM Pedidos WHERE id_pedido=?";
        seleccionarTodoQuery = "SELECT * FROM Pedidos";
        seleccionarPorIdQuery = "SELECT * FROM Pedidos WHERE id_pedido=?";
    }


    /**
     * Mapea un ResultSet de la base de datos a un objeto Pedidos.
     * @param rs ResultSet con los datos de la consulta.
     * @return Objeto Pedidos con los datos mapeados.
     * @throws SQLException Si ocurre un error al acceder a los datos.
     */
    @Override
    protected Pedidos mapear(ResultSet rs) throws SQLException {
        return new Pedidos(
            rs.getLong("id_pedido"),
            usuariosRPS.obtenerPorId(rs.getLong("id_usuario")),
            rs.getString("folio"),
            rs.getString("tiempo_estimado"),
            rs.getString("tiempo_entrega"),
            rs.getString("estado"),
            rs.getString("comentario"),
            rs.getDouble("total"),
            rs.getTimestamp("fecha_pedido")
        );
    }


    /**
     * Prepara la sentencia SQL para insertar un nuevo pedido en la base de datos.
     * @param stmt PreparedStatement a preparar.
     * @param p Objeto Pedidos con los datos a insertar.
     * @throws SQLException Si ocurre un error al preparar la sentencia.
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, Pedidos p) throws SQLException {
        stmt.setLong(1, p.getUsuario().getId());
        stmt.setString(2, p.getFolio());
        stmt.setString(3, p.getTiempoEstimado());
        stmt.setString(4, p.getTiempoEntrega());
        stmt.setString(5, p.getEstado());
        stmt.setString(6, p.getComentario());
        stmt.setDouble(7, p.getTotal());
    }


    /**
     * Prepara la sentencia SQL para actualizar un pedido existente en la base de datos.
     * @param stmt PreparedStatement a preparar.
     * @param p Objeto Pedidos con los datos a actualizar.
     * @throws SQLException Si ocurre un error al preparar la sentencia.
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, Pedidos p) throws SQLException {
        stmt.setString(1, p.getTiempoEstimado());
        stmt.setString(2, p.getTiempoEntrega());
        stmt.setString(3, p.getEstado());
        stmt.setString(4, p.getComentario());
        stmt.setDouble(5, p.getTotal());
        stmt.setLong(6, p.getId());
    }

    /**
     * Actualiza el estado del pedido dado.
     * @param id id del pedido a actualizar
     * @param estado estado al que pasa
     */
    public void actualizarEstado(long id, String estado) {
        actualizarCampo("Pedidos", "estado", estado, id, "id_pedido");
    }


    /**
     * Busca todos los pedidos relacionados a un usuario.
     * @param idUsuario id del usuario relacionado
     * @return lista de pedidos
     */
    public List<Pedidos> buscarPorIdUsuario(long idUsuario) {
        final String sql = "SELECT * FROM Pedidos WHERE id_usuario = ?";
        return ejecutarConsulta(sql, new Object[] {idUsuario});
    }


    /**
     * Busca todos los pedidos que coincidan con el folio dado.
     * @param folio folio a buscar
     * @return lista de pedidos
     */
    public List<Pedidos> buscarPorFolio(String folio) {
        final String sql = "SELECT * FROM Pedidos WHERE LOWER(folio) LIKE ? ";
        return ejecutarConsulta(sql, new Object[] {"%" + folio + "%"});
    }

    /**
     * Busca todos los pedidos relacionados a un usuario y que coincidan con el folio dado.
     * @param idUsuario id del usuario relacionado
     * @param folio folio a buscar
     * @return lista de pedidos
     */
    public List<Pedidos> buscarPorIdUsuarioConFolio(long idUsuario, String folio) {
        final String sql = "SELECT * FROM Pedidos WHERE id_usuario = ? AND LOWER(folio) LIKE ? ";
        return ejecutarConsulta(sql, new Object[] {idUsuario, "%" + folio + "%"});
    }
}
