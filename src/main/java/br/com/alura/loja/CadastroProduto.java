package br.com.alura.loja;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Produto;
import br.com.alura.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JpaUtil.getEntityManager();
        ProdutoDao produtoDAO = new ProdutoDao(em);

        Produto p = produtoDAO.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> produtos = produtoDAO.buscarPorCategoria("CELULARES");
//        List<Produto> produtos = produtoDAO.buscarPorNome("Xiaomi Redmi");
//        List<Produto> produtos = produtoDAO.buscarPorPreco(new BigDecimal(800));
        produtos.forEach(pr -> System.out.println(pr.getNome()));
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
