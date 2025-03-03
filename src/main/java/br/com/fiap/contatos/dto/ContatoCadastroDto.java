package br.com.fiap.contatos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ContatoCadastroDto(
        Long id,

        @NotBlank(message = "Nome do contato é obrigatório!")
        String nome,

        @NotBlank(message = "Email é obrigatório!")
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "O e-mail esta com formato incorreto!")
        String email,

        @NotBlank(message = "Senha é obrigatório!")
        @Size(min = 6, max = 10, message = "A senha deve conter entre 6 e 10 caracteres!")
        String senha,

        @NotNull(message = "Data de nascimento é obrigatória")
        LocalDate dataNascimento
) {
    // NÃO PRECISA DO CONSTRUTOR

} //FIM
