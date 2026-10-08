package org.upemor.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.upemor.models.Notificaciones;
import org.upemor.models.Pedidos;
import org.upemor.repositories.base.Repositorio;


/**
 * Repositorio para la gestión de operaciones CRUD sobre la entidad Notificaciones.
 * <p>
 * Esta clase se encarga de mapear los datos entre la base de datos y el modelo Notificaciones,
 * así como de ejecutar consultas específicas relacionadas con notificaciones, como búsquedas por usuario o mensaje.
 * Hereda de la clase base Repositorio, que provee la estructura general para repositorios.
 */
public class NotificacionesRespositorio extends Repositorio<Notificaciones> {

    /**
     * Repositorio auxiliar para obtener información de usuarios relacionada a las notificaciones.
     */
    private static final UsuariosRepositorio usuariosRPS = new UsuariosRepositorio();

    /**
     * Repositorio auxiliar para obtener información de pedidos relacionada a las notificaciones.
     */
    private static final PedidosRepositorio pedidosRPS = new PedidosRepositorio();



    /**
     * Inicializa las consultas SQL básicas para operaciones CRUD sobre la tabla Notificaciones.
     */
    @Override
    protected void inicializarQueries() {
        insertarQuery = "INSERT INTO Notificaciones (id_usuario, id_pedido, mensaje) VALUES (?, ?, ?)";
        actualizarQuery = "UPDATE Notificaciones SET id_usuario=?, id_pedido=?, mensaje=? WHERE id_notificacion=?";
        eliminarQuery = "DELETE FROM Notificaciones WHERE id_notificacion=?";
        seleccionarTodoQuery = "SELECT * FROM Notificaciones";
        seleccionarPorIdQuery = "SELECT * FROM Notificaciones WHERE id_notificacion=?";
    }


    /**
     * Mapea un ResultSet de la base de datos a un objeto Notificaciones.
     * @param rs ResultSet de la consulta
     * @return Objeto Notificaciones
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected Notificaciones mapear(ResultSet rs) throws SQLException {
        return new Notificaciones(
            rs.getLong("id_notificacion"),
            usuariosRPS.obtenerPorId(rs.getLong("id_usuario")),
            pedidosRPS.obtenerPorId(rs.getLong("id_pedido")),
            rs.getString("mensaje"),
            rs.getTimestamp("fecha_creacion")
        );
    }


    /**
     * Prepara la sentencia SQL para insertar una notificación en la base de datos.
     * @param stmt PreparedStatement
     * @param n Notificaciones a insertar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararInsert(PreparedStatement stmt, Notificaciones n) throws SQLException {
        stmt.setLong(1, n.getUsuario().getId());
        stmt.setLong(2, n.getPedido().getId());
        stmt.setString(3, n.getMensaje());
    }


    /**
     * Prepara la sentencia SQL para actualizar una notificación existente en la base de datos.
     * @param stmt PreparedStatement
     * @param n Notificaciones a actualizar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    protected void prepararActualizar(PreparedStatement stmt, Notificaciones n) throws SQLException {
        prepararInsert(stmt, n);
        stmt.setLong(5, n.getId());
    }


    /**
     * Busca todas las notificaciones que estén relacionadas a un usuario.
     * @param idUsuario id del usuario relacionado
     * @return lista de notificaciones
     */
    public List<Notificaciones> buscarPorIdUsuario(long idUsuario) {
        final String sql = "SELECT * FROM Notificaciones WHERE id_usuario = ?";
        return ejecutarConsulta(sql, new Object[] {idUsuario});
    }


    /**
     * Genera y almacena una notificación para un pedido específico.
     * El mensaje se construye automáticamente con el folio, estado y tiempo estimado del pedido.
     * @param pedido Pedido para el cual se genera la notificación
     */
    public void notificar(Pedidos pedido) {
        String mensaje = "El pedido " + 
            pedido.getFolio() + 
            " esta " + pedido.getEstado() + 
            ". Tiempo estimado: " + pedido.getTiempoEstimado();
        
        Notificaciones notificacion = new Notificaciones(0, pedido.getUsuario(), pedido, mensaje, null);
        insertar(notificacion);
    }

    /**
     * Busca todas las notificaciones que estén relacionadas a un usuario y que coincidan con el mensaje dado.
     * @param idUsuario id del usuario relacionado
     * @param mensaje mensaje a buscar
     * @return lista de notificaciones
     */
    public List<Notificaciones> buscarPorIdUsuarioConMensaje(long idUsuario, String mensaje) {
        final String sql = "SELECT * FROM Notificaciones WHERE id_usuario = ? AND LOWER(mensaje) LIKE ?";
        return ejecutarConsulta(sql, new Object[] {idUsuario, "%" + mensaje.toLowerCase() + "%"});
    }
}
