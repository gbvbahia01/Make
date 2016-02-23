package br.com.gbvbahia.entities;

/**
 *
 * @author Guilherme
 */
public class EntityPropertiesTest {


  private String cpf;

  private String cnpj;

  private String nome;

  private String cep;

  public String getCnpj() {
    return this.cnpj;
  }

  public String getCpf() {
    return this.cpf;
  }

  public String getNome() {
    return this.nome;
  }

  public String getCep() {
    return this.cep;
  }


  @Override
  public String toString() {
    return "EnityPropertiesTest{" + " cpf=" + this.cpf + " cnpj=" + this.cnpj + " nome="
        + this.nome + " cep=" + this.cep + '}';
  }


}
