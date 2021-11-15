package br.com.alura.loja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId // Atributos que formam chave primária composta
    private CategoriaId id;

    public Categoria(String nome) {
        this.id = new CategoriaId(nome, "XPTO");
    }

    public String getNome(){
        return this.id.getNome();
    }
}
