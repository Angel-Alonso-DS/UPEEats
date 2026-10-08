package org.upemor.controllers;

import java.util.ArrayList;
import java.util.List;

import org.upemor.models.Sugerencias;
import org.upemor.models.Usuarios;
import org.upemor.repositories.SugerenciasRepositorio;

public class SugerenciasControlador {
    private final SugerenciasRepositorio SugerenciasRPS = new SugerenciasRepositorio();
    private final UsuariosControlador usuariosCTR = new UsuariosControlador();

    /**
     * Obtiene la lista de todas las sugerencias registradas.
     * @return Lista de sugerencias.
     */
    public List<Sugerencias> obtenerTodos() {
        return SugerenciasRPS.obtenerTodos();
    }

    /**
     * Busca una sugerencia por su ID.
     * @param id Identificador de la sugerencia.
     * @return Sugerencia encontrada o null si no existe.
     */
    public Sugerencias buscarPorId(long id) {
        return SugerenciasRPS.obtenerPorId(id);
    }
    /**
     * Busca los registros de las Sugerencias que tengan el id del usuario proporcionado
     * @param idUsuario id del usuario propietario de las sugerencias
     * @return Una lista de todos las sugerencias que realizo
     */
    public List<Sugerencias> obtenerTodoPorIdUsuario(long idUsuario) {
        if (usuariosCTR.isUsuarioActivo(idUsuario)) return SugerenciasRPS.obtenerTodoPorIdUsuario(idUsuario);

        return new ArrayList<>();
    }
    /**
     * Busca asuntos relacionados el nombre dado en la tabla de sugerencias.
     * Si el usuario esta desactivado o no exitse, o
     * en el caso de no enontrar alguna devuelve una lista vacia
     * @param nombre Nombre de la busqueda de asuntos
     * @param idUsuario id Del usuario que hace la consulta
     * @return Devuelve una lista de las sugerencias si son encontradas
     */
    public List<Sugerencias> buscarPorAsuntoConIdUsuario(long idUsuario, String asunto) {
        if (usuariosCTR.isUsuarioActivo(idUsuario)) return SugerenciasRPS.buscarPorAsuntoConIdUsuario(idUsuario, asunto);

        return new ArrayList<>();
    }

    public List<Sugerencias> buscarPorAsunto(String asunto) {
        return SugerenciasRPS.buscarPorAsunto(asunto);
    }

    /**
     * Inserta una nueva sugerencia en la base de datos.
     * @param sugerencia Sugerencia a insertar.
     */
    public void insertar(long idUsuario, String asunto, String comentarios) {
        Usuarios usuario = usuariosCTR.buscarPorId(idUsuario);
        Sugerencias sugerencia = new Sugerencias(0, usuario, asunto, comentarios, "", null, null);
        SugerenciasRPS.insertar(sugerencia);
    }

    /**
     * Actualiza una sugerencia existente en la base de datos.
     * 
     * @param sugerencia Sugerencia a actualizar
     * @param asunto Asunto actualizado
     * @param comentario Comentarios actualizado
     * @param estado Nuevo estado
     * Si se cambia de estado a 
     */
    public void actualizar(Sugerencias s, String asunto, String comentarios, String estado) {
        Sugerencias sugerencia = new Sugerencias(s.getId(), s.getUsuario(), asunto, comentarios, estado, null, null);
        SugerenciasRPS.actualizar(sugerencia);
    }

    public boolean marcarSugerenciaLeida(long idSugerencia) {
        boolean RES = SugerenciasRPS.actualizarCampo("Sugerencias", "estado", "revisada", idSugerencia, "id_sugerencia");
        if (!RES) return false;
        
        java.sql.Timestamp fecha = new java.sql.Timestamp(System.currentTimeMillis());

        RES = SugerenciasRPS.actualizarCampo("Sugerencias", "fecha_revision", fecha, idSugerencia, "id_sugerencia");
        return RES;
    }

    /**
     * Elimina una sugerencia de la base de datos por su ID.
     * @param id Identificador de la sugerencia a eliminar.
     */
    public void eliminar(long id) {
        SugerenciasRPS.eliminar(id);
    }
}