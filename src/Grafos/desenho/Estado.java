/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

import Grafos.antigo.ArestaAntiga;

/**
 *
 * @author Darlan Nakamura
 */
public class Estado {
    private Aresta arestaInicial;
    private boolean inicial;
    private boolean eFinal;
    private String ID;

    public Estado() {
    }

    public Estado(Aresta arestaInicial, boolean inicial, boolean eFinal, String ID) {
        this.arestaInicial = arestaInicial;
        this.inicial = inicial;
        this.eFinal = eFinal;
        this.ID = ID;
    }

    public Aresta getArestaInicial() {
        return arestaInicial;
    }

    public void setArestaInicial(Aresta arestaInicial) {
        this.arestaInicial = arestaInicial;
    }

    public boolean isInicial() {
        return inicial;
    }

    public void setInicial(boolean inicial) {
        this.inicial = inicial;
    }

    public boolean iseFinal() {
        return eFinal;
    }

    public void seteFinal(boolean eFinal) {
        this.eFinal = eFinal;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    
    
}
