package br.uel.ExercicioAPI.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "contatos")
@JsonPropertyOrder({ "id", "nome", "telefone", "email", "endereco", "dataNascimento" })
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "O nome não pode estar vazio!")
    @Size(max = 100, message = "Nome muito longo!")
    private String nome;

    @NotBlank(message = "O telefone não pode estar vazio!")
    @Size(max = 50, message = "Telefone muito longo!")
    private String telefone;

    @NotBlank(message = "O email não pode estar vazio!")
    @Size(max = 150, message = "Email muito longo!")
    @Email(message = "O email deve ser válido!")
    private String email;

    @Size(max = 150, message = "Endereço muito longo!")
    private String endereco;

    @NotNull(message = "A data de nascimento não pode estar vazia!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;


    public Long getId() { return id;}
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getEndereco() { return endereco; }
    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
