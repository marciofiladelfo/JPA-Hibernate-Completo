package br.com.alura.loja.model.produtos;

import br.com.alura.loja.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livro extends Produto {
    private String autor;
    private Integer numeroDePaginas;
}
