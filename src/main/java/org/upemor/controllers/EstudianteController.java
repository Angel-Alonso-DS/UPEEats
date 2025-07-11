package org.upemor.controllers;

import org.upemor.models.entities.DetallePedido;
import org.upemor.models.repositories.DetallePedidoRepository;
import java.util.List;

import org.upemor.models.entities.Pedidos;
import org.upemor.models.entities.Sugerencias;
import org.upemor.models.repositories.PedidosRepository;
import org.upemor.models.repositories.SugerenciasRepository;

/**
 * Controlador para operaciones de estudiantes: registrar pedidos y sugerencias.
 */
public class EstudianteController {
    private final PedidosRepository pedidosRepository = new PedidosRepository();
    private final SugerenciasRepository sugerenciasRepository = new SugerenciasRepository();


    /**
     * Registra un nuevo pedido realizado por un estudiante, junto con sus detalles.
     * @param pedido objeto Pedidos con los datos del pedido
     * @param detalles lista de detalles de pedido (productos, cantidades, etc.)
     */
    public void registrarPedidoConDetalles(Pedidos pedido, List<DetallePedido> detalles) {
        pedidosRepository.insertar(pedido);
        // Se asume que el repositorio asigna el ID al objeto pedido (por ejemplo, usando getId() tras insertar)
        long idPedido = pedido.getId();
        DetallePedidoRepository detalleRepo = new DetallePedidoRepository();
        for (DetallePedido detalle : detalles) {
            detalle.setId(idPedido);
            detalleRepo.insertar(detalle);
        }
    }

    /**
     * Registra una nueva sugerencia realizada por un estudiante.
     * @param sugerencia objeto Sugerencias con los datos de la sugerencia
     */
    public void registrarSugerencia(Sugerencias sugerencia) {
        sugerenciasRepository.insertar(sugerencia);
    }
}
