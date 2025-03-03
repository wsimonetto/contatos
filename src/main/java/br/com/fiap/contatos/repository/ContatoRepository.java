package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    //public Optional<Contato> findByNome(String nome);

    //public List<Contato> findByDataNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal);

    @Query("SELECT c FROM Contato c WHERE c.nome = :nome")
    Optional<Contato> buscarContatoPeloNome(@Param("nome") String nome);

    Optional<Contato> findByEmail(String email);

   @Query("SELECT c FROM Contato c WHERE c.dataNascimento BETWEEN :dataInicial AND :dataFinal")
    List<Contato> listarAniversariantesPorPeriodo(
            @Param("dataInicial") LocalDate dataInicial,
            @Param("dataFinal") LocalDate dataFinal
    );

} //FIM
