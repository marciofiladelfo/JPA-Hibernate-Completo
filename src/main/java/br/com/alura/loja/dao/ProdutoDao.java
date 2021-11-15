package br.com.alura.loja.dao;

import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualizar(Produto produto) {
        this.em.merge(produto);
    }

    public void remover(Produto produto) {
        this.em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(Long id) {
        return this.em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String sql = "SELECT p FROM Produto p";
        return this.em.createQuery(sql, Produto.class).getResultList();
    }

    /* Parâmetro named parameter*/
    public List<Produto> buscarPorNome(String nome) {
        String sql = "SELECT p FROM Produto p WHERE p.nome = :nome";

        return this.em.createQuery(sql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    /* Parâmetro value parameter*/
    public List<Produto> buscarPorPreco(BigDecimal preco) {
        String sql = "SELECT p FROM Produto p WHERE p.preco = ?1";

        return this.em.createQuery(sql, Produto.class)
                .setParameter(1, preco)
                .getResultList();
    }

    public List<Produto> buscarPorCategoria(String nome) {
        String sql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";

        return this.em.createQuery(sql, Produto.class)
                .setParameter(1, nome)
                .getResultList();
    }

    /** Consulta usando criteria */
    public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = criteriaBuilder.and();

        if (nome != null && !nome.trim().isEmpty()){
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("nome"), nome));
        }
        if (preco != null){
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("preco"), preco));
        }
        if (dataCadastro != null){
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);
        return em.createQuery(query).getResultList();
    }
}
