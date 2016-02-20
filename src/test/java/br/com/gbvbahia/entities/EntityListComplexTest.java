/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntityListComplexTest {

    /**
     * ID único no banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Bla bla bla...
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento", nullable = false)
    @NotNull
    private Date dataVencimento;
    /**
     * Bla bla bla...
     */
    @NotNull
    @Column(name = "valor_estimado", nullable = false)
    @Digits(fraction = 2, integer = 12)
    private BigDecimal valorEstimado;
    /**
     * Valor real pago na conta, utilizado nas receitas fixas, em que
     * estimativas futuras são realizadas. Não é obrigatório nas
     * despesas/receitas fixas, nas variaveis sempre deverá ser
     * informado.<br> <b>PODE SER NULO</b>
     */
    @Column(name = "valor_real")
    @Digits(fraction = 2, integer = 12)
    private BigDecimal valorReal;

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public BigDecimal getValorReal() {
        return valorReal;
    }

    @Override
    public String toString() {
        return "EntityComplexListTest{"
                + "id=" + id
                + ", dataVencimento=" + dataVencimento
                + ", valorEstimado=" + valorEstimado
                + ", valorReal=" + valorReal
                + '}';
    }
}
