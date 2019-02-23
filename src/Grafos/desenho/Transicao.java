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

//    private String label;
//    private String fita;
//    private char sentido;
    public TransicaoPorFita[] fita;
    private Point point;
    public String label;

    public Transicao() {
    }

    public Transicao(TransicaoPorFita[] fita) {
        this.fita = fita;
        createLabel();
        System.out.println("Transição label: "+this.label);
    }
    
    

    public TransicaoPorFita[] getFita() {
        return fita;
    }

    public void setFita(TransicaoPorFita[] fita) {
        this.fita = fita;
        createLabel();
    }

    

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public void createLabel(){
        String s = "";
        for(int i = 0 ; i < fita.length; i++){
            if(i != 0 ){
                s += " | ";
            }
            if(fita[i].getValue().equals("VAZIO")){
                s += "λ";
            }else{
                s += fita[i].getValue();
            }
            s += " ; ";
            
            if(fita[i].getFita().equals("VAZIO")){
                 s += "λ";
            }else{
                s += fita[i].getFita();
            }
            s += " ; ";
            
            s += ""+fita[i].getSentido();
            
            
        }
        label = s;
    }
    
    
}
