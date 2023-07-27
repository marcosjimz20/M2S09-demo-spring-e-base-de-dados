package br.senai.lab365.demospringbd.DTO;

public class PessoaDto {
// Atributos
    private String nome;
    private String email;
    private Boolean status;

// Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


// FIM da classe PessoaDto
}
