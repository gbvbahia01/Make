package br.com.gbvbahia.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
public class EntitySetComplexTest {

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
   * Valor real pago na conta, utilizado nas receitas fixas, em que estimativas futuras são
   * realizadas. Não é obrigatório nas despesas/receitas fixas, nas variaveis sempre deverá ser
   * informado.<br>
   * <b>PODE SER NULO</b>
   */
  @Column(name = "valor_real")
  @Digits(fraction = 2, integer = 12)
  private BigDecimal valorReal;

  public Date getDataVencimento() {
    return this.dataVencimento;
  }

  public Long getId() {
    return this.id;
  }

  public BigDecimal getValorEstimado() {
    return this.valorEstimado;
  }

  public BigDecimal getValorReal() {
    return this.valorReal;
  }

  @Override
  public String toString() {
    return "EntityComplexListTest{" + "id=" + this.id + ", dataVencimento=" + this.dataVencimento
        + ", valorEstimado=" + this.valorEstimado + ", valorReal=" + this.valorReal + '}';
  }
}
