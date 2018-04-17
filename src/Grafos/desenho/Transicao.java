/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

import java.awt.Point;

/**
 *
 * @author Darlan Nakamura
 */
public class Transicao {
    private String label;
    private String fita;
    private char sentido;
    private Point point;

    public Transicao() {
    }

    public Transicao(String label, String fita, char sentido, Point point) {
        this.label = label;
        this.fita = fita;
        this.sentido = sentido;
        this.point = point;
    }

    
    
    public Transicao(String label, Point point) {
        this.label = label;
        this.point = point;
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
    
    

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    
    
}
