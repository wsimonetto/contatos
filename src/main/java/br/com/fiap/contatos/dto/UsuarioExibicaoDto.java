package br.com.fiap.contatos.dto;

import br.com.fiap.contatos.model.Usuario;
import br.com.fiap.contatos.model.UsuarioRole;

public record UsuarioExibicaoDto(

        Long idUsuario,

        String nome,

        String email,

        UsuarioRole role

) {
    public UsuarioExibicaoDto (Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole());
    }
}
