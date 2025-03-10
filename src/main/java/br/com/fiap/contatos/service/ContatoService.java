package br.com.fiap.contatos.service;

import br.com.fiap.contatos.dto.ContatoCadastroDto;
import br.com.fiap.contatos.dto.ContatoExibicaoDto;
import br.com.fiap.contatos.exception.UsuarioNaoEncontradoException;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public ContatoExibicaoDto gravar(ContatoCadastroDto contatoCadastroDto){
        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoCadastroDto, contato);
        return new ContatoExibicaoDto(contatoRepository.save(contato));
    }

    public ContatoExibicaoDto buscarPeloId(Long id){

        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()){

            return new ContatoExibicaoDto(contatoOptional.get());
        }else {

            throw new UsuarioNaoEncontradoException("Contato não existe!");
        }
    }

    public Page<ContatoExibicaoDto> listarTodosOsContatos(Pageable paginacao){
        return contatoRepository
                .findAll(paginacao)
                .map(ContatoExibicaoDto::new);
    }

    public void excluir(Long id){
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()){
            contatoRepository.delete(contatoOptional.get());
        }else{
            throw new RuntimeException("Contato não encontrado!");
        }
    }

   /* public List<Contato> mostrarAniversariamentes(LocalDate dataInicial, LocalDate dataFinal){
        return contatoRepository.findByDataNascimentoBetween(dataInicial, dataFinal);
    }*/

    public Contato atualizar(Contato contato){
        Optional<Contato> contatoOptional = contatoRepository.findById(contato.getId());

        if (contatoOptional.isPresent()){
            return contatoRepository.save(contato);
        }else{
            throw new RuntimeException("Contato não encontrado!");
        }
    }

   /* public Contato buscarPeloNome(String nome){

        Optional<Contato> contatoOptional = contatoRepository.findByNome(nome);

        if (contatoOptional.isPresent()){
            return contatoOptional.get();
        }else{
            throw new RuntimeException("Contato não encontrado!");
        }
    }*/

    public ContatoExibicaoDto buscarContatoPeloNome(String nome) {

        Optional<Contato> contatoOptional = contatoRepository.buscarContatoPeloNome(nome);

        if (contatoOptional.isPresent()) {
                return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new RuntimeException("Contato não encontrado!");
        }
    }

    public List<ContatoExibicaoDto> listarAniversariantesPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){

        return contatoRepository
                .listarAniversariantesPorPeriodo(dataInicial, dataFinal)
                .stream()
                .map(ContatoExibicaoDto::new)
                .collect(Collectors.toList());
    }

    public ContatoExibicaoDto buscarContatoPeloEmail(String email){
        Optional<Contato> contatoOptional = contatoRepository.findByEmail(email);
        if (contatoOptional.isPresent()){
            return new ContatoExibicaoDto(contatoOptional.get());
        }else {
            throw new UsuarioNaoEncontradoException("Contato não encontrado");
        }
    }

} //FIM
