/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

/**
 *
 * @author Darlan Nakamura
 */
public class Estrutura {
    private Estado estado;
    private int index;

    public Estrutura() {
    }

    public Estrutura(Estado estado, int index) {
        this.estado = estado;
        this.index = index;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    
}
