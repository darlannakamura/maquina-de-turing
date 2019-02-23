/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

import Automato.visual.Elemento;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author darla
 */
public class FilaIndices {
        List<Elemento> lista;
 
    public FilaIndices(){
         this.lista = new LinkedList<>();
    }
    public void insere(Elemento estado){
        //Estrutura e = new Estrutura(estado, index);
        this.lista.add(estado);
    }
    public Elemento remove(){
        return this.lista.remove(0);
    }
    public boolean vazia(){
        return this.lista.isEmpty();
    }
    
    public int size(){
        return this.lista.size();
    }
}
