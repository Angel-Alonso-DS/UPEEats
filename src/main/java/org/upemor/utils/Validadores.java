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
     * @return true si es válido, de caso contrario lanza una excepción
     */
    public static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");
        if (nombre.length() < 3) throw new IllegalArgumentException("El nombre es muy corto");
        if (nombre.length() > 50) throw new IllegalArgumentException("El nombre es demasiado largo");
        return true;
    }

    /**
     * Valida que el apellido no sea nulo, vacío y tenga entre 3 y 50 caracteres.
     * @param apellido Apellido a validar
     * @return true si es válido, de caso contrario lanza una excepción
     */
    public static boolean validarApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");
        if (apellido.length() < 3) throw new IllegalArgumentException("El apellido es muy corto");
        if (apellido.length() > 50) throw new IllegalArgumentException("El apellido es demasiado largo");
        return true;
    }
    
    /**
     * Valida que la matrícula no sea nula, vacía y tenga exactamente 10 caracteres.
     * @param matricula Matrícula a validar
     * @return true si es válida, de caso contrario lanza una excepción
     */
    public static boolean validarMatricula(String matricula) {
        if (matricula == null || matricula.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");
        if (matricula.length() != 10) throw new IllegalArgumentException("La matrícula debe tener 10 caracteres");
        return true;
    }

    
    /**
     * Valida que el teléfono no sea nulo, vacío y tenga al menos 10 caracteres.
     * @param telefono Teléfono a validar
     * @return true si es válido, de caso contrario lanza una excepción
     */
    public static boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");
        if (telefono.length() < 10 ) throw new IllegalArgumentException("El teléfono debe tener al menos 10 caracteres");
        return true;
    }

    
    /**
     * Valida que la URL de la imagen no sea nula, vacía y tenga al menos 10 caracteres.
     * @param imagenURL URL de la imagen a validar
     * @return true si es válida, de caso contrario lanza una excepción
     */
    public static boolean validarImagenURL(String imagenURL) {
        if (imagenURL == null || imagenURL.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");
        if (imagenURL.length() < 10) throw new IllegalArgumentException("La URL de la imagen es inválida");
        return true;
    }
    
    /**
     * Valida que el correo tenga formato válido usando una expresión regular.
     * @param correo Correo a validar
     * @return true si es válido, de caso contrario lanza una excepción
     */
    public static boolean validarCorreo(String correo) {
        if (correo == null || correo.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");

        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!correo.matches(regex)) throw new IllegalArgumentException("El correo es inválido");

        return true;
    }
    
    /**
     * Valida que la contraseña no sea nula, vacía y tenga al menos 8 caracteres.
     * @param contrasenia Contraseña a validar
     * @return true si es válida, de caso contrario lanza una excepción
     */
    public static boolean validarContrasenia(String contrasenia) {
        if (contrasenia == null || contrasenia.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");
        if (contrasenia.length() < 8) throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        return true;
    }
    
    /**
     * Valida que el rol sea uno de los permitidos: Estudiante, Empleado o Administrador.
     * @param rol Rol a validar
     * @return true si es válido, de caso contrario lanza una excepción
     */
    public static boolean validarRol(String rol) {
        if (rol == null || rol.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");
        if (!rol.equalsIgnoreCase("Estudiante") && 
            !rol.equalsIgnoreCase("Empleado") && 
            !rol.equalsIgnoreCase("Administrador")
        ) throw new IllegalArgumentException("Rol no válido");
        return true;
    }
    
    /**
     * Valida que el texto no sea nulo, vacío y tenga al menos 5 caracteres.
     * @param texto Texto a validar
     * @return true si es válido, de caso contrario lanza una excepción
     */
    public static boolean validarTexto(String texto) {
        if (texto == null || texto.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");
        if (texto.length() < 5) throw new IllegalArgumentException("El texto es demasiado corto");
        return true;
    }
    
    /**
     * Valida que el costo no sea negativo.
     * @param costo Costo a validar
     * @return true si es válido, de caso contrario lanza una excepción
     */
    public static boolean validarCosto(double costo) {
        if (costo < 1 || costo > 1000) throw new IllegalArgumentException("El costo es inválido");
        return true;
    }

    /**
     * Valida que la fecha no sea nula.
     * @param fecha Fecha a validar
     * @return true si es válida, de caso contrario lanza una excepción
     */
    public static boolean validarFecha(Timestamp fecha) {
        if (fecha == null) throw new IllegalArgumentException("No dejes campos vacios");
        return true;
    }

    /**
     * Valida que el tiempo no sea nulo ni negativo.
     * @param tiempo Objeto Time a validar
     * @return true si es válido, de caso contrario lanza una excepción
     */
    public static boolean validarTiempo(Time tiempo) {
        if (tiempo == null) throw new IllegalArgumentException("No dejes campos vacios");
        if (tiempo.getTime() < 0) throw new IllegalArgumentException("El tiempo no puede ser negativo");
        return true;
    }

    /**
     * Valida que el estado sea uno de los permitidos: pendiente, preparando, listo, entregado o cancelado.
     * @param estado Estado a validar
     * @return true si es válido, de caso contrario lanza una excepción
     */
    public static boolean validarEstado(String estado) {
        if (estado == null || estado.isEmpty()) throw new IllegalArgumentException("No dejes campos vacios");

        if (!estado.equalsIgnoreCase("pendiente") && 
            !estado.equalsIgnoreCase("preparando") && 
            !estado.equalsIgnoreCase("listo") && 
            !estado.equalsIgnoreCase("entregado") &&
            !estado.equalsIgnoreCase("cancelado")
        ) throw new IllegalArgumentException("Estado no válido");
        return true;
    }
}
