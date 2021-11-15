package br.com.alura.loja.dao;

import br.com.alura.loja.model.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {
    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    /**
     * Metodo que faz a somatória de todos os pedidos cadastrados
     */
    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    /**
     * Metodo que faz select em várias entidades com valores específicos
     */
    public List<RelatorioDeVendasVo> relatorio() {
        String jpql = "SELECT NEW br.com.alura.loja.vo.RelatorioDeVendasVo(" +
                " produto.nome," +
                " SUM(item.quantidade)," +
                " MAX(pedido.dataCadastro))" +
                " FROM Pedido pedido" +
                " JOIN pedido.itemPedidos item" +
                " JOIN item.produto produto" +
                " GROUP BY produto.nome" +
                " ORDER BY item.quantidade DESC ";
        return em.createQuery(jpql, RelatorioDeVendasVo.class)
                .getResultList();
    }

    public Pedido buscarPorId(Long id) {
        return this.em.find(Pedido.class, id);
    }

    /**
     * Método que busca a entidade e seus respectivos relacionamentos
     */
    public Pedido buscarPedidoComCliente(Long id) {
        String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = ?1";

        return this.em.createQuery(jpql, Pedido.class)
                .setParameter(1, id)
                .getSingleResult();
    }
}
