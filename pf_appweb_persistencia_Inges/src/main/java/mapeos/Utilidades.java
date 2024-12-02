/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapeos;

import modelo.Genero;
import modelo.TipoUsuario;

/**
 *
 * @author af_da
 */
public class Utilidades {

    public static Genero stringToGenero(String generoStr) {
        switch (generoStr.toUpperCase()) {
            case "MASCULINO":
                return Genero.MASCULINO;
            case "FEMENINO":
                return Genero.FEMENINO;
            default:
                throw new IllegalArgumentException("Género inválido: " + generoStr);
        }
    }

    public static TipoUsuario stringToTipoUsuario(String tipoStr) {
        switch (tipoStr.toUpperCase()) {
            case "ADMOR":
                return TipoUsuario.ADMOR;
            case "NORMAL":
                return TipoUsuario.NORMAL;
            default:
                throw new IllegalArgumentException("Tipo de usuario inválido: " + tipoStr);
        }
    }
}
