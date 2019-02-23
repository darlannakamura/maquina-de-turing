/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

/**
 *
 * @author Matheus Palmeira
 */
public class Validator {

    private final String TOKEN = "[a-z]|[A-Z]|[0-9]";
    private final String DIRECTION = "[S|L|R]";

    public Validator() {

    }
    
    public boolean verificaEntradas(String[][] entradas){
        for(int i = 0; i < entradas.length; i++){
            for(int j = 0; j < 3; j++){
                if(j != 2){
                    if(entradas[i][j].length() > 1 && !entradas[i][j].equals("VAZIO")){
                        return false;
                    }
                    else{
                        if(!entradas[i][j].equals("VAZIO") && !entradas[i][j].matches(TOKEN)){
                            return false;
                        }
                    }
                    
                }
                else{
                    if(!entradas[i][j].matches(DIRECTION)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @return the valido
     */
//    public boolean verifica(String[] entrada, int index) {
//        if (index < 2) {
//            if (entrada[index].length() > 1) {
//                if (entrada[index].equals("VAZIO")) {
//                    return true;
//                } else {
//                    return false;
//                }
//            } else {
//                if (entrada[index].matches(TOKEN)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        } else {
//            if (entrada[index].matches(DIRECTION)) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }

}
