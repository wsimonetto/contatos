package br.com.fiap.contatos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "tbl_contato")
public class Contato {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CONTATO"
    )
    @SequenceGenerator(
            name = "SEQ_CONTATO",
            sequenceName = "SEQ_CONTATO",
            allocationSize = 1
    )

    private Long id;

    private String nome;

    private String email;

    private String senha;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id) && Objects.equals(nome, contato.nome) && Objects.equals(email, contato.email) && Objects.equals(senha, contato.senha) && Objects.equals(dataNascimento, contato.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, dataNascimento);
    }
} // FIM
