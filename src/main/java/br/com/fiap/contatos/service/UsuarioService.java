package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.UsuarioCadastroDto;
import br.com.fiap.contatos.dto.UsuarioExibicaoDto;
import br.com.fiap.contatos.exception.UsuarioNaoEncontradoException;
import br.com.fiap.contatos.model.Usuario;
import br.com.fiap.contatos.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto cadastrarUsuario (UsuarioCadastroDto usuarioCadastroDto){

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        usuario.setSenha(senhaCriptografada);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDto(usuarioRepository.save(usuarioSalvo));
    }

    public UsuarioExibicaoDto buscarPorId(Long id){

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            return new UsuarioExibicaoDto(usuarioOptional.get());
        }else {
            throw new UsuarioNaoEncontradoException("Usuário não existe no banco de dados!");
        }
    }

    public Page<UsuarioExibicaoDto> listarTodosOsUsuarios(Pageable paginacao){
        return usuarioRepository
                .findAll(paginacao)
                .map(UsuarioExibicaoDto::new);
    }

    public void excluirUsuario(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
        }else{
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

  /* public UsuarioExibicaoDto atualizarUsuario(@Valid @RequestBody UsuarioCadastroDto usuarioCadastroDto){

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            Usuario usuarioExistente = usuarioOptional.get();

            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setSenha(usuario.getSenha());
            usuarioExistente.setRole(usuario.getRole());

            usuarioExistente = usuarioRepository.save(usuarioExistente);

            return new UsuarioExibicaoDto(
                    usuarioExistente.getId(),
                    usuarioExistente.getNome(),
                    usuarioExistente.getEmail(),
                    usuarioExistente.getRole()
            );

        }else {
            throw new NoSuchElementException("Usuário não encontrado para o ID: " + id);
        }

    }*/

} //FIM
