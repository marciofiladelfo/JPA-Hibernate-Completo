package br.com.alura.loja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private  DadosPessoais dadosPessoais;

    public Cliente(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }

    /** Método delegate de atribuição */
    public String getNome(){
        return this.dadosPessoais.getNome();
    }

    /** Método delegate de atribuição */
    public String getCpf(){
        return this.dadosPessoais.getCpf();
    }
}
