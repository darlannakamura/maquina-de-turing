/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

import Grafos.desenho.Estado;

/**
 *
 * @author Darlan Nakamura
 */
public class Aresta {
   
    public TransicaoPorFita[] fita;
    private Aresta prox;
    private Estado inicio;
    private Estado fim;

    public Aresta() {
    }
    
    public Aresta(int quantidade){
        fita = new TransicaoPorFita[quantidade];
        prox = null;
        inicio = new Estado();
        fim = new Estado();
    }

    public Aresta(TransicaoPorFita[] fita, Aresta prox, Estado inicio, Estado fim) {
        this.fita = fita;
        this.prox = prox;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    

    public TransicaoPorFita[] getFita() {
        return fita;
    }

    public void setFita(TransicaoPorFita[] fita) {
        this.fita = fita;
    }

    public Aresta getProx() {
        return prox;
    }

    public void setProx(Aresta prox) {
        this.prox = prox;
    }

    public Estado getInicio() {
        return inicio;
    }

    public void setInicio(Estado inicio) {
        this.inicio = inicio;
    }

    public Estado getFim() {
        return fim;
    }

    public void setFim(Estado fim) {
        this.fim = fim;
    }
    
    
    
}
