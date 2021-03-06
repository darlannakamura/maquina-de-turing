package Grafos.desenho;

import Grafos.antigo.ArestaAntiga;
import Automato.visual.Elemento;
import Grafos.desenho.color.RainbowScale;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class Graph {

    //public ArrayList<Elemento> indicesFita;
    public FilaIndices[] indicesFita;
    public ArrayList<String> verticesSolucao = new ArrayList<>();
    public ArrayList<Estado> estados = new ArrayList<>();
    public ArrayList<Vertex> vertex = new ArrayList<>();
    public ArrayList<Edge> edges = new ArrayList<>();
    public HashMap edgesMap = new HashMap();
    public Fita fita;
    public Fita[] fitas;

//    public Graph() {
//        inicializaIndicesFita();
//        fita = new Fita("");
//    }
    public Graph(Fita[] fitas) {
        this.fitas = fitas;
        //indicesFita = new ArrayList<>();
        inicializaIndicesFita(fitas.length);
    }

//    public Graph(int nVert) {
//        //indicesFita = new ArrayList<Elemento>();
//        inicializaIndicesFita();
//        fita = new Fita("");
//
//        RainbowScale cS = new RainbowScale();
//        int colorStep = 255 / nVert;
//        for (int i = 0; i < nVert; i++) {
//            Vertex v = new Vertex();
//            v.setColor(cS.getColor(i * colorStep));
//            v.setID(Integer.toString(i));
//            this.vertex.add(v);
//        }
//        computeCircledPosition(150);
//    }
    public void inicializaIndicesFita(int quantidade) {
        indicesFita = new FilaIndices[quantidade];
        for(int i =0 ; i < indicesFita.length; i++){
            indicesFita[i] = new FilaIndices();
        }
    }

    public FilaIndices[] salvaIndicesMultifita(FilaIndices[] indicesFita, Fita[] fitas) {
        for (int i = 0; i < fitas.length; i++) {
            indicesFita[i].insere(new Elemento(fitas[i].getPonteiro(), fitas[i].getConteudo()));
        }
        return indicesFita;
    }

    /**
     * Esse método verifica se para todas as fitas, a condição é satisfeita.
     *
     * @param aux
     * @param fita
     * @return
     */
    public boolean verificaSeEntra(Aresta aux, Fita[] fita) {
        boolean entra = false;
        for (int i = 0; i < fita.length; i++) {
            if (fita[i].retorna() == aux.fita[i].getValue().charAt(0) || (fita[i].retorna() == '*' && aux.fita[i].getValue().equals("VAZIO"))) {
                entra = true;
            } else {
                return false;
            }
        }

        return true;
    }

    public Fita[] escreveNasFitas(Aresta aux, Fita[] fita) {
        for (int i = 0; i < fita.length; i++) {
            if (aux.fita[i].getFita().equals("VAZIO")) {
                fita[i].write('*');
                //indices.add(new Elemento(newFita.getPonteiro(), newFita.getConteudo()));
            } else {
                fita[i].write(aux.fita[i].getFita().charAt(0));
                //indices.add(new Elemento(newFita.getPonteiro(), newFita.getConteudo()));
            }
            fita[i].anda(aux.fita[i].getSentido());
        }
        return fita;
    }

    public Fita[] inicializaNewFita(Fita[] fita) {
        Fita[] nFita = new Fita[fita.length];
        for (int i = 0; i < nFita.length; i++) {
            nFita[i] = new Fita(fita[i].getConteudo(), fita[i].getPonteiro());
        }

        return nFita;
    }

    public FilaIndices[] inicializaIndicesFita(FilaIndices[] indicesFita) {
        FilaIndices[] nIndices = new FilaIndices[indicesFita.length];
        for(int i = 0 ; i < nIndices.length; i++){
            nIndices[i] = new FilaIndices();
        }
        
        for (int i = 0; i < indicesFita.length; i++) {
            while (!indicesFita[i].vazia()) {
                nIndices[i].insere(indicesFita[i].remove());
            }
        }
        return nIndices;
    }

    public void imprimeFitas(Fita[] fitas){
        for(int i =0 ; i < fitas.length; i++){
            System.out.println("Fita "+i+": "+fitas[i].getConteudo());
        }
    }
    
    //buscaLarguraMultifita
    public boolean buscaLarguraRecursiva(Estado estado, Fita[] fita, FilaIndices[] indicesFita, ArrayList<String> verticesSolucao) {
//        System.out.println("fita:"+fita.getConteudo()+"ponteiro:"+fita.getPonteiro());
//        System.out.println("estado:"+estado.getID());
        imprimeFitas(fita);
        verticesSolucao.add("" + estado.getID());
        //indicesFita.add(new Elemento(fita.getPonteiro(), fita.getConteudo()));
        indicesFita = salvaIndicesMultifita(indicesFita, fita);
        int indice = retornaIndiceNosEstados(estado);
        estado = estados.get(indice);

        if (estado.iseFinal()) {
            //indicesFita.add(new Elemento(fita.getPonteiro(), fita.getConteudo()));
            this.indicesFita = indicesFita;
            this.verticesSolucao = verticesSolucao;
            this.fitas = fita;
            return true;
        }

        Aresta aux = estado.getArestaInicial();
        while (aux != null) { //percorrerei todas as adjacências:
            if (verificaSeEntra(aux, fita)) {

                Fita[] newFita = inicializaNewFita(fita);
                FilaIndices[] newIndicesFita = inicializaIndicesFita(indicesFita);
                newFita = escreveNasFitas(aux, newFita);
                boolean resposta = buscaLarguraRecursiva(aux.getFim(), newFita, newIndicesFita, verticesSolucao);
                if (resposta) {
                    return resposta;
                }
            }
            aux = aux.getProx();
        }
        return false;
    }

//    public boolean buscaLarguraRecursiva(Estado estado, Fita fita, ArrayList<Elemento> indicesFita, ArrayList<String> verticesSolucao) {
////        System.out.println("fita:"+fita.getConteudo()+"ponteiro:"+fita.getPonteiro());
////        System.out.println("estado:"+estado.getID());
//        verticesSolucao.add("" + estado.getID());
//        indicesFita.add(new Elemento(fita.getPonteiro(), fita.getConteudo()));
//        int indice = retornaIndiceNosEstados(estado);
//        estado = estados.get(indice);
//
//        if (estado.iseFinal()) {
//            //indicesFita.add(new Elemento(fita.getPonteiro(), fita.getConteudo()));
//            this.indicesFita = indicesFita;
//            this.verticesSolucao = verticesSolucao;
//            this.fita = fita;
//            return true;
//        }
//
//        Aresta aux = estado.getArestaInicial();
//        while (aux != null) { //percorrerei todas as adjacências:
//            if (fita.retorna() == aux.getValue().charAt(0) || (fita.retorna() == '*' && aux.getValue().equals("VAZIO"))) {
//                Fita newFita = new Fita(fita.getConteudo(), fita.getPonteiro());
//                ArrayList<Elemento> indices = (ArrayList<Elemento>) indicesFita.clone();
//                ArrayList<String> vertices = (ArrayList<String>) verticesSolucao.clone();
//                if (aux.getFita().equals("VAZIO")) {
//                    newFita.write('*');
//                    //indices.add(new Elemento(newFita.getPonteiro(), newFita.getConteudo()));
//                } else {
//                    newFita.write(aux.getFita().charAt(0));
//                    //indices.add(new Elemento(newFita.getPonteiro(), newFita.getConteudo()));
//                }
//                newFita.anda(aux.getSentido());
//
//                boolean resposta = buscaLarguraRecursiva(aux.getFim(), newFita, indices, vertices);
//                if (resposta) {
//                    return resposta;
//                }
//            }
//            aux = aux.getProx();
//        }
//        return false;
//    }
    public void addVertex(Vertex v) {
        v.setPosition(this.vertex.size());
        this.vertex.add(v);

    }

    public void addVertex(float x, float y, String ID) {

        Vertex v = new Vertex(x, y, ID);
        v.setPosition(this.vertex.size());
        this.vertex.add(v);

    }

    public void addVertex(float x, float y) {
        Vertex v = new Vertex(x, y);
        v.setPosition(this.vertex.size());
        this.vertex.add(v);

    }

    public void imprimeVertices() {
        for (int i = 0; i < vertex.size(); i++) {
            System.out.println("\n V[" + i + "] = " + vertex.get(i).getID());
        }
    }

    public void imprimeEdges() {
        for (int i = 0; i < edges.size(); i++) {
            System.out.println("\n Aresta[" + i + "] = " + edges.get(i).getSource().getID() + "," + edges.get(i).getTarget().getID() + "Label : " + edges.get(i).getLabel());
        }
    }

    private Edge verificaArestasIguais(Edge e) {
        for (int i = 0; i < getEdges().size(); i++) {
            Edge aux = this.getEdges().get(i);
            if ((aux.getSource() == e.getSource()) && (aux.getTarget() == e.getTarget())) {
                return aux;
            }
        }
        return null;
    }

//    public boolean buscaLargura(Estado e, Fita fita) {
//        Fila fila = new Fila();
//        indicesFita = new ArrayList<Elemento>();
//        fila.insere(e);
//        while (!fila.vazia()) {
//            Estado estado = fila.remove();
//            verticesSolucao.add(estado.getID());
//            System.out.println("Removeu da Fila = " + estado.getID());
//            int indice = retornaIndiceNosEstados(estado);
//            estado = estados.get(indice);
//            if (estado.iseFinal()) {
//                indicesFita.add(new Elemento(fita.getPonteiro(), fita.getConteudo()));
//                return true;
//            }
//            ArestaAntiga aux = estado.getArestaInicial();
//            while (aux != null) { //percorrerei todas as adjacências:
//                if (fita.retorna() == aux.getValue().charAt(0) || (fita.retorna() == '*' && aux.getValue().equals("VAZIO"))) {
//                    if (aux.getFita().equals("VAZIO")) {
//                        fita.write('*');
//                        indicesFita.add(new Elemento(fita.getPonteiro(), fita.getConteudo()));
//                    } else {
//                        fita.write(aux.getFita().charAt(0));
//                        indicesFita.add(new Elemento(fita.getPonteiro(), fita.getConteudo()));
//                    }
//                    fita.anda(aux.getSentido());
//                    fila.insere(aux.getFim());
//                    break;
//                }
//                aux = aux.getProx();
//            }
//        }
//        return false;
//    }
    public FilaIndices[] getIndicesFita() {
        return indicesFita;
    }

    public void setIndicesFita(FilaIndices[] indicesFita) {
        this.indicesFita = indicesFita;
    }

//    public boolean buscaLarguraAntiga(Estado e, String input) {
//        int v;
//        int inicio = estados.indexOf(e);
//        String label = "";
//        int quantidadeVertices = vertex.size();
//
//        Fila fila = new Fila();
//
//        fila.insere(e, 0); //inserimos o estado e o index do input
//        System.out.println("Busca em Largura : \n");
//        while (!fila.vazia()) { //enquanto a fila NÃO for vazia, faça:
//            Estrutura estrutura = fila.remove(); //removo o primeiro elemento da fila.
//
//            verticesSolucao.add(estrutura.getEstado().getID());
//            Estado estado = estrutura.getEstado();
//            int index = estrutura.getIndex();
//            System.out.println("Removeu da fila = " + estado.getID());
//
//            int indice = retornaIndiceNosEstados(estado);
//
//            if (estados.get(indice).iseFinal() && index >= input.length()) {
//                System.out.println("Index final = " + index);
//                System.out.println("Pertence a linguagem.");
//                //return true;
//                return true;
//            }
//
//            Aresta aux = estados.get(indice).getArestaInicial();
//            while (aux != null) {
//                //vou pegar todas as adjacencias do estado, ou melhor, todas as arestas:
//
//                label = aux.getValue();
//
//                System.out.println("Aresta (" + aux.getInicio().getID() + "," + aux.getFim().getID() + ") = " + aux.getValue()+" , "+aux.getFita()+" , "+aux.getSentido());
//
//                
//                 String substring = input.substring(index, index + aux.getValue().length());
//                 
//                if (index >= input.length()) {
//                    System.out.println("entrou aqui");
//                    return false;
//      
//                } //vou verificar SE o input, substring de input até o tamanho do elemento, é igual ao elemento.
//                else if (aux.getValue().equals("VAZIO")) {
//                    fila.insere(aux.getFim(), index);
//                } else if (substring.equals(aux.getValue())) {
//
//                    //Aqui vem o tratamento mais importante: Verificarmos se chegou, ou seja, se dado o input apresentado
//                    //com o caractere apresentado nessa aresta estão iguais até o momento
//                    //Se for VAZIO, nós só pulamos para o vértice final.
//                    System.out.println("Vamos inserir na fila o :" + aux.getFim().getID());
//                    Estado fim = aux.getFim();
//                    int id2;
//                    id2 = index + aux.getValue().length();
//
//                    fila.insere(fim, id2);
//                }
//                aux = aux.getProx();
//            }
//
//        }
//        System.out.println("Acabou");
//
//        return false;
//
//    }
    public int retornaIndice(String id) {
        for (int i = 0; i < estados.size(); i++) {
            if (id.equals(estados.get(i).getID())) {
                return i;
            }
        }
        return -1;
    }

    public int retornaIndiceNosEstados(Estado e) {
        for (int i = 0; i < estados.size(); i++) {
            if (e.getID().equals(estados.get(i).getID())) {
                return i;
            }
        }
        return -1;
    }

    public Transicao criaTransicao(TransicaoPorFita[] transicoes, Edge e) {
        Transicao transicao = new Transicao();

        transicao.setFita(transicoes);
        transicao.setPoint(new Point((int) e.getxLabel(), (int) e.getyLabel()));
        return transicao;
    }

    public void addEdge(Edge e) {
        Edge aux = verificaArestasIguais(e);
        Transicao transicao = new Transicao();
        transicao.setPoint(new Point());

        if (aux == null) { //ou seja, se não existe edge:
            transicao = criaTransicao(e.getTransicoes(), e);
            e.getValues().add(transicao);
            this.edges.add(e);
        } else { //se já existe a edge:
            //aux.getValues().add(aux.getLabel());
//            transicao.setLabel(e.getLabel());
//            transicao.setFita(e.getFita());
//            transicao.setSentido(e.getSentido());
            transicao = criaTransicao(e.getTransicoes(), e);
            aux.getValues().add(transicao);

        }

        ArrayList<String> chave = new ArrayList();
        chave.add(e.getSource().getID());
        chave.add(e.getTarget().getID());
        this.edgesMap.put(chave, e);
    }

//    public void addEdge(Edge e) {
//        Edge aux = verificaArestasIguais(e);
//        Transicao transicao = new Transicao();
//        transicao.setPoint(new Point());
//
//        if (aux == null) {
//            transicao.setLabel(e.getLabel());
//            transicao.setFita(e.getFita());
//            transicao.setSentido(e.getSentido());
//            transicao.setPoint(new Point((int) e.getxLabel(), (int) e.getyLabel()));
//            e.getValues().add(transicao);
//            this.edges.add(e);
//        } else {
//            //aux.getValues().add(aux.getLabel());
//            transicao.setLabel(e.getLabel());
//            transicao.setFita(e.getFita());
//            transicao.setSentido(e.getSentido());
//            aux.getValues().add(transicao);
//
//        }
//
//        ArrayList<String> chave = new ArrayList();
//        chave.add(e.getSource().getID());
//        chave.add(e.getTarget().getID());
//        this.edgesMap.put(chave, e);
//    }
    public HashMap retornaHashMap() {
        return this.edgesMap;
    }

    private void computeCircledPosition(int ray) {
        int nVert = this.vertex.size();
        int step = 360 / nVert;
        int deslocX = 100 + ray;
        int deslocY = 100 + ray;
        for (int i = 0; i < nVert; i++) {
            double ang = i * step;
            ang = ang * Math.PI / 180;
            float X = (float) Math.cos(ang);
            float Y = (float) Math.sin(ang);
            X = X * ray + deslocX;
            Y = Y * ray + deslocY;
            this.vertex.get(i).setX(X);
            this.vertex.get(i).setY(Y);
        }

    }

    public ArrayList<String> getVerticesSolucao() {
        return verticesSolucao;
    }

    public void setVerticesSolucao(ArrayList<String> verticesSolucao) {
        this.verticesSolucao = verticesSolucao;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public HashMap getEdgesMap() {
        return edgesMap;
    }

    public void setEdgesMap(HashMap edgesMap) {
        this.edgesMap = edgesMap;
    }

    public ArrayList<Vertex> getVertex() {
        return this.vertex;
    }

    public Vertex findVertexById(String index) {
        int i;
        for (i = 0; i < vertex.size(); i++) {
            if (this.vertex.get(i).getID() == index) {
                return this.vertex.get(i);
            }
        }
        return null;

    }

    public void setVertex(ArrayList<Vertex> v) {
        this.vertex.clear();
        this.vertex = v;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public void setEdges(ArrayList<Edge> e) {
        this.edges = e;
    }

    public void draw(java.awt.Graphics2D g2) {

        if (edges != null) {
            for (Edge edge : edges) {
                edge.draw(g2, edges);
            }
        }
        if (vertex != null) {
            for (Vertex v : this.vertex) {
                v.draw(g2);
            }
        }
    }

    public java.awt.Dimension getSize() {
        if (this.vertex.size() > 0) {
            float maxX = vertex.get(0).getX();
            float minX = vertex.get(0).getX();
            float maxY = vertex.get(0).getY();
            float minY = vertex.get(0).getY();

            for (Vertex v : this.vertex) {
                if (maxX < v.getX()) {
                    maxX = v.getX();
                } else {
                    if (minX > v.getX()) {
                        minX = v.getX();
                    }
                }

                if (maxY < v.getY()) {
                    maxY = v.getY();
                } else {
                    if (minY > v.getY()) {
                        minY = v.getY();
                    }
                }
            }

            int w = (int) (maxX + (this.vertex.get(0).getRay() * 5)) + 350;
            int h = (int) (maxY + (this.vertex.get(0).getRay() * 5));

            return new java.awt.Dimension(w, h);
        } else {
            return new java.awt.Dimension(0, 0);
        }
    }

    public void setInicial(Vertex v, boolean isInicial) {
        v.setInicial(isInicial);
        //grafo.getLista().setInicial(v.getID(), isInicial);
        for (int i = 0; i < this.vertex.size(); i++) {
            if (v.getID().equals(vertex.get(i).getID())) {
                vertex.get(i).setInicial(isInicial);
            }
        }
    }

    public void excluirAresta(Transicao t) {
        for (int i = 0; i < this.edges.size(); i++) {

            for (int j = 0; j < this.edges.get(i).getValues().size(); j++) {
                if (this.edges.get(i).getValues().get(j).equals(t) && this.edges.get(i).getValues().size() == 1) {
                    this.edges.remove(edges.get(i));
                    break;
                } else if (this.edges.get(i).getValues().get(j).equals(t)) {
                    edges.get(i).getValues().remove(t);
                    break;
                }

            }
        }
    }

    public void excluirVertice(Vertex v) {
        for (int j = 0; j < this.edges.size();) {
            if ((this.edges.get(j).getSource().equals(v))
                    || (this.edges.get(j).getTarget().equals(v))) {
                for (int i = 0; i < this.edges.get(j).getValues().size(); i++) {
                    this.excluirAresta(this.edges.get(j).getValues().get(i));
                    break;
                }
            } else {
                j++;
            }
        }
        this.vertex.remove(v);

    }

    public void setFinal(Vertex v, boolean isFinal) {
        v.setEstFinal(isFinal);
        //grafo.getLista().setInicial(v.getID(), isInicial);
        for (int i = 0; i < this.vertex.size(); i++) {
            if (v.getID().equals(vertex.get(i).getID())) {
                vertex.get(i).setEstFinal(isFinal);
            }
        }
    }

    public void transformaNaNovaEstrutura() {
        estados = new ArrayList<>();

        for (int i = 0; i < vertex.size(); i++) {

            Estado estado = new Estado();
            estado.setArestaInicial(null);
            estado.setID(vertex.get(i).getID());
            estado.setInicial(vertex.get(i).isInicial());
            estado.seteFinal(vertex.get(i).isEstFinal());

            estados.add(estado);

            for (int j = 0; j < edges.size(); j++) {
                if (edges.get(j).getSource().getID().equals(vertex.get(i).getID())) { //se achamos uma aresta:

                    for (int k = 0; k < edges.get(j).getValues().size(); k++) {

                        Aresta aux;

                        Estado ini = new Estado();
                        ini.setID(edges.get(j).getSource().getID());

                        Estado fin = new Estado();
                        fin.setID(edges.get(j).getTarget().getID());

                        Transicao transicao = edges.get(j).getValues().get(k);
                        //for (int l = 0; l < transicao.getFita().length; l++) {
                            //aux = new Aresta(transicao.getLabel(), transicao.getFita(), transicao.getSentido(), null, ini, fin);
                            
                            aux = new Aresta(transicao.getFita(), null, ini, fin);
                            
                            if (estados.get(i).getArestaInicial() == null) {
                                estados.get(i).setArestaInicial(aux);
                            } else {
                                Aresta a = estados.get(i).getArestaInicial();
                                while (a.getProx() != null) {
                                    a = a.getProx();
                                }
                                a.setProx(aux);
                            }
                        //}

                    }

                }
            }

        }

    }

    //Se tiver pelo menos um nó final, retorna true
    public boolean encontraNoFinal() {
        //boolean peloMenosUmNoFinal = false;
        for (int i = 0; i < vertex.size(); i++) {
            if (vertex.get(i).isEstFinal()) {
                return true;
            }
        }
        return false;
    }

    public boolean execucaoPassoAPasso(String input) {
        Vertex aux = retornaInicial();
        if (aux == null) {
            JOptionPane.showMessageDialog(null, "Por favor, marque um nó como inicial.");

        } //quer dizer que não tem vértice inicial
        else if (!encontraNoFinal()) {
            JOptionPane.showMessageDialog(null, "Por favor, marque pelo menos um nó como final.");

        } else {
            imprimeVertices();
            imprimeEdges();

            transformaNaNovaEstrutura();
            Estado inicial = encontraEstadoinicial();
            boolean resposta;
            verticesSolucao = new ArrayList<>();

            //Fita fita = new Fita(input);
            Fita[] f = inicializaFita(input, indicesFita.length);
            //return buscaLargura(inicial, fita);
            return buscaLarguraRecursiva(inicial, f, indicesFita, verticesSolucao);
        }
        return false;
    }

    public boolean deixaVerticeCinza(int cont) {
        if (cont < verticesSolucao.size()) {
            for (int i = 0; i < vertex.size(); i++) {
                vertex.get(i).setColor(Color.GREEN);
                if (verticesSolucao.get(cont).equals(vertex.get(i).getID())) {
                    vertex.get(i).setColor(Color.GRAY);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean verificacaoDoAutomato() {
        Vertex aux = retornaInicial();
        if (aux == null) {
            JOptionPane.showMessageDialog(null, "Por favor, marque um nó como inicial.");
            return false;

        } //quer dizer que não tem vértice inicial
        else if (!encontraNoFinal()) {
            JOptionPane.showMessageDialog(null, "Por favor, marque pelo menos um nó como final.");
            return false;

        }
        return true;
    }
    
    public Fita[] inicializaFita(String input, int quantidade){
        Fita[] f = new Fita[quantidade];
        f[0] = new Fita(input);
        for(int i = 1 ; i < quantidade ; i++){
            f[i] = new Fita("");
        }
        return f;
    }
    
    public boolean execucaoMultiplasEntradas(String input) {
        transformaNaNovaEstrutura();
        Estado inicial = encontraEstadoinicial();
        //Fita f = new Fita(input);
        Fita[] f = inicializaFita(input, indicesFita.length);
        
        FilaIndices[] fi = new FilaIndices[f.length];
        for(int i = 0 ; i < fi.length; i++){
            fi[i] = new FilaIndices();
        }
       
        //return buscaLarguraRecursiva(inicial, f, new ArrayList<>(), new ArrayList<>());
        return buscaLarguraRecursiva(inicial, f , fi, new ArrayList<>());
    }

//    public boolean execucaoRapida(Fita fita) {
//        Vertex aux = retornaInicial();
//        if (aux == null) {
//            JOptionPane.showMessageDialog(null, "Por favor, marque um nó como inicial.");
//
//        } //quer dizer que não tem vértice inicial
//        else if (!encontraNoFinal()) {
//            JOptionPane.showMessageDialog(null, "Por favor, marque pelo menos um nó como final.");
//
//        } else {
//            imprimeVertices();
//            imprimeEdges();
//
//            transformaNaNovaEstrutura();
//            Estado inicial = encontraEstadoinicial();
//            boolean resposta;
//            resposta = buscaLargura(inicial, fita);
//            return resposta;
//
//            //return visit(inicial.getID(), input, 0);
//            //return visita(aux, input, 0, 0);
//        }
//        return false;
//    }

    public boolean execucaoRapidaNaoDeterministica(String input) {
        Vertex aux = retornaInicial();
        if (aux == null) {
            JOptionPane.showMessageDialog(null, "Por favor, marque um nó como inicial.");

        } //quer dizer que não tem vértice inicial
        else if (!encontraNoFinal()) {
            JOptionPane.showMessageDialog(null, "Por favor, marque pelo menos um nó como final.");

        } else {
            //imprimeVertices();
            //imprimeEdges();

            transformaNaNovaEstrutura();
            Estado inicial = encontraEstadoinicial();
            boolean resposta;
            
            Fita[] f = inicializaFita(input, indicesFita.length);
            
            resposta = buscaLarguraRecursiva(encontraEstadoinicial(), f, indicesFita, verticesSolucao);
            return resposta;

            //return visit(inicial.getID(), input, 0);
            //return visita(aux, input, 0, 0);
        }
        return false;
    }

    public Estado encontraEstadoPeloId(String id) {
        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).getID().equals(id)) {
                return estados.get(i);
            }
        }
        return null;
    }

    public Estado encontraEstadoinicial() {
        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).isInicial()) {
                return estados.get(i);
            }
        }
        return null;
    }

//    private boolean visit(String id, String input, int indexInput) {
//        Estado estado = encontraEstadoPeloId(id);
//        boolean vazio = false;
//        Aresta arestaVazia = null;
//        boolean naoDeterministico = false;
//        for (Aresta aux = estado.getArestaInicial(); aux != null; aux = aux.getProx()) {
//            if (aux.getValue().equals("VAZIO")) {
//                arestaVazia = aux;
//                vazio = true;
//            }
//        }
//
//        if (indexInput >= input.length()) {
//            if (estado.iseFinal()) {
//                return true;
//            } else {
//                if (arestaVazia != null) {
//                    return visit(arestaVazia.getFim().getID(), input, indexInput);
//                }
//            }
//        }
//
//        for (ArestaAntiga aux = estado.getArestaInicial(); aux != null; aux = aux.getProx()) {
//
//            String string = input.substring(indexInput, indexInput + aux.getValue().length());
//            if (aux.getValue().equals(string)) {
//                return (visit(aux.getFim().getID(), input, indexInput + aux.getValue().length()));
//            }
//
//        }
//
//        return false;
//    }

    public void ordenaEdge() { //devo deixar as que são source == target sempre no começo do arraylist
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getSource() == edges.get(i).getTarget()) {
                //criar um comparator
            }
        }
    }

    public Transicao retornaTransicao(Vertex v, int indiceAresta) {
        int indice = 0;
        //tenho que de alguma maneira, OU ordenar, deixando sempre as arestas que são source == target antes das que são source != target, OU dar um jeito de controlar isso, dele verificar sempre antes se existe source == target. Acho melhor ordenar.
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getSource().equals(v) && indice == indiceAresta) {
                return edges.get(i).getValues().get(0);
            } else if (edges.get(i).getSource().equals(v) && indiceAresta <= edges.get(i).getValues().size() - 1) {
                return edges.get(i).getValues().get(indiceAresta);
            } else if (edges.get(i).getSource().equals(v)) {
                indice++;
            }
        }
        return null;
    }

    public Edge retornaAresta(Transicao t) {
        for (int i = 0; i < edges.size(); i++) {
            for (int j = 0; j < edges.get(i).getValues().size(); j++) {
                if (t.equals(edges.get(i).getValues().get(j))) {
                    return edges.get(i);
                }
            }
        }
        return null;
    }

    public Vertex retornaInicial() {
        for (int i = 0; i < vertex.size(); i++) {
            if (vertex.get(i).isInicial()) {
                return vertex.get(i);
            }
        }
        return null;
    }

}
