package br.com.alura.loja.dao;

import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.em.merge(categoria);
    }

    public void remover(Categoria categoria) {
        this.em.merge(categoria);
        this.em.remove(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return this.em.find(Categoria.class, id);
    }

    public List<Categoria> buscarTodos() {
        String sql = "SELECT p FROM Produto p";
        return this.em.createQuery(sql, Categoria.class).getResultList();
    }
}
