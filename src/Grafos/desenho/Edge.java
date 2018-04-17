package Grafos.desenho;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

public class Edge {

    private Color color = Color.WHITE; 
    private Vertex source;
    private Vertex target; 
    private int directed = 0;
    private Boolean selected = true;
    private Boolean show = true;
    private String label;
    private String fita;
    private char sentido; //'S' - Stop, 'L' - Left, 'R' - Right 
    private float xLabel;
    private float yLabel;
    private ArrayList<Transicao> values;

    
    private final int peso;

    public Edge(Vertex source, Vertex target, String label, String fita, char sentido){
        values = new ArrayList<>();
        this.source = source;
        this.target = target;
        this.label = label;
        this.fita = fita;
        this.sentido = sentido;
        this.peso = 0;
    }
    
    public Edge(Vertex source, Vertex target, int peso, int directed) {
        values = new ArrayList<>();
        this.source = source;
        this.target = target;
        this.directed = directed;
        this.peso = peso;
    }

    public Edge(Vertex source, Vertex target, String label) {
        values = new ArrayList<>();
        this.source = source;
        this.target = target;
        this.label = label;
        this.peso = 0;
    }
    
    
    
    public void draw(java.awt.Graphics2D g2, ArrayList<Edge> edges) 
    {

        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new java.awt.BasicStroke(1.0f));
        g2.setColor(this.color.darker().darker());
        
        
         Edge aux = null;
        for(int i=0; i < edges.size(); i++){
            if ((this.getTarget() == edges.get(i).getSource()) && (this.getSource() == edges.get(i).getTarget()))
                aux = edges.get(i);
        }
      
         Point a, b;
        if (this.source.getID() != this.target.getID()) 
        {
               
            if (aux == null) 
            { // reta normal
                g2.drawLine(((int) this.source.getX()), ((int) this.source.getY()), ((int) this.target.getX()), ((int) this.target.getY()));
                g2.setStroke(new java.awt.BasicStroke(1.0f));
                a = new Point((int) source.getX(), (int) source.getY());
                b = new Point((int) target.getX(), (int) target.getY());
            }
            else
            {  //curva
                Point s = new Point((int)this.source.getX(), (int)this.source.getY());
                Point t = new Point((int)this.target.getX(), (int)this.target.getY());
                //achar o ponto do meio
                int xMeio = (int)((s.getX() + t.getX())/2);
                int yMeio = (int)((s.getY() + t.getY())/2);
                Point meio;
                
                
                //achar o quadrante do aux em relacao ao x
                double dx = t.x - s.x, dy = t.y - s.y;
                double theta = Math.toDegrees(Math.atan2(dy, dx));

                if ((s.getX() <= t.getX()) && (s.getY() >= t.getY()))
                { //primeiro quadrante
                    if ((theta < 45)) 
                    {
                        meio = new Point(xMeio - 25, yMeio - 50);
                    } else 
                    {
                        meio = new Point(xMeio - 50, yMeio - 25);
                    }
                }
                else if ((s.getX() > t.getX()) && (s.getY() >= t.getY()))
                { //segundo quadrante
                    if ((theta < 135)) 
                    {
                        meio = new Point(xMeio + 50, yMeio - 25);
                    } else 
                    {
                        meio = new Point(xMeio + 25, yMeio - 50);
                    }
                }
                else if ((s.getX() > t.getX()) && (s.getY() <= t.getY()))
                { //terceiro quadrante
                   if ((theta < 225)) 
                   {
                        meio = new Point(xMeio + 25, yMeio + 50);
                    } else 
                   {
                        meio = new Point(xMeio + 50, yMeio + 25);
                    }
                }
                else 
                { //quarto quadrante
                    if ((theta < 315)) 
                    {
                        meio = new Point(xMeio - 50, yMeio + 25);
                    } else 
                    {
                        meio = new Point(xMeio - 25, yMeio + 50);
                    }
                }
                
                QuadCurve2D q = new QuadCurve2D.Float();

                q.setCurve(s, meio, t);
                g2.draw(q);                        
                meio.x += (meio.x > xMeio ? -10: 10);
                meio.y += (meio.y > yMeio ? -10: 10);
                a = meio;
                b = t;
            }
            double ux, uy, norma;
            ux = (a.getX() - b.getX());
            uy = (a.getY() - b.getY());
            norma = Math.sqrt(ux * ux + uy * uy);
            ux = ux / norma;
            uy = uy / norma;
            b.x = (int) (1 + b.getX() + target.getRay() * ux);
            b.y = (int) (1 + b.getY() + target.getRay() * uy);
            desenhaHeadSeta(g2, a, b);

            g2.setColor(Color.BLACK);
            for(int i = 0 ; i < values.size(); i++){
                Transicao t = values.get(i);
                Point m = new Point((int)b.x, (int)b.y-i*45);
                desenhaLabel(g2, a, m, t.getLabel()+" ; "+t.getFita()+" ; "+t.getSentido());
                
            }
        } else 
        { 
            g2.drawOval((int) (this.source.getX()) - 15, (int) (this.source.getY()) - 50, this.source.getRay() + 10, this.source.getRay() + 20);
            
            Point c = new Point((int)this.source.getX() - 20, (int)this.source.getY() - 50);
            Point d = new Point((int)this.target.getX() - 11, (int)this.target.getY() - 15);
            desenhaHeadSeta(g2, c, d);

            g2.setColor(Color.BLACK);
            Point l = new Point((int)this.source.getX()-10, (int)this.source.getY() - 55);
            for(int i = 0 ; i < values.size(); i++){
                Transicao t = values.get(i);
                Point m = new Point((int)this.source.getX()-10, (int)this.source.getY()- 55 -i*20);
                //desenhaLabel(g2, m, m, values.get(i).getLabel());
                //desenhaLabel(g2, m, m, values.get(i).getLabel());
                desenhaLabel(g2, m, m, t.getLabel()+" ; "+t.getFita()+" ; "+t.getSentido());


                
            }
        }
            
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
    }
    
    private void desenhaHeadSeta(Graphics2D g2, Point origem, Point destino)  
    {  
        double dy = destino.y - origem.y;  
        double dx = destino.x - origem.x;  
        double theta = Math.atan2(dy, dx);
        double phi = Math.toRadians(20);
        double rho = theta + phi;  
        int tam = 10;
        Polygon pol = new Polygon();
        pol.addPoint(destino.x, destino.y);
        pol.addPoint( (int)(destino.x - tam * Math.cos(rho)),(int)(destino.y - tam * Math.sin(rho)));
        rho = theta - phi;
        pol.addPoint( (int)(destino.x - tam * Math.cos(rho)),(int)(destino.y - tam * Math.sin(rho)));
        g2.fillPolygon(pol);
    }

    private void desenhaLabel(Graphics2D g2, Point s, Point t, String label) 
    {
        int transX = (int) ((t.x + s.x) * 0.5f); //metade da reta
        int transY = (int) ((t.y + s.y) * 0.5f); //metade da reta  
        
        xLabel = transX-label.length()*2;
        yLabel = transY -5;
        
        for(int i = 0; i < this.values.size(); i++){
            Transicao aux = this.values.get(i);
            String labelValue = aux.getLabel()+";"+aux.getFita()+";"+aux.getSentido();
            if(labelValue.equals(label)) this.values.get(i).setPoint(new Point((int)transX-label.length()*2, (int)transY-5));
        }
        
        g2.drawString(label, transX-label.length()*2, transY - 5);
    }
    
        private void desenhaTriangulo(Graphics g1, int x1, int y1, int x2, int y2) 
        {
        Graphics2D g = (Graphics2D) g1.create();
        double dx = (x2 - x1), dy = (y2 - y1);
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        len = len - 20;

        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        g.fillPolygon(new int[]{len, len - 5, len - 5, len},
                new int[]{0, -5, 5, 0}, 4);


    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getxLabel() {
        return xLabel;
    }

    public void setxLabel(float xLabel) {
        this.xLabel = xLabel;
    }

    public float getyLabel() {
        return yLabel;
    }

    public void setyLabel(float yLabel) {
        this.yLabel = yLabel;
    }

    public ArrayList<Transicao> getValues() {
        return values;
    }

    public void setValues(ArrayList<Transicao> values) {
        this.values = values;
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

    
    
    



    public int isDirected() {
        return directed;
    }

    public void setDirected(int directed) {
        this.directed = directed;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public int getPeso() {
        return peso;
    }
    
    public Boolean getShow(){
        return this.show;
    }
     
    public void setShow(Boolean op){
        this.show = op;    
    }
    
}
