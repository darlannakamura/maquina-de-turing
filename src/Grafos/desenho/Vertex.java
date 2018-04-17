package Grafos.desenho;

import java.awt.Color;
import java.util.ArrayList;

public class Vertex {

    private float x;
    private float y;
    private int ray = 25;
    private Boolean selected = false;
    private Color color = Color.GREEN;
    private String ID;
    private boolean estFinal = false;
    private boolean inicial = false;
    private Color colorSelected = Color.GREEN;
    private int position;

    public Vertex() {

    }

    public Vertex(float x, float y) {

        this.x = x;
        this.y = y;

    }

    public Vertex(float x, float y, String nomeVertice) {

        this.x = x;
        this.y = y;
        this.ID = nomeVertice;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isEstFinal() {
        return estFinal;
    }

    public void setEstFinal(boolean estFinal) {
        this.estFinal = estFinal;
    }

    public boolean isInicial() {
        return inicial;
    }

    public void setInicial(boolean inicial) {
        this.inicial = inicial;
    }

    public void draw(java.awt.Graphics2D g2) {
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new java.awt.BasicStroke(2.0f));

        if (this.ray != 0) {
            if (this.isSelected()) {
                g2.setColor(this.colorSelected);
            } else {
                g2.setColor(this.getColor());
            }
            g2.fillOval(((int) this.x) - this.getRay(), ((int) this.y) - this.getRay(), this.getRay() * 2, this.getRay() * 2);
            g2.setColor(Color.darkGray);
            if (this.isEstFinal()) {
                g2.drawOval(((int) this.x) - this.getRay() + 4, ((int) this.y) - this.getRay() + 4, this.getRay() * 2 - 8, this.getRay() * 2 - 8);
            }
            g2.drawOval(((int) this.x) - this.getRay(), ((int) this.y) - this.getRay(), this.getRay() * 2, this.getRay() * 2);
            if (this.isInicial()) {
                int[] xPoints = {(int) this.getX() - this.getRay(), (int) this.getX() - this.getRay() * 2, (int) this.getX() - this.getRay() * 2};
                int[] yPoints = {(int) this.getY(), (int) this.getY() - this.getRay(), (int) this.getY() + this.getRay()};
                g2.drawPolygon(xPoints, yPoints, 3);
            }
            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
            g2.setStroke(new java.awt.BasicStroke(3.0f));
            g2.setColor(Color.black);
            //g2.drawString(ID, x-ID.length()*3, y+ray/4);
            g2.drawString(ID, x - ID.length() * 3, y + ray / 4);
            //g2.drawString(ID, x-ID.length()*3, y-ray-3);
        }
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));

    }

//    public void draw(java.awt.Graphics2D g2) {
//        if (this.selected) {
//            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
//            g2.setStroke(new java.awt.BasicStroke(3.0f));
//        } else {
//            g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 0.2f));
//            g2.setStroke(new java.awt.BasicStroke(1.5f));
//        }           
//        if (estFinal)
//                g2.drawOval(((int) this.x) - this.getRay() + 4, ((int) this.y) - this.getRay() + 4, this.getRay() * 2 - 8, this.getRay() * 2 - 8);
//            g2.drawOval(((int) this.x) - this.getRay(), ((int) this.y) - this.getRay(), this.getRay() * 2, this.getRay() * 2);
//        if (inicial){
//                int [] xPoints = {(int)this.getX()-this.getRay(), (int)this.getX()-this.getRay()*2, (int)this.getX()-this.getRay()*2};
//                int [] yPoints = {(int)this.getY(), (int)this.getY()-this.getRay(), (int)this.getY()+this.getRay()};
//                g2.drawPolygon(xPoints, yPoints, 3);
//            }
//        
//        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
//        g2.setStroke(new java.awt.BasicStroke(3.0f));
//        g2.setColor(Color.black);
//        
//        
//        g2.setColor(this.color);
//        g2.fillOval(((int) this.x) - this.getRay(), ((int) this.y)
//                - this.getRay(), this.getRay() * 2, this.getRay() * 2);
//
//        g2.setColor(Color.BLACK);
//        g2.drawOval(((int) this.x) - this.getRay(), ((int) this.y)
//                - this.getRay(), this.getRay() * 2, this.getRay() * 2);
//        
//        g2.setColor(Color.WHITE);
//        g2.drawString(ID, (float) (this.getX()-3.0) , (float) (this.getY()+ 3.0));
//        
//        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
//
//    }
    public float getX() {
        return x;
    }

    public void setX(float X) {
        this.x = X;
    }

    public float getY() {
        return y;
    }

    public void setY(float Y) {
        this.y = Y;
    }

    public int getRay() {
        return ray;
    }

    public void setRay(int ray) {
        this.ray = ray;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean flag) {
        this.selected = flag;
    }
}
