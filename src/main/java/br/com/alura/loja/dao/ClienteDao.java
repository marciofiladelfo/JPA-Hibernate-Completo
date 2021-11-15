package br.com.alura.loja.dao;

import br.com.alura.loja.model.Cliente;
import br.com.alura.loja.model.Pedido;

import javax.persistence.EntityManager;

public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }
//
//    public void atualizar(Produto produto) {
//        this.em.merge(produto);
//    }
//
//    public void remover(Produto produto) {
//        this.em.merge(produto);
//        this.em.remove(produto);
//    }
//
    public Cliente buscarPorId(Long id) {
        return this.em.find(Cliente.class, id);
    }
//
//    public List<Produto> buscarTodos() {
//        String sql = "SELECT p FROM Produto p";
//        return this.em.createQuery(sql, Produto.class).getResultList();
//    }
//
//    /* Parâmetro named parameter*/
//    public List<Produto> buscarPorNome(String nome) {
//        String sql = "SELECT p FROM Produto p WHERE p.nome = :nome";
//
//        return this.em.createQuery(sql, Produto.class)
//                .setParameter("nome", nome)
//                .getResultList();
//    }
//
//    /* Parâmetro value parameter*/
//    public List<Produto> buscarPorPreco(BigDecimal preco) {
//        String sql = "SELECT p FROM Produto p WHERE p.preco = ?1";
//
//        return this.em.createQuery(sql, Produto.class)
//                .setParameter(1, preco)
//                .getResultList();
//    }
//
//    public List<Produto> buscarPorCategoria(String nome) {
//        String sql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
//
//        return this.em.createQuery(sql, Produto.class)
//                .setParameter(1, nome)
//                .getResultList();
//    }
}
