package br.com.alura.loja.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Classe Value Object
 */
@Getter
@AllArgsConstructor
@ToString
public class RelatorioDeVendasVo {

    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;


}
