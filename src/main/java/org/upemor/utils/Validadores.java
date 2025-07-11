package org.upemor.utils;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Clase utilitaria para validaciones de datos de entrada en el sistema UPEEats.
 * Incluye métodos estáticos para validar nombres, apellidos, matrículas, correos, contraseñas, roles, etc.
 */
public class Validadores {
    /**
     * Valida que el nombre no sea nulo, vacío y tenga entre 3 y 50 caracteres.
     * @param nombre Nombre a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) return false;
        if (nombre.length() < 3 || nombre.length() > 50) return false;
        return true;
    }

    /**
     * Valida que el apellido no sea nulo, vacío y tenga entre 3 y 50 caracteres.
     * @param apellido Apellido a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) return false;
        if (apellido.length() < 3 || apellido.length() > 50) return false;
        return true;
    }
    
    /**
     * Valida que la matrícula no sea nula, vacía y tenga exactamente 10 caracteres.
     * @param matricula Matrícula a validar
     * @return true si es válida, false en caso contrario
     */
    public static boolean validarMatricula(String matricula) {
        if (matricula == null || matricula.isEmpty()) return false;
        if (matricula.length() != 10) return false;
        return true;
    }

    
    /**
     * Valida que el teléfono no sea nulo, vacío y tenga al menos 10 caracteres.
     * @param telefono Teléfono a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty()) return false;
        if (telefono.length() < 10 ) return false;
        return true;
    }

    
    /**
     * Valida que la URL de la imagen no sea nula, vacía y tenga al menos 10 caracteres.
     * @param imagenURL URL de la imagen a validar
     * @return true si es válida, false en caso contrario
     */
    public static boolean validarImagenURL(String imagenURL) {
        if (imagenURL == null || imagenURL.isEmpty()) return false;
        if (imagenURL.length() < 10) return false;
        return true;
    }
    
    /**
     * Valida que el correo tenga formato válido usando una expresión regular.
     * @param correo Correo a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarCorreo(String correo) {
        if (correo == null || correo.isEmpty()) return false;
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return correo.matches(regex);
    }
    
    /**
     * Valida que la contraseña no sea nula, vacía y tenga al menos 8 caracteres.
     * @param contrasenia Contraseña a validar
     * @return true si es válida, false en caso contrario
     */
    public static boolean validarContrasenia(String contrasenia) {
        if (contrasenia == null || contrasenia.isEmpty()) return false;
        if (contrasenia.length() < 8) return false;
        return true;
    }
    
    /**
     * Valida que el rol sea uno de los permitidos: Estudiante, Empleado o Administrador.
     * @param rol Rol a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarRol(String rol) {
        if (rol == null || rol.isEmpty()) return false;
        if (!rol.equalsIgnoreCase("Estudiante") && 
            !rol.equalsIgnoreCase("Empleado") && 
            !rol.equalsIgnoreCase("Administrador")
        ) return false;
        return true;
    }
    
    /**
     * Valida que el texto no sea nulo, vacío y tenga al menos 5 caracteres.
     * @param texto Texto a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarTexto(String texto) {
        if (texto == null || texto.isEmpty()) return false;
        if (texto.length() < 5) return false;
        return true;
    }
    
    /**
     * Valida que el costo no sea negativo.
     * @param costo Costo a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarCosto(double costo) {
        if (costo < 0) return false;
        return true;
    }

    /**
     * Valida que la fecha no sea nula.
     * @param fecha Fecha a validar
     * @return true si es válida, false en caso contrario
     */
    public static boolean validarFecha(Timestamp fecha) {
        if (fecha == null) return false;
        return true;
    }

    /**
     * Valida que el tiempo no sea nulo ni negativo.
     * @param tiempo Objeto Time a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarTiempo(Time tiempo) {
        if (tiempo == null) return false;
        if (tiempo.getTime() < 0) return false;
        return true;
    }

    /**
     * Valida que el estado sea uno de los permitidos: pendiente, preparando, listo, entregado o cancelado.
     * @param estado Estado a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarEstado(String estado) {
        if (estado == null || estado.isEmpty()) return false;

        if (!estado.equalsIgnoreCase("pendiente") && 
            !estado.equalsIgnoreCase("preparando") && 
            !estado.equalsIgnoreCase("listo") && 
            !estado.equalsIgnoreCase("entregado") &&
            !estado.equalsIgnoreCase("cancelado")
        ) return false;
        return true;
    }
}
