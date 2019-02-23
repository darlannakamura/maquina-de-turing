/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

/**
 *
 * @author darla
 */
public class TransicaoPorFita {
    private String value;
    private String fita;
    private char sentido; //'S' - Stop, 'L' - Left, 'R' - Right 

    public TransicaoPorFita(String value, String fita, char sentido) {
        this.value = value;
        this.fita = fita;
        this.sentido = sentido;
    }

    
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
    
    
}
