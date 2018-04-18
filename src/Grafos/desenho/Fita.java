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
public class Fita {
    private char[] conteudo;
    private int ponteiro;

    public Fita(String conteudo) {
        this.conteudo = new char[100];
        this.conteudo[0] = '*';
        this.conteudo[1] = '*';
        this.conteudo[2] = '*';
        this.conteudo[3] = '@';
        int j = 0;
        for(int i = 4; i < 100; i++){
            if(j < conteudo.length()){
                this.conteudo[i] = conteudo.charAt(j);
                j++;
            }else{
                this.conteudo[i] = '*';
            }
            
        }
        this.ponteiro = 4;
    }

    public char[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(char[] conteudo) {
        this.conteudo = conteudo;
    }

    public int getPonteiro() {
        return ponteiro;
    }

    public void setPonteiro(int ponteiro) {
        this.ponteiro = ponteiro;
    }

    
    
    public char anda(char sentido){
       if(sentido == 'R'){
           ponteiro++;
           return conteudo[ponteiro];
       }else if(sentido == 'L'){
           ponteiro--;
           return conteudo[ponteiro];
       }
       
       return conteudo[ponteiro];
    }
    
    public char retorna(){
        return conteudo[ponteiro];
    }
    
    public void write(char c){
     conteudo[ponteiro] = c;
       
       
    }
    
}
