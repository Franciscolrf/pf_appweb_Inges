package mapeos;

import dtos.UsuarioDTO;
import dtos.DireccionDTO;
import modelo.Usuario;
import modelo.Direccion;
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
        usuario.setTipoUsuario(usuarioDTO.getTipo()); 
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());

        if (usuarioDTO.getDireccion() != null) {
            Direccion direccion = dtoToDireccion(usuarioDTO.getDireccion());
            usuario.setDireccion(direccion); 
        }
        
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
        usuarioDTO.setTipo(usuario.getTipoUsuario());
        usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());

        if (usuario.getDireccion() != null) {
            DireccionDTO direccionDTO = direccionToDTO(usuario.getDireccion());
            usuarioDTO.setDireccion(direccionDTO);
        }
        
        return usuarioDTO;
    }

    public Direccion dtoToDireccion(DireccionDTO direccionDTO) {
        Direccion direccion = new Direccion();
        direccion.setId(direccionDTO.getId());
        direccion.setCalle(direccionDTO.getCalle());
        direccion.setNumero(direccionDTO.getNumero());
        direccion.setCiudad(direccionDTO.getCiudad());
        return direccion;
    }

    public DireccionDTO direccionToDTO(Direccion direccion) {
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setId(direccion.getId());
        direccionDTO.setCalle(direccion.getCalle());
        direccionDTO.setNumero(direccion.getNumero());
        direccionDTO.setCiudad(direccion.getCiudad());
        return direccionDTO;
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
