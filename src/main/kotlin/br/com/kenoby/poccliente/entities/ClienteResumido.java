package br.com.kenoby.poccliente.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="cliente")
public class ClienteResumido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Length(min = 5, max = 50)
    @Column(length = 50, nullable = false)
    private String nome;


    public ClienteResumido() {
    }

    public ClienteResumido(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
