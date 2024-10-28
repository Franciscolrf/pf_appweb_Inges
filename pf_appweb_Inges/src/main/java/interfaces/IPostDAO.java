/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import modelo.Post;

/**
 *
 * @author Fran
 */
public interface IPostDAO {
    
    void agregarPost(Post post);
    void modificarPost(Post post);
    void eliminarPost(Post post);
    Post consultarPost(int id);
}
