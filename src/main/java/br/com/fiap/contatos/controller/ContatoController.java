package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.dto.ContatoCadastroDto;
import br.com.fiap.contatos.dto.ContatoExibicaoDto;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoExibicaoDto gravar(@RequestBody @Valid ContatoCadastroDto contatoCadastroDto) {
        return service.gravar(contatoCadastroDto);
    }

    @GetMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarPeloId(@PathVariable Long id) {

        return service.buscarPeloId(id);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibicaoDto> listarTodosOsContatos(Pageable paginacao) {
        return service.listarTodosOsContatos(paginacao);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contato atualizar(@RequestBody Contato contato) {
        return service.atualizar(contato);
    }

  /*  @GetMapping("/contatos/{nome}")
    public ContatoExibicaoDto buscarContatoPeloNome(@PathVariable String nome) {
        return service.buscarContatoPeloNome(nome);
    }

    //api/contatos?nome=Wagner
   @GetMapping(value = "/contatos", params = "nome")
    public ContatoExibicaoDto buscarContatoPorNome(@RequestParam String nome) {
        return service.buscarContatoPeloNome(nome);
    }*/

    //api/contatos?dataInicio=2000-10-01&dataFinal=2000-10-31
    @GetMapping(value = "/contatos", params = {"dataInicio", "dataFinal"})
    public List<ContatoExibicaoDto> listarAniversariantesPorPeriodo(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFinal) {
        return service.listarAniversariantesPorPeriodo(dataInicio, dataFinal);
    }

    //api/contatos/?email=paula_silva@fiap.com.br
    @GetMapping(value = "/contatos", params = "email")
    public ContatoExibicaoDto buscarContatoPeloEmail(@RequestParam String email){
        return service.buscarContatoPeloEmail(email);
    }

} //FIM