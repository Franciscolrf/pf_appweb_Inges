package mapeos;

import dtos.UsuarioDTO;
import modelo.Usuario;
import modelo.Genero;
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

    public Genero stringToGenero(String generoStr) {
        switch (generoStr.toUpperCase()) {
            case "MASCULINO":
                return Genero.MASCULINO;
            case "FEMENINO":
                return Genero.FEMENINO;
            default:
                throw new IllegalArgumentException("Género inválido: " + generoStr);
        }
    }


    public TipoUsuario stringToTipoUsuario(String tipoStr) {
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
