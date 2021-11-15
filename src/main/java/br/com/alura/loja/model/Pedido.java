package br.com.alura.loja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private LocalDate dataCadastro = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // Sempre indicar qual relacionamento quando houver um relacionamento bidirecional
    private List<ItemPedido> itemPedidos = new ArrayList<>(); // Inicializar lista na Model para evitar verificação de lista nula

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    /** Metodo utilitário para adicionar objeto na lista do relacionamento bidirecional */
    public void adicionarItem(ItemPedido itemPedido){
        itemPedido.setPedido(this); // Esse mapeia o outro lado
        this.itemPedidos.add(itemPedido);
        this.valorTotal = this.valorTotal.add(itemPedido.getValor());
    }
}
