/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.antigo;

import Grafos.desenho.Estado;

/**
 *
 * @author Darlan Nakamura
 */
public class ArestaAntiga {
    private String value;
    private String fita;
    private char sentido; //'S' - Stop, 'L' - Left, 'R' - Right 
    private ArestaAntiga prox;
    private Estado inicio;
    private Estado fim;

    public ArestaAntiga(String value, String fita, char sentido, ArestaAntiga prox, Estado inicio, Estado fim) {
        this.value = value;
        this.fita = fita;
        this.sentido = sentido;
        this.prox = prox;
        this.inicio = inicio;
        this.fim = fim;
    }

    
    
    public String getFita() {
        return fita;
    }

    public void setFita(String fita) {
        this.fita = fita;
    }

    public char getSentido() {
        return sentido;
    }

    public void setSentido(char sentido) {
        this.sentido = sentido;
    }

    
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArestaAntiga getProx() {
        return prox;
    }

    public void setProx(ArestaAntiga prox) {
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

    public ArestaAntiga() {
    }

    public ArestaAntiga(String value, ArestaAntiga prox, Estado inicio, Estado fim) {
        this.value = value;
        this.prox = prox;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    
}
