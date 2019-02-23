/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automato.visual;

/**
 *
 * @author darla
 */
public class Elemento {
    public int indice;
    public String fita;

    public Elemento(int indice, String fita) {
        this.indice = indice;
        this.fita = fita;
    }

    
    
    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getFita() {
        return fita;
    }

    public void setFita(String fita) {
        this.fita = fita;
    }
    
    
}
