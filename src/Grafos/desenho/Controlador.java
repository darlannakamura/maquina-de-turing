/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.desenho;

import Automato.visual.View;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author darla
 */
public class Controlador {

    public void verificaTipo(String linha) {
        if (linha.contains("<type>")) {
            linha = linha.replace("<type>", "");
            linha = linha.replace("</type>", "");
            linha = linha.replace(" ", "");

            if (!linha.contains("turing")) {
                JOptionPane.showMessageDialog(null, "Tipo de arquivo não é uma máquina de turing!");

            }
        }
    }

    public String replaceCaractereBugado(String linha) {
        return linha.replace("&#13;", "");
    }

    public Vertex adicionaVertex(BufferedReader br, String linha) throws IOException {
        Vertex v = new Vertex();
        while (!linha.contains("</block>")) {

            int inicio = linha.lastIndexOf("name=\"");
            int fim = linha.lastIndexOf("\">");
            String id = linha.substring(inicio, fim);
            id = id.replace("name=\"", "");
            v.setID(id);
            v.setLabel(id);
            //proxima linha é o x
            linha = br.readLine();
            linha = linha.replace("&#13;", "");
            if (linha.contains("<tag>")) {
                linha = br.readLine();
                linha = linha.replace("&#13;", "");
            }

            linha = linha.replace("<x>", "");
            linha = linha.replace("</x>", "");
            float x = Float.parseFloat(linha);
            v.setX(x);
            //proxima linha é o y
            linha = br.readLine();
            linha = linha.replace("&#13;", "");
            linha = linha.replace("<y>", "");
            linha = linha.replace("</y>", "");
            float y = Float.parseFloat(linha);
            v.setY(y);

            linha = br.readLine();
            linha = linha.replace("&#13;", "");
            if (linha.contains("<initial")) {
                v.setInicial(true);
                linha = br.readLine();
                linha = linha.replace("&#13;", "");
            }
            if (linha.contains("<final")) {
                v.setEstFinal(true);
                linha = br.readLine();
                linha = linha.replace("&#13;", "");

            }
        }

        return v;
    }

    public Edge adicionaEdge(Graph graph, BufferedReader br, String linha, int quantidadeFita) throws IOException {
        Vertex source = null;
        Vertex target = null;
        //proxima linha é o from:
        linha = br.readLine();
        linha = linha.replace("&#13;", "");
        linha = linha.replace("<from>", "");
        linha = linha.replace("</from>", "");
        linha = linha.replace(" ", "");

        int indice = (int) Float.parseFloat(linha);
        for (int i = 0; i < graph.vertex.size(); i++) {
            if (indice == graph.vertex.get(i).getPosition()) {
                source = graph.vertex.get(i);
            }
        }
        //acabei de setar o from, agora é o <to>:
        linha = br.readLine();
        linha = linha.replace("&#13;", "");
        linha = linha.replace("<to>", "");
        linha = linha.replace("</to>", "");
        linha = linha.replace(" ", "");
        indice = (int) Float.parseFloat(linha);
        for (int i = 0; i < graph.vertex.size(); i++) {
            if (indice == graph.vertex.get(i).getPosition()) {
                target = graph.vertex.get(i);
            }
        }
        TransicaoPorFita[] transicoes = new TransicaoPorFita[quantidadeFita];
        for (int i = 0; i < quantidadeFita; i++) {

            //acabei de setar  o to, agora a proxima linha é o label:
            linha = br.readLine();
            linha = linha.replace("&#13;", "");
            linha = linha.replace("<read", "");
            linha = linha.replace("</read>", "");
            linha = linha.replace("tape=\"" + (i + 1) + "\"", "");
            linha = linha.replace("/>", "");
            linha = linha.replace(">", "");
            linha = linha.replace(" ", "");
            linha = linha.replace("\t", "");
            String label = linha;

            linha = br.readLine();
            linha = linha.replace("&#13;", "");
            linha = linha.replace("<write", "");
            linha = linha.replace("tape=\"" + (i + 1) + "\"", "");
            linha = linha.replace("/>", "");
            linha = linha.replace(">", "");
            linha = linha.replace("</write", "");
            linha = linha.replace(" ", "");
            linha = linha.replace("\t", "");
            String fita = linha;

            linha = br.readLine();
            linha = linha.replace("&#13;", "");
            linha = linha.replace("<move", "");
            linha = linha.replace("tape=\"" + (i + 1) + "\"", "");
            linha = linha.replace("</move>", "");
            linha = linha.replace("/>", "");
            linha = linha.replace(">", "");
            linha = linha.replace(" ", "");
            linha = linha.replace("\t", "");
            char direcao = linha.charAt(0);

            if (label.equals("")) {
                label = "VAZIO";
            }
            if (fita.equals("")) {
                fita = "VAZIO";
            }

            transicoes[i] = new TransicaoPorFita(label, fita, direcao);
        }
        Edge edge = new Edge(source, target, transicoes);
        return edge;

    }

    /**
     * Fazer o verificador da quantidade de fita:
     *
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public Graph abreArquivo(String path) throws FileNotFoundException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String linha;
        int quantidadeDeFita = 0;
        try {

            linha = br.readLine();
            linha = br.readLine();
            Graph graph = null;

            linha = replaceCaractereBugado(linha);
            verificaTipo(linha);
            linha = br.readLine();
            if (linha.contains("tapes")) {
                linha = linha.replace("<tapes>", "");
                linha = linha.replace("</tapes>", "");
                linha = replaceCaractereBugado(linha);
                linha = linha.replace("\t", "");
                linha = linha.replace(" ", "");
                quantidadeDeFita = Integer.parseInt(linha);
                graph = new Graph(new Fita[quantidadeDeFita]);

            } else {
                quantidadeDeFita = 1;
                graph = new Graph(new Fita[1]);
            }
            while (linha != null) {
                //linha = linha.replace("&#13;", "");
                if (linha.contains("<block")) {
                    Vertex v = adicionaVertex(br, linha);

                    graph.addVertex(v);
                } else if (linha.contains("<transition>")) {
                    Edge edge = adicionaEdge(graph, br, linha, quantidadeDeFita);
                    graph.addEdge(edge);
                }

                linha = br.readLine();
            }
            return graph;
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String cabecalho() {
        String html = "";
        html += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><!--Created with Nakamura and Santos JFlap - 2017.--><structure>\n";

        html += "<type>turing</type>\n";
        return html;
    }

    public String tapes(int quantidade) {
        if (quantidade > 1) {
            return "<tapes>" + quantidade + "</tapes>\n";
        }
        return "";
    }

    public String listOfStates(Graph graph) {
        String html = "";

        //List of States:
        for (int i = 0; i < graph.vertex.size(); i++) {
            html += "   <block id=\"" + i + "\" name=\"" + graph.vertex.get(i).getID()+ "\">\n";
            html += "       <tag>" + "Machine" + i + "</tag>\n";
            html += "       <x>" + graph.vertex.get(i).getX() + "</x>\n";
            html += "       <y>" + graph.vertex.get(i).getY() + "</y>\n";
            if (graph.vertex.get(i).isInicial()) {
                html += "       <initial/>\n";
            }
            if (graph.vertex.get(i).isEstFinal()) {
                html += "       <final/>\n";
            }
            html += "   </block>\n";
        }

        return html;
    }

    public String listOfTransitions(Graph graph) {
        String html = "";
        //List of transitions:
        html += "   <!--The list of transitions.-->\n";

        for (int i = 0; i < graph.edges.size(); i++) {
            for (int j = 0; j < graph.edges.get(i).getValues().size(); j++) {
                html += "   <transition>\n";
                html += "       <from>" + graph.edges.get(i).getSource().getPosition() + "</from>\n";
                html += "       <to>" + graph.edges.get(i).getTarget().getPosition() + "</to>\n";
                for (int k = 0; k < graph.edges.get(i).getValues().get(j).getFita().length; k++) {
                    TransicaoPorFita t = graph.edges.get(i).getValues().get(j).getFita()[k];
                   
                    if (graph.edges.get(i).getValues().get(j).getFita().length == 1) {

                        if (t.getValue().equals("VAZIO")) {
                            html += "<read/>\n";
                        } else {
                            html += "       <read>" + t.getValue() + "</read>\n";
                        }
                        if (t.getFita().equals("VAZIO")) {
                            html += "<write/>\n";
                        } else {
                            html += "       <write>" + t.getFita() + "</write>\n";
                        }
                        html += "       <move>" + t.getSentido() + "</move>\n";

                    }
                    else{
                        String valor = t.getValue();
                        String fita  = t.getFita();
                        String sentido = t.getSentido()+"";
                        if(valor.equals("VAZIO")){
                            valor = "";
                        }
                        if(fita.equals("VAZIO")){
                            fita = "";
                        }
                        
                        
                        
                        if(valor.equals("")){
                            html += "<read tape=\""+(k+1)+"\"/>\n";
                        }else{
                            html += "<read tape=\""+(k+1)+"\">"+valor+"</read>\n";
                        }
                        
                        if(fita.equals("")){
                            html += "<write tape=\""+(k+1)+"\"/>\n";
                        }else{
                            html += "<write tape=\""+(k+1)+"\">"+fita+"</write>\n";
                        }
                        html += "<move tape=\""+(k+1)+"\">"+sentido+"</move>\n";
                    }

                   

                }
                html += "   </transition>\n";

            }

        }
        return html;
    }

    public void salvarArquivo(String path, Graph graph) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            FileWriter fw = new FileWriter(path);
            Graph aux = graph;

            String html = "";
            //inicio do arquivo:
            html += cabecalho();
            html += tapes(graph.fitas.length);
            html += "  <automaton>\n"
                    + "		<!--The list of states.-->\n";

            html += listOfStates(graph);

            html += listOfTransitions(graph);

            html += "<!--The list of automata-->\n";
            for (int i = 0; i < graph.vertex.size(); i++) {
                html += "<Machine" + i + "/>\n";
            }

            html += " </automaton>\n"
                    + "</structure>";

            fw.write(html);
            JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
            fw.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(View.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
