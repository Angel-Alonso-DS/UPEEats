package org.upemor.views.cmd;

import org.upemor.controllers.SugerenciasController;
import org.upemor.models.entities.Sugerencias;
import java.util.List;
import java.util.Scanner;

public class SugerenciasViewCMD {
    private final SugerenciasController sugerenciasController = new SugerenciasController();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Sugerencias ---");
            System.out.println("1. Listar todas las sugerencias");
            System.out.println("2. Buscar sugerencia por ID");
            System.out.println("3. Agregar sugerencia");
            System.out.println("4. Editar sugerencia");
            System.out.println("5. Eliminar sugerencia");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    listarTodas();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    agregarSugerencia();
                    break;
                case 4:
                    editarSugerencia();
                    break;
                case 5:
                    eliminarSugerencia();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void agregarSugerencia() {
        System.out.print("ID de usuario: ");
        long idUsuario = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Tipo de dieta: ");
        String tipoDieta = scanner.nextLine();
        System.out.print("Alergias: ");
        String alergias = scanner.nextLine();
        System.out.print("Comentarios: ");
        String comentarios = scanner.nextLine();
        java.sql.Timestamp fecha = new java.sql.Timestamp(System.currentTimeMillis());
        org.upemor.controllers.UsuariosController usuariosController = new org.upemor.controllers.UsuariosController();
        org.upemor.models.entities.Usuarios usuario = usuariosController.buscarPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        Sugerencias sugerencia = new Sugerencias(0, usuario, tipoDieta, alergias, comentarios, fecha);
        sugerenciasController.insertar(sugerencia);
        System.out.println("Sugerencia agregada.");
    }

    private void editarSugerencia() {
        System.out.print("ID de la sugerencia a editar: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Sugerencias s = sugerenciasController.buscarPorId(id);
        if (s == null) {
            System.out.println("Sugerencia no encontrada.");
            return;
        }
        System.out.print("Nuevo ID de usuario (actual: " + s.getUsuario().getId() + "): ");
        String idUsuarioStr = scanner.nextLine();
        long idUsuario = idUsuarioStr.isEmpty() ? s.getUsuario().getId() : Long.parseLong(idUsuarioStr);
        org.upemor.controllers.UsuariosController usuariosController = new org.upemor.controllers.UsuariosController();
        org.upemor.models.entities.Usuarios usuario = usuariosController.buscarPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        System.out.print("Nuevo tipo de dieta (actual: " + s.getTipoDieta() + "): ");
        String tipoDieta = scanner.nextLine();
        if (tipoDieta.isEmpty()) tipoDieta = s.getTipoDieta();
        System.out.print("Nuevas alergias (actual: " + s.getAlergias() + "): ");
        String alergias = scanner.nextLine();
        if (alergias.isEmpty()) alergias = s.getAlergias();
        System.out.print("Nuevos comentarios (actual: " + s.getComentarios() + "): ");
        String comentarios = scanner.nextLine();
        if (comentarios.isEmpty()) comentarios = s.getComentarios();
        java.sql.Timestamp fecha = s.getFechaSugerencia();
        Sugerencias sugerencia = new Sugerencias(id, usuario, tipoDieta, alergias, comentarios, fecha);
        sugerenciasController.actualizar(sugerencia);
        System.out.println("Sugerencia actualizada.");
    }

    private void eliminarSugerencia() {
        System.out.print("ID de la sugerencia a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        sugerenciasController.eliminar(id);
        System.out.println("Sugerencia eliminada.");
    }

    private void listarTodas() {
        List<Sugerencias> sugerencias = sugerenciasController.obtenerTodos();
        System.out.println("\n--- Lista de Sugerencias ---");
        for (Sugerencias s : sugerencias) {
            System.out.println(s.getId() + " | Usuario: " + s.getId() + " | Comentarios: " + s.getComentarios());
        }
    }

    private void buscarPorId() {
        System.out.print("Ingrese el ID de la sugerencia: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Sugerencias sugerencia = sugerenciasController.buscarPorId(id);
        if (sugerencia != null) {
            System.out.println(sugerencia.getId() + " | Usuario: " + sugerencia.getId() + " | Comentarios: " + sugerencia.getComentarios());
        } else {
            System.out.println("Sugerencia no encontrada.");
        }
    }
}
