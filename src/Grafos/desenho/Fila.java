/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Darlan Nakamura
 */
public class Fila {
    List<Estrutura> lista;
 
    public Fila(){
         this.lista = new LinkedList<>();
    }
    public void insere(Estado estado, int index){
        Estrutura e = new Estrutura(estado, index);
        this.lista.add(e);
    }
    public Estrutura remove(){
        return this.lista.remove(0);
    }
    public boolean vazia(){
        return this.lista.isEmpty();
    }
    
}
