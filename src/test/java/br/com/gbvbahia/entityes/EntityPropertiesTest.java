/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

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
        return cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }
    

    @Override
    public String toString() {
        return "EnityPropertiesTest{"
                + " cpf=" + cpf
                + " cnpj=" + cnpj
                + " nome=" + nome
                + " cep=" + cep
                + '}';
    }
    
    
}
