package org.upemor.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Filtro de entrada para campos de texto en Swing (JTextField, JPasswordField, etc.).
 * 
 * Permite restringir:
 * <ul>
 *     <li>El número máximo de caracteres.</li>
 *     <li>El tipo de caracteres permitidos (números, letras, correos, etc.).</li>
 *     <li>Evitar ciertos caracteres peligrosos para prevenir inyección SQL.</li>
 * </ul>
 * 
 * Uso recomendado junto con AbstractDocument:
 * <pre>
 * AbstractDocument doc = (AbstractDocument) campo.getDocument();
 * doc.setDocumentFilter(new FiltroEntradas(maxCaracteres, tipo));
 * </pre>
 * 
 * @author angelalonso
 */
public class FiltroEntradas extends DocumentFilter {
    /** Número máximo de caracteres permitidos en el campo */
    private int maxCaracteres;
    /** Tipo de validación que se aplicará sobre el texto ingresado */
    private int tipo;
    
    /** Solo dígitos del 0 al 9 */
    public static final int SOLO_NUMEROS = 1; 
    /** Solo decimales */
    public static final int SOLO_DECIMALES = 2;
    /** Solo letras (incluye acentos y ñ/Ñ) */
    public static final int SOLO_LETRAS = 3;
    /** Letras y números */
    public static final int LETRAS_NUMEROS = 4;
    /** Bloquea caracteres peligrosos y comandos SQL */
    public static final int NO_SQL = 5;

    /**
     * Constructor que inicializa el filtro con un límite de caracteres y un tipo de entrada.
     * 
     * @param maxCaracteres número máximo de caracteres permitidos
     * @param tipo tipo de validación a aplicar
     */
    public FiltroEntradas(int maxCaracteres, int tipo) {
        this.maxCaracteres = maxCaracteres;
        this.tipo = tipo;
    }

    /**
     * Método privado para validar si el texto cumple con el tipo de entrada permitido.
     * 
     * @param text texto a validar
     * @return true si el texto es válido, false si contiene caracteres no permitidos
     */
    private boolean validarEntrada(String texto) {
        if (texto == null) return true;

        switch (tipo) {
            case SOLO_NUMEROS:
                return texto.matches("\\d*");
            case SOLO_DECIMALES:
                return texto.matches("\\d*([\\.,]\\d*)?");
            case SOLO_LETRAS:
                return texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*");
            case LETRAS_NUMEROS:
                return texto.matches("[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ]*");
            case NO_SQL:
                return !texto.matches(".*(['\\\";]|--|(DROP|SELECT|INSERT|DELETE|UPDATE)).*");
            default:
                return true;
        }
    }

    /**
     * Método que se ejecuta cuando se intenta insertar texto en el campo.
     * 
     * @param fb permite interactuar con el documento subyacente
     * @param offset posición donde se insertará el texto
     * @param string texto a insertar
     * @param attr atributos del texto (generalmente estilos)
     * @throws BadLocationException si la posición de inserción no es válida
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        
        // Verifica que el texto sea válido según el tipo
        // y que no exceda el número máximo de caracteres
        if (validarEntrada(string) && fb.getDocument().getLength() + string.length() <= maxCaracteres) {
            super.insertString(fb, offset, string, attr);
        }
    }
    
    /**
     * Método que se ejecuta cuando se reemplaza texto existente.
     * 
     * @param fb permite interactuar con el documento subyacente
     * @param offset posición inicial donde se hará el reemplazo
     * @param length cantidad de caracteres a reemplazar
     * @param text nuevo texto que reemplazará al anterior
     * @param attrs atributos del texto
     * @throws BadLocationException si la posición no es válida
     */
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        
        // Verifica que el nuevo texto cumpla con las reglas
        // y que la longitud total final no exceda el máximo permitido
        if (validarEntrada(text) && fb.getDocument().getLength() - length + text.length() <= maxCaracteres) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
