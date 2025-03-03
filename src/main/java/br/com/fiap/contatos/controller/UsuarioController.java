package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.dto.UsuarioCadastroDto;
import br.com.fiap.contatos.dto.UsuarioExibicaoDto;
import br.com.fiap.contatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        UsuarioExibicaoDto usuarioSalvo = null;
        usuarioSalvo = service.cadastrarUsuario(usuarioCadastroDto);
        return usuarioSalvo;
    }

    @GetMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarPeloId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDto> listarTodosOsContatos(Pageable paginacao) {
        return service.listarTodosOsUsuarios(paginacao);
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario(@PathVariable Long id) {
        service.excluirUsuario(id);
    }

    /*@PutMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto atualizarUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioCadastroDto usuarioCadastroDto) {
        return service.atualizarUsuario(usuarioCadastroDto);
    }*/

} //FIM
