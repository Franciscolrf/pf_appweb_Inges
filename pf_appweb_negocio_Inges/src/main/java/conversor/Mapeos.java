package conversor;

import dtos.ComentarioDTO;
import dtos.PostDTO;
import dtos.UsuarioDTO;
import modelo.Comentario;
import modelo.Usuario;
import modelo.Genero;
import modelo.Post;
import modelo.TipoUsuario;

public class Mapeos {

    public Usuario dtoToUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setAvatar(usuarioDTO.getAvatar());
        usuario.setContrasenia(usuarioDTO.getContrasenia());
        usuario.setGenero(usuarioDTO.getGenero()); 
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario()); 
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuario.setDireccion(usuarioDTO.getDireccion());
        
        
        return usuario;
    }

    public UsuarioDTO usuarioToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombreCompleto(usuario.getNombreCompleto());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setAvatar(usuario.getAvatar());
        usuarioDTO.setContrasenia(usuario.getContrasenia());
        usuarioDTO.setGenero(usuario.getGenero());
        usuarioDTO.setTipoUsuario(usuario.getTipoUsuario());
        usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioDTO.setDireccion(usuario.getDireccion());
        
        return usuarioDTO;
    }



    // Convertir Entidad Post a DTO
    public PostDTO entidadToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitulo(post.getTitulo());
        postDTO.setContenido(post.getContenido());
        postDTO.setAnclado(post.isAnclado());
        postDTO.setFechaHoraCreacion(post.getFechaHoraCreacion());
        postDTO.setFechaHoraEdicion(post.getFechaHoraEdicion());

        if (post.getUsuario() != null) {
            UsuarioDTO usuarioDTO = usuarioToDTO(post.getUsuario());
            postDTO.setUsuario(usuarioDTO);
        }
        return postDTO;
    }
    
     // Convertir DTO Post a Entidad
    public Post dtoToEntidad(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitulo(postDTO.getTitulo());
        post.setContenido(postDTO.getContenido());
        post.setAnclado(postDTO.isAnclado());
        post.setFechaHoraCreacion(postDTO.getFechaHoraCreacion());
        post.setFechaHoraEdicion(postDTO.getFechaHoraEdicion());

        if (postDTO.getUsuario() != null) {
            Usuario usuario = dtoToUsuario(postDTO.getUsuario());
            post.setUsuario(usuario);
        }
        return post;
    }
    
    // Convertir Entidad Comentario a DTO
    public ComentarioDTO comentarioEntidadToDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setContenido(comentario.getContenido());
        comentarioDTO.setFechaHora(comentario.getFechaHora());

        if (comentario.getUsuario() != null) {
            UsuarioDTO usuarioDTO = usuarioToDTO(comentario.getUsuario());
            comentarioDTO.setUsuario(usuarioDTO);
        }
        return comentarioDTO;
    }

    // Convertir DTO Comentario a Entidad
    public Comentario comentarioDtoToEntidad(ComentarioDTO comentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setContenido(comentarioDTO.getContenido());
        comentario.setFechaHora(comentarioDTO.getFechaHora());

        if (comentarioDTO.getUsuario() != null) {
            Usuario usuario = dtoToUsuario(comentarioDTO.getUsuario());
            comentario.setUsuario(usuario);
        }
        return comentario;
    }
}
