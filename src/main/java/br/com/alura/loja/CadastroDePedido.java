package br.com.alura.loja;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.model.*;
import br.com.alura.loja.util.JpaUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {


    public static void main(String[] args) {
        cadastrarProduto();
        cadastrarPedido();
    }
    private static void cadastrarPedido() {

        EntityManager em = JpaUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.buscarPorId(1l);

        Cliente cliente = new Cliente("Marcio", "123456");
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        em.getTransaction().begin();

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);
        ClienteDao clienteDao = new ClienteDao(em);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();

        BigDecimal total = pedidoDao.valorTotalVendido();
        System.out.println(total);

        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorio();
        relatorio.forEach(System.out::println);
    }
    private static void cadastrarProduto() {

        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal",
                new BigDecimal("800"), celulares);

        EntityManager em = JpaUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
