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
    private StringBuilder conteudo;
    private int ponteiro;
    
    public Fita(String conteudo, int ponteiro){
        this.conteudo = new StringBuilder(conteudo);
        this.ponteiro = ponteiro;
    }

    public Fita(String conteudo) {
//        this.conteudo = new char[100];
//        this.conteudo[0] = '*';
//        this.conteudo[1] = '*';
//        this.conteudo[2] = '*';
//        this.conteudo[3] = '@';
        //this.conteudo = "***@"+conteudo;
          this.conteudo = new StringBuilder("****"+conteudo);
//        int j = 0;
//        for(int i = 4; i < 100; i++){
//            if(j < conteudo.length()){
//                this.conteudo[i] = conteudo.charAt(j);
//                j++;
//            }else{
//                this.conteudo[i] = '*';
//            }
//            
//        }
        for(int i = 0 ; i < 100 - this.conteudo.length(); i++){
            this.conteudo.append("*");
        }
        this.ponteiro = 4;
    }

    public String getConteudo() {
        return conteudo.toString();
    }

    public void setConteudo(String conteudo) {
        //this.conteudo = conteudo;
        this.conteudo = new StringBuilder(conteudo);
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
           if(ponteiro == this.conteudo.length()){
               this.conteudo.append("*");
           }
           //return conteudo[ponteiro];
           return this.conteudo.charAt(ponteiro);
       }else if(sentido == 'L'){
           ponteiro--;
           if(ponteiro == -1){
               this.conteudo.insert(0, "*");
               ponteiro = 0;
           }
           //return conteudo[ponteiro];
           return this.conteudo.charAt(ponteiro);
       }
       
       //return conteudo[ponteiro];
       return this.conteudo.charAt(ponteiro);
    }
    
    public char retorna(){
        //return conteudo[ponteiro];
        return this.conteudo.charAt(ponteiro);
    }
    
    public void write(char c){
     //conteudo[ponteiro] = c;
     
     this.conteudo = this.conteudo.replace(ponteiro, ponteiro+1, ""+c);
   
       
       
    }
    
}
