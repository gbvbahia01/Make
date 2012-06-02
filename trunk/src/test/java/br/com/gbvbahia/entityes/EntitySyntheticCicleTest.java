/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.entityes;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
@Entity
public class EntitySyntheticCicleTest {
    
    @NotNull
    private String nome;
    
    @NotNull
    private EntitySyntheticTest cicle;

    @Override
    public String toString() {
        return "EntitySyntheticCicleTest{"
                + "nome=" + nome
                + '}';
    }
    
    
}
