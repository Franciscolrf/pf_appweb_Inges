/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.PostDTO;
import modelo.Post;

/**
 *
 * @author Fran
 */
public interface IPostDAO {
    
    boolean agregarPost(PostDTO post);
    void modificarPost(PostDTO post);
    void eliminarPost(PostDTO post);
    Post consultarPost(int id);
}
