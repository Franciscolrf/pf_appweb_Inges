/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import modelo.Direccion;

/**
 *
 * @author Fran
 */
public interface IDireccionDAO {
    
    void agregarDireccion(Direccion direccion);
    void modificarDireccion(Direccion direccion);
    void eliminarDireccion(Direccion direccion);

}
