/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automato.visual;

import Grafos.desenho.Controlador;
import Grafos.desenho.Edge;
import Grafos.desenho.Fita;
import Grafos.desenho.Graph;
import Grafos.desenho.Transicao;
import Grafos.desenho.TransicaoPorFita;
import Grafos.desenho.Validator;
import Grafos.desenho.Vertex;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TrayIcon;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author Darlan Nakamura
 */
public class View extends javax.swing.JFrame {

    public ViewPanel view;
    private Graph graph;
    private int select = 0;
    private int contador;
    private int incr = 0;
    private String path;
    private AbrirArquivo aa;
    private Fita fita;
    private Fita[] fitas;
    private String input;
    private JScrollPane scroll;
    //private transicaoTableModel model;
    private JTable transicao;
    private int[] od;
    private int xzin, yzin;
    private Validator validation;
    private boolean abriuJTableParaAlterar;
    private Controlador controlador;

    //Select 0 : Movimentar
    //Select 1 : Estados
    //Select 2 : Transições
    //Select 3 : Apagar
    //Select 4 : Inicial
    //Select 5 : Final
    //select 6 : Alterar
    /**
     * Creates new form Main
     *
     * @param quantidadeFitas
     */
    public View(int quantidadeFitas) {
        this.view = new ViewPanel();
        initComponents();
        fitas = new Fita[quantidadeFitas];
        graph = new Graph(fitas);
        abriuJTableParaAlterar = false;
        contador = 0;
        od = new int[2];
        validation = new Validator();
        controlador = new Controlador();

        this.view.addMouseMotionListener(new Mouse());
        this.view.addMouseListener(new EventoMouse());
        jButton5.setVisible(false);

        path = "";
        jMenuItem8.setEnabled(false);
        jMenuItem9.setVisible(false);

        fita0.setVisible(false);
        fita1.setVisible(false);
        fita2.setVisible(false);
        fita3.setVisible(false);
        fita4.setVisible(false);

        mostraFitasVisuais();

    }

    public void mostraFitasVisuais() {
        for (int i = 0; i < fitas.length; i++) {
            marcarVisivel(i);
        }
    }

    public void marcarVisivel(int i) {
        switch (i) {
            case 0:
                fita0.setVisible(true);
                break;
            case 1:
                fita1.setVisible(true);
                break;
            case 2:
                fita2.setVisible(true);
                break;
            case 3:
                fita3.setVisible(true);
                break;
            case 4:
                fita4.setVisible(true);
                break;

        }
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void marcaFitasSucesso() {
        for(int i = 0 ; i < fitas.length; i++){
            marcaEssaFitaDeVerde(i);
        }
    }
    
    public void marcaEssaFitaDeVerde(int indice ){
        switch(indice){
            case 0: 
                fita0.setForeground(Color.GREEN);
                fita0.setEnabled(true);
                break;
             case 1: 
                fita1.setForeground(Color.GREEN);
                fita1.setEnabled(true);
                break;
             case 2: 
                fita2.setForeground(Color.GREEN);
                fita2.setEnabled(true);
                break;
             case 3: 
                fita3.setForeground(Color.GREEN);
                fita3.setEnabled(true);
                break;
             case 4: 
                fita4.setForeground(Color.GREEN);
                fita4.setEnabled(true);
                break;
                
        }
    }
    
    public void marcaFitasFalha(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editmodel = jTable1.getModel();or.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        respostaText = new javax.swing.JLabel();
        painel = new javax.swing.JScrollPane(this.view);
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Inicial = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        qtdeIteracoes = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        fita0 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        fita4 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        fita1 = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        fita2 = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        fita3 = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        painel.setBackground(new java.awt.Color(255, 255, 255));
        painel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                painelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                painelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                painelMouseReleased(evt);
            }
        });
        painel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                painelKeyPressed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Automato/visual/mover_icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Automato/visual/new_icon.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Automato/visual/transicao_icon.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Automato/visual/delete_icon.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Inicial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Automato/visual/incial_icon.png"))); // NOI18N
        Inicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicialActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Automato/visual/final_icon.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Automato/visual/proximo_icon.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setText("FITA:");

        jLabel2.setText("Quantidade de Iterações:");

        qtdeIteracoes.setEditable(false);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Automato/visual/alterar_icon.jpeg"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(fita0);

        jScrollPane5.setViewportView(fita4);

        jScrollPane6.setViewportView(fita1);

        jScrollPane7.setViewportView(fita2);

        jScrollPane8.setViewportView(fita3);

        jMenu1.setText("Arquivo");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Abrir Arquivo");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Salvar Como");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Salvar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Salvar imagem");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Sair");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Teste");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Teste Rápido");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Teste Passo a Passo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Múltiplas Entradas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Teste Rápido Não-Deterministico");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(Inicial, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 294, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(qtdeIteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(painel, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1112, Short.MAX_VALUE)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(respostaText)
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Inicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(qtdeIteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(495, 495, 495)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(painel, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 10, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(538, 538, 538)
                        .addComponent(respostaText)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        select = 1;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        select = 0;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        select = 2;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        select = 3;
    }//GEN-LAST:event_jButton4ActionPerformed


    private void InicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicialActionPerformed
        select = 4;
    }//GEN-LAST:event_InicialActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        select = 5;
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FileSystemView fsv = FileSystemView.getFileSystemView();
        JFileChooser dialog = new JFileChooser();
        dialog.setMultiSelectionEnabled(false);
        dialog.setDialogTitle("Salvar imagem");

        dialog.setCurrentDirectory(fsv.getRoots()[0]);
        int result = dialog.showDialog(this, "Salvar");
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filename = dialog.getSelectedFile().getAbsolutePath();
                if (!filename.contains(".png")) {
                    filename = filename.concat(".png");
                }
                this.view.saveToPngImageFile(filename);
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        //Não deterministico
        String input = JOptionPane.showInputDialog(null, "Digite a entrada:");

        //fita = new Fita(input);
        //boolean resposta = graph.execucaoRapida(fita);
        boolean resposta = graph.execucaoRapidaNaoDeterministica(input);
        //fita = graph.fita;
        fitas = graph.fitas;
        int quantidadeDeIteracoes = graph.indicesFita[0].size();
        printarFitas();
        if (resposta) {
//            fitinhaText.setForeground(Color.GREEN);
//            fitinhaText.setEnabled(resposta);
            marcaFitasSucesso();
            JOptionPane.showMessageDialog(null, "Pertence a linguagem!");
            qtdeIteracoes.setText(Integer.toString(quantidadeDeIteracoes));

        } else {
//            fitinhaText.setForeground(Color.RED);
            marcaFitasFalha();
            JOptionPane.showMessageDialog(null, "Não pertence a linguagem.");
        }


    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        String input = JOptionPane.showInputDialog(null, "Digite a entrada:");
        boolean resposta = false;
        this.input = input;
        resposta = graph.execucaoPassoAPasso(input);
        fita = new Fita(input);
        System.out.println("quantidade de indicesFita:" + graph.indicesFita[0].size());
        incr = 0;
        if (resposta) {
            jButton5.setVisible(true);
            JOptionPane.showMessageDialog(null, "Para visualizar a continuação, clique em próximo.");
            qtdeIteracoes.setText(Integer.toString(incr));
        } else {
            JOptionPane.showMessageDialog(null, "Não pertence a linguagem.");
        }

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void painelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelMouseClicked

    }//GEN-LAST:event_painelMouseClicked

    private void painelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelMouseReleased

    }//GEN-LAST:event_painelMouseReleased

    private void painelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_painelMousePressed

    }//GEN-LAST:event_painelMousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (graph.deixaVerticeCinza(incr)) {
            repintar();
            //printarFita(graph.getIndicesFita()[0].get(incr));
            //printarFitas(incr);
            printarFitasPassoAPasso();
            incr++;
            qtdeIteracoes.setText(Integer.toString(incr));

        } else {
            JOptionPane.showMessageDialog(null, "Acabou a execução.");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        MultiplasEntradas me = new MultiplasEntradas();
        me.setVisible(true);
        me.setGraph(graph);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showOpenDialog(null);
        if (i == 1) {
            file.setVisible(false);

        } else {
            File arquivo = file.getSelectedFile();
            path = arquivo.getPath();

            if (!path.contains(".jff")) {
                JOptionPane.showMessageDialog(null, "Este não é um tipo de arquivo válido, por favor selecione novamente");

            } else {
                try {
                    graph = controlador.abreArquivo(path);
                    contador = graph.vertex.size();

                    jMenuItem8.setEnabled(true);

                    repintar();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_jMenuItem6ActionPerformed

    public void repintar() {
        view.cleanImage();
        painel.repaint();
    }

//    public void printarFita(Elemento elemento) {
//        fitinhaText.setContentType("text/html");
//        fita.setConteudo(elemento.getFita());
//        String html = "";
//        html += "<html><body><p style=\"font-size:20pt; \">";
//
//        for (int i = 0; i < fita.getConteudo().length(); i++) {
//            if (i == elemento.getIndice()) {
//                //fita.getConteudo()[i] = elemento.getValor();
//
//                html += "<span style=\"background-color: blue;\">" + fita.getConteudo().charAt(i) + "</span>";
//            } else {
//                html += fita.getConteudo().charAt(i);
//            }
//        }
//        html += "</body></html>";
//        fitinhaText.setText(html);
//    }
//
//    public void printarFita() {
//        fitinhaText.setContentType("text/html");
//
//        String html = "";
//        html += "<html><body><p style=\"font-size:20pt; \">";
//
//        for (int i = 0; i < fita.getConteudo().length(); i++) {
//            if (i == fita.getPonteiro()) {
//                html += "<span style=\"background-color: blue;\">" + fita.getConteudo().charAt(i) + "</span>";
//            } else {
//                html += fita.getConteudo().charAt(i);
//            }
//        }
//        html += "</body></html>";
//        fitinhaText.setText(html);
//
//    }

    public void printarFitas() {
        //String html = "";
        for (int i = 0; i < fitas.length; i++) {
            imprimeFita(i);
        }

    }
    
    public void printarFitasPassoAPasso(){
        //cada fita vai receber seu respectivo snapshot:
        for ( int i = 0 ; i < fitas.length; i++){
            Elemento e = graph.indicesFita[i].remove();
            imprimeFitaPassoAPasso(i, e);
        }
    }
    
    public void imprimeFitaPassoAPasso(int indice, Elemento e){
         switch(indice){
            case 0:
                fita0.setContentType("text/html");
                fita0.setText(retornaHtmlParaSetarNaFitaPassoAPasso(e));
                break;
            case 1:
                fita1.setContentType("text/html");
                fita1.setText(retornaHtmlParaSetarNaFitaPassoAPasso(e));
                break;
            case 2:
                fita2.setContentType("text/html");
                fita2.setText(retornaHtmlParaSetarNaFitaPassoAPasso(e));
                break;
            case 3:
                fita3.setContentType("text/html");
                fita3.setText(retornaHtmlParaSetarNaFitaPassoAPasso(e));
                break;
            case 4:
                fita4.setContentType("text/html");
                fita4.setText(retornaHtmlParaSetarNaFitaPassoAPasso(e));
                break;
        }
    }

    public void imprimeFita(int indice){
        switch(indice){
            case 0:
                fita0.setContentType("text/html");
                fita0.setText(retornaHtmlParaSetarNaFita(indice));
                break;
            case 1:
                fita1.setContentType("text/html");
                fita1.setText(retornaHtmlParaSetarNaFita(indice));
                break;
            case 2:
                fita2.setContentType("text/html");
                fita2.setText(retornaHtmlParaSetarNaFita(indice));
                break;
            case 3:
                fita3.setContentType("text/html");
                fita3.setText(retornaHtmlParaSetarNaFita(indice));
                break;
            case 4:
                fita4.setContentType("text/html");
                fita4.setText(retornaHtmlParaSetarNaFita(indice));
                break;
        }
    }
    public String retornaHtmlParaSetarNaFita(int indice) {
        String html = "";

        html += "<html><body><p style=\"font-size:20pt; \">";

        for (int i = 0; i < fitas[indice].getConteudo().length(); i++) {
            if (i == fitas[indice].getPonteiro()) {
                html += "<span style=\"background-color: blue;\">" + fitas[indice].getConteudo().charAt(i) + "</span>";
            } else {
                html += fitas[indice].getConteudo().charAt(i);
            }
        }
        html += "</body></html>";
        return html;
    }
    
    public String retornaHtmlParaSetarNaFitaPassoAPasso(Elemento elemento) {
        String html = "";
       
        html += "<html><body><p style=\"font-size:20pt; \">";

        for (int i = 0; i < elemento.getFita().length(); i++) {
            if (i == elemento.getIndice()) {
                html += "<span style=\"background-color: blue;\">" + elemento.getFita().charAt(i) + "</span>";
            } else {
                html += elemento.getFita().charAt(i);
            }
        }
        html += "</body></html>";
        return html;
    }


    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        //Salvar arquivo
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int abriu = file.showSaveDialog(null);
        //String nomeArquivo = JOptionPane.showInputDialog(null, "Digite o nome do arquivo:");
        String nomeArquivo;
        if (abriu == 1) {
            nomeArquivo = "";
        } else {
            File arquivo = file.getSelectedFile();
            nomeArquivo = arquivo.getPath();
            String path = "";
            if (!nomeArquivo.contains(".jff")) {
                path = nomeArquivo + ".jff";
            }

            controlador.salvarArquivo(path, graph);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        //Salvar
        //controlador.salvarArquivo(path, graph);

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void painelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_painelKeyPressed

    }//GEN-LAST:event_painelKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        select = 6;
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        //Não deterministico
        String input = JOptionPane.showInputDialog(null, "Digite a entrada:");

        fita = new Fita(input);

        //boolean resposta = graph.execucaoRapida(fita);
        boolean resposta = graph.execucaoRapidaNaoDeterministica(input);
        fita = graph.fita;
        //printarFitas();
        if (resposta) {
//            fitinhaText.setForeground(Color.GREEN);
//            fitinhaText.setEnabled(resposta);
            marcaFitasSucesso();
            JOptionPane.showMessageDialog(null, "Pertence a linguagem!");
            qtdeIteracoes.setText(Integer.toString(graph.indicesFita[0].size()));

        } else {
//            fitinhaText.setForeground(Color.RED);
            marcaFitasFalha();
            JOptionPane.showMessageDialog(null, "Não pertence a linguagem.");
        }


    }//GEN-LAST:event_jMenuItem9ActionPerformed

    //considerando que há apenas um único estado final: Funcionando
    private boolean tiverEstadosFinaisNaoMarcados(ArrayList<Edge> arestas) {

        for (int i = 0; i < arestas.size(); i++) {
            if (arestas.get(i).getTarget().isEstFinal() && !arestas.get(i).getTarget().isSelected()) {
                return true;
            }
        }
        return false;
    }

    public class EventoMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent evt) {

            switch (select) {
                case 4: //marca inicial
                    marcaInicial();

                    break;
                case 5: //marca final
                    marcaFinal();

                    break;
                case 6: //alterar
                    alterarEstadoOuTransicao(evt);
                    break;

            }
            tiraOFocoDoTableDaTransicao(evt);
            repintar();

//            if (select == 4) {  //Se tiver selecionado o Inciial:
//
//                if (view.getSelectedVertex().isInicial()) {
//                    graph.setInicial(view.getSelectedVertex(), false);
//                } else {
//                    for (int i = 0; i < graph.vertex.size(); i++) {
//                        graph.setInicial(graph.vertex.get(i), false);
//                    }
//                    graph.setInicial(view.getSelectedVertex(), true);
//                }
//
//                repintar();
//
//            }
//            if (select == 5) {  //Se tiver selecionado o final:
//
//                if (view.getSelectedVertex().isEstFinal()) {
//                    graph.setFinal(view.getSelectedVertex(), false);
//                } else {
//                    graph.setFinal(view.getSelectedVertex(), true);
//                }
//
//                repintar();
//            }
//            if (select == 6) {
//                if (!graph.edges.isEmpty()) {
//                    Point p = graph.edges.get(0).getValues().get(0).getPoint();
//                    System.out.println("Mouse: " + evt.getX() + "," + evt.getY() + " Transição:" + p.x + "," + p.y);
//                    if (view.getSelectedVertex() != null) {
//                        System.out.println("Vertice selecionado:" + view.getSelectedVertex().getID());
//                    } else {
//                        System.out.println("Vertice selecionado: nenhum");
//                    }
//                    if (view.getSelectedTransicao() != null) {
//                        Transicao t = view.getSelectedTransicao();
//                        System.out.println("Transicao selecionada:" + t.getLabel() + " ; " + t.getFita() + " ; " + t.getSentido());
//                    } else {
//                        System.out.println("Transicao selecionada: nenhuma");
//                    }
//
//                    if (!graph.edges.isEmpty()) {
//                        for (int i = 0; i < graph.edges.get(0).getValues().size(); i++) {
//                            System.out.println("Transicao " + i + ":" + graph.edges.get(0).getValues().get(i).getPoint().x + "," + graph.edges.get(0).getValues().get(i).getPoint().y);
//                        }
//                    }
//
//                }
//
//                if (view.getSelectedVertex() != null) {
//                    alterarEstado();
//                }
//                if (view.getSelectedTransicao() != null) {
//                    alterarTransicao(evt);
//                }
//                repintar();
//            }
//            if (transicao != null && transicao.isVisible() && !abriuJTableParaAlterar) {
//                if (evt.getX() < transicao.getX() || transicao.getX() + transicao.getSize().width < evt.getX() || evt.getY() < transicao.getY() || transicao.getY() + transicao.getSize().height < evt.getY()) {
//                    if (select == 6) {
//                        updateTransition(evt);
//                    } else {
//                        newTransition(evt);
//                    }
//                }
//            }
        }

        @Override
        public void mousePressed(MouseEvent evt) {

            marcaEstadoOuTransicao(evt);

            switch (select) {
                case 1:
                    criarEstado(evt);
                    break;

                case 3:
                    excluirEstadoOuAresta();
                    break;

                case 6:
                    alterarEstadoOuTransicao(evt);
                    break;

            }
            repintar();

//            //criando estados
//            if (select == 1) {
//                criarEstado(evt);
//            }
//            //excluindo estados
//            if (select == 3) {
//                if (view.getSelectedVertex() != null) {
//                    excluirEstado();
//                }
//                if (view.getSelectedTransicao() != null) {
//                    excluirAresta();
//                }
//
//            }
//            if (select == 6) {
//                if (!graph.edges.isEmpty()) {
//                    Point p = graph.edges.get(0).getValues().get(0).getPoint();
//                    System.out.println("Mouse: " + evt.getX() + "," + evt.getY() + " Transição:" + p.x + "," + p.y);
//                    if (view.getSelectedVertex() != null) {
//                        System.out.println("Vertice selecionado:" + view.getSelectedVertex().getID());
//                    } else {
//                        System.out.println("Vertice selecionado: nenhum");
//                    }
//                    if (view.getSelectedTransicao() != null) {
//                        Transicao t = view.getSelectedTransicao();
//                        System.out.println("Transicao selecionada:" + t.getLabel() + " ; " + t.getFita() + " ; " + t.getSentido());
//                    } else {
//                        System.out.println("Transicao selecionada: nenhuma");
//                    }
//                    if (!graph.edges.isEmpty()) {
//                        for (int i = 0; i < graph.edges.get(0).getValues().size(); i++) {
//                            System.out.println("Transicao " + i + ":" + graph.edges.get(0).getValues().get(i).getPoint().x + "," + graph.edges.get(0).getValues().get(i).getPoint().y);
//                        }
//                    }
//                    if (view.getSelectedVertex() != null) {
//                        alterarEstado();
//                    }
//                    if (view.getSelectedTransicao() != null) {
//                        alterarTransicao(evt);
//                    }
//                    repintar();
//                }
//            }
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
            int origem, destino;

            origem = -1;

            switch (select) {
                case 2:
                    view.s.setLine(0, 0, 0, 0);
                    view.getTblTransicao();
                    origem = marcaVerticeOrigem();
                    tiraSelecaoDoEstado();
                    desenharTransicao(evt, origem);
                    repintar();
                    break;
            }

//            if (select == 2) { //add transição
//                view.s.setLine(0, 0, 0, 0);
//                view.getTblTransicao();
//                for (int i = 0; i < graph.vertex.size(); i++) {
//                    if (graph.vertex.get(i).isSelected()) {
//                        origem = i;
//                        break;
//                    }
//                }
//            }
//            //tirar a seleção do estado
//            if ((graph != null) && (!graph.vertex.isEmpty()) && (view.getSelectedVertex() != null)) {
//                view.getSelectedVertex().setSelected(false);
//                repintar();
//            }
            //desenhar transição
//            if (select == 2) {
//                //verifica onde soltou o mouse pra encontrar o estado destino
//                if ((graph != null) && (!graph.vertex.isEmpty())) {
//                    for (int i = graph.vertex.size() - 1; i >= 0; i--) {
//                        float x = graph.vertex.get(i).getX(),
//                                y = graph.vertex.get(i).getY(),
//                                ray = graph.vertex.get(i).getRay();
//                        if ((x + ray > evt.getX()) && (x - ray < evt.getX()) && (y + ray > evt.getY()) && (y - ray < evt.getY())) {
//                            destino = i;
//                            break;
//                        }
//                    }
//                }
//                //verifica se soltou o mouse em algum estado
//                if (destino != -1) {
//                    if (!transicao.isVisible()) {
//                        od[0] = origem;
//                        od[1] = destino;
//                    }
//                    repintar();
//                    xzin = evt.getX();
//                    yzin = evt.getY();
//                    transicao.setLocation(evt.getX(), evt.getY());
//                    transicao.setVisible(true);
//
//                }
//            }
        }

        public void desenharTransicao(MouseEvent evt, int origem) {
            int destino;
            destino = encontraEstadoDeDestino(evt);
            verificaSeSoltouOMouseEmAlgumEstado(evt, origem, destino);
        }

        public void verificaSeSoltouOMouseEmAlgumEstado(MouseEvent evt, int origem, int destino) {
            //verifica se soltou o mouse em algum estado
            if (destino != -1) {
                if (!transicao.isVisible()) {
                    od[0] = origem;
                    od[1] = destino;
                }
                repintar();
                xzin = evt.getX();
                yzin = evt.getY();
                transicao.setLocation(evt.getX(), evt.getY());
                transicao.setVisible(true);

            }
        }

        public int encontraEstadoDeDestino(MouseEvent evt) {
            int destino = -1;
            //verifica onde soltou o mouse pra encontrar o estado destino
            if ((graph != null) && (!graph.vertex.isEmpty())) {
                for (int i = graph.vertex.size() - 1; i >= 0; i--) {
                    float x = graph.vertex.get(i).getX(),
                            y = graph.vertex.get(i).getY(),
                            ray = graph.vertex.get(i).getRay();
                    if ((x + ray > evt.getX()) && (x - ray < evt.getX()) && (y + ray > evt.getY()) && (y - ray < evt.getY())) {
                        destino = i;
                        break;
                    }
                }
            }

            return destino;
        }

        public void tiraSelecaoDoEstado() {
            if ((graph != null) && (!graph.vertex.isEmpty()) && (view.getSelectedVertex() != null)) {
                view.getSelectedVertex().setSelected(false);
//                repintar();
            }
        }

        public int marcaVerticeOrigem() {
            int origem = 0;
            for (int i = 0; i < graph.vertex.size(); i++) {
                if (graph.vertex.get(i).isSelected()) {
                    origem = i;
                    break;
                }
            }
            return origem;
        }

        public void marcaEstadoOuTransicao(MouseEvent evt) {
            if ((graph != null) && (!graph.vertex.isEmpty())) {
                marcarEstado(evt);
                marcarTransicoes(evt);

            }
        }

        public void excluirEstadoOuAresta() {
            if (view.getSelectedVertex() != null) {
                excluirEstado();
            }
            if (view.getSelectedTransicao() != null) {
                excluirAresta();
            }
        }

        public Edge getTransicao() {
            String string = JOptionPane.showInputDialog(jMenu1, "Transição:", "Digite a Transição:", 0);

            String[] array = string.split(";");
            char sentido = array[2].charAt(0);

            return new Edge(null, null, array[0], array[1], sentido);

        }

        public void marcaInicial() {
            if (view.getSelectedVertex().isInicial()) {
                graph.setInicial(view.getSelectedVertex(), false);
            } else {
                for (int i = 0; i < graph.vertex.size(); i++) {
                    graph.setInicial(graph.vertex.get(i), false);
                }
                graph.setInicial(view.getSelectedVertex(), true);
            }
        }

        public void marcaFinal() {
            if (view.getSelectedVertex().isEstFinal()) {
                graph.setFinal(view.getSelectedVertex(), false);
            } else {
                graph.setFinal(view.getSelectedVertex(), true);
            }

        }

        public void alterarEstadoOuTransicao(MouseEvent evt) {
            if (!graph.edges.isEmpty()) {
                Point p = graph.edges.get(0).getValues().get(0).getPoint();
                System.out.println("Mouse: " + evt.getX() + "," + evt.getY() + " Transição:" + p.x + "," + p.y);
                if (view.getSelectedVertex() != null) {
                    System.out.println("Vertice selecionado:" + view.getSelectedVertex().getID());
                } else {
                    System.out.println("Vertice selecionado: nenhum");
                }
                if (view.getSelectedTransicao() != null) {
                    Transicao t = view.getSelectedTransicao();
                    //System.out.println("Transicao selecionada:" + t.getLabel() + " ; " + t.getFita() + " ; " + t.getSentido());

                } else {
                    System.out.println("Transicao selecionada: nenhuma");
                }

                if (!graph.edges.isEmpty()) {
                    for (int i = 0; i < graph.edges.get(0).getValues().size(); i++) {
                        System.out.println("Transicao " + i + ":" + graph.edges.get(0).getValues().get(i).getPoint().x + "," + graph.edges.get(0).getValues().get(i).getPoint().y);
                    }
                }

            }

            if (view.getSelectedVertex() != null) {
                alterarEstado();
            }
            if (view.getSelectedTransicao() != null) {
                alterarTransicao(evt);
            }
        }

        public void tiraOFocoDoTableDaTransicao(MouseEvent evt) {
            if (transicao != null && transicao.isVisible() && !abriuJTableParaAlterar) {
                if (evt.getX() < transicao.getX() || transicao.getX() + transicao.getSize().width < evt.getX() || evt.getY() < transicao.getY() || transicao.getY() + transicao.getSize().height < evt.getY()) {
                    if (select == 6) {
                        //updateTransition(evt);
                    } else {
                        newTransition(evt);
                    }
                }
            }
        }

//        public void updateTransition(MouseEvent evt) {
//            if (transicao.isEditing()) {
//                transicao.getCellEditor().stopCellEditing();
//            }
//            transicao.setVisible(false);
//            String[] array = new String[3];
//            array[0] = ((String) (view.getTblTransicao().getValueAt(0, 0))).trim();
//            if (array[0].equals("")) {
//                array[0] = "VAZIO";
//            }
//            array[1] = ((String) (view.getTblTransicao().getValueAt(0, 1))).trim();
//            if (array[1].equals("")) {
//                array[1] = "VAZIO";
//            }
//            array[2] = ((String) (view.getTblTransicao().getValueAt(0, 2))).trim();
//            if (array[2].equals("")) {
//                array[2] = "R";
//            }
//            painel.updateUI();
//            view.getTableModel().limpar();
//
//            if (array != null) {
//                if (validation.verifica(array, 0) && validation.verifica(array, 1) && validation.verifica(array, 2)) {
//                    //graph.addEdge(new Edge(graph.vertex.get(od[0]), graph.vertex.get(od[1]), array[0], array[1], array[2].charAt(0)));
//
//                    Transicao t = view.getSelectedTransicao();
//                    t.setLabel(array[0]);
//                    t.setFita(array[1]);
//                    t.setSentido(array[2].charAt(0));
//
//                    repintar();
//                    transicao.setLocation(evt.getX(), evt.getY());
//
//                } else {
//                    JOptionPane.showMessageDialog(null, "Insira entrada/saída/direção corretamente!");
//                }
//
//            }
//            repintar();
//        }
        public String[][] pegaValoresDaTable(int quantidadeLinha, int quantidadeColuna) {
            String[][] array = new String[quantidadeLinha][quantidadeColuna];
            for (int i = 0; i < quantidadeLinha; i++) {
                for (int j = 0; j < quantidadeColuna; j++) {
                    array[i][j] = ((String) (view.getTblTransicao().getValueAt(i, j))).trim();
                    if (array[i][j].equals("") && j != 2) {
                        array[i][j] = "VAZIO";
                    } else if (j == 2 && array[i][j].equals("VAZIO")) {
                        array[i][j] = "R";
                    }
                }
            }

            return array;
        }

        public TransicaoPorFita[] criaTransicoes(String[][] array) {
            TransicaoPorFita[] transicoes = new TransicaoPorFita[array.length];
            for (int i = 0; i < array.length; i++) {
                TransicaoPorFita t = new TransicaoPorFita(array[i][0], array[i][1], array[i][2].charAt(0));
                transicoes[i] = t;
            }

            return transicoes;
        }

        public void newTransition(MouseEvent evt) {
            if (transicao.isEditing()) {
                transicao.getCellEditor().stopCellEditing();
            }
            transicao.setVisible(false);
            String[][] array = pegaValoresDaTable(fitas.length, 3);

//            array[0] = ((String) (view.getTblTransicao().getValueAt(0, 0))).trim();
//            if (array[0].equals("")) {
//                array[0] = "VAZIO";
//            }
//            array[1] = ((String) (view.getTblTransicao().getValueAt(0, 1))).trim();
//            if (array[1].equals("")) {
//                array[1] = "VAZIO";
//            }
//            array[2] = ((String) (view.getTblTransicao().getValueAt(0, 2))).trim();
//            if (array[2].equals("")) {
//                array[2] = "R";
//            }
            painel.updateUI();
            view.getTableModel().limpar();

            if (array != null) {
                //if (validation.verifica(array, 0) && validation.verifica(array, 1) && validation.verifica(array, 2)) {
                if (validation.verificaEntradas(array)) {
                    TransicaoPorFita[] transicoes = criaTransicoes(array);

                    Edge e = new Edge(graph.vertex.get(od[0]), graph.vertex.get(od[1]), transicoes);
                    graph.addEdge(e);
                    //graph.addEdge(new Edge(graph.vertex.get(od[0]), graph.vertex.get(od[1]), array[0], array[1], array[2].charAt(0)));

                    repintar();
                    transicao.setLocation(evt.getX(), evt.getY());

                } else {
                    JOptionPane.showMessageDialog(null, "Insira entrada/saída/direção corretamente!");
                }

            }
            repintar();
        }

        public void criarEstado(MouseEvent evt) {
//            if (graph == null) {
//                graph = new Graph();
//            }
            String nomeVertice = "q" + Integer.toString(contador);
            contador++;
            graph.addVertex(evt.getX(), evt.getY(), nomeVertice);
            view.setSelectedVertex(null);
            view.selectedVertex = null;
            repintar();
        }

        public void marcarEstado(MouseEvent evt) {
            boolean passou = false;
            for (int i = 0; i < graph.vertex.size(); i++) {
                float x = graph.vertex.get(i).getX(),
                        y = graph.vertex.get(i).getY(),
                        ray = graph.vertex.get(i).getRay();
                if ((x + ray > evt.getX()) && (x - ray < evt.getX()) && (y + ray > evt.getY()) && (y - ray < evt.getY())) {

                    view.setSelectedVertex(graph.vertex.get(i));
                    view.setSelectedEdge(null);
                    view.setSelectedTransicao(null);
                    view.getSelectedVertex().setSelected(true);
                    passou = true;
                    break;
                }
            }

        }

        public void marcarTransicoes(MouseEvent evt) {
            boolean passou = false;
            for (int i = 0; i < graph.edges.size(); i++) {
                //for(int j = 0 ; j < graph.edges.)
                for (int j = 0; j < graph.edges.get(i).getValues().size(); j++) {
                    float x = (float) graph.edges.get(i).getValues().get(j).getPoint().getX(),
                            y = (float) graph.edges.get(i).getValues().get(j).getPoint().getY(),
                            ray = 25;
                    if ((x + ray > evt.getX()) && (x - ray < evt.getX()) && (y + ray > evt.getY()) && (y - ray < evt.getY())) {

                        view.setSelectedEdge(graph.edges.get(i));
                        view.setSelectedTransicao(graph.edges.get(i).getValues().get(j));
                        view.setSelectedVertex(null);

                        view.getSelectedEdge().setSelected(true);
                        passou = true;
                        break;
                    }
                }
            }

        }

        public void alterarEstado() {
            Vertex e = view.getSelectedVertex();
            String newLabel = JOptionPane.showInputDialog(null, "", e.getLabel());
            if (newLabel != null) {
                e.setLabel(newLabel);
            }
        }

        public void alterarTransicao(MouseEvent evt) {
            Transicao t = view.getSelectedTransicao();
            //view.alterarTbl(t);
        }

        public void excluirEstado() {
            //ou seja tiver um vértice selecionado
            graph.excluirVertice(view.getSelectedVertex());

            repintar();
        }

        public void excluirAresta() {
            //ou seja tiver uma transição selecionada
            graph.excluirAresta(view.getSelectedTransicao());

            repintar();
        }

    }

    public class Mouse extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent e) { //movimento de arrastar mouse com obtão esquerdo pressionado

            switch (select) {
                case 0:
                    //arrastar estado com botao esquerdo do mouse
                    marcaNovasPosicoesDosVertices(e);
                    break;

                case 2:
                    criarLinha(e);
                    repintar();
                    break;
            }

            //arrastar estado com botao esquerdo do mouse
//            if (select == 0) {
//                for (int i = 0; i < graph.vertex.size(); i++) {
//                    if (view.selectedVertex != null) {
//                        if (view.selectedVertex.getID().equals(graph.vertex.get(i).getID())) {
//                            graph.vertex.get(i).setX(e.getX());
//                            graph.vertex.get(i).setY(e.getY());
//                            repintar();
//                            break;
//                        }
//
//                    }
//                }
//            }
//            if (select == 2) {
//
//                if (view.selectedVertex != null) {
//                    view.s.setLine(e.getX(), e.getY(), view.selectedVertex.getX(), view.selectedVertex.getY());
//                }
//                //System.out.println("X: " + e.getX() + "Y:" + e.getY());
//                repintar();
////                
//            }
        }

        public void marcaNovasPosicoesDosVertices(MouseEvent e) {
            for (int i = 0; i < graph.vertex.size(); i++) {
                if (view.selectedVertex != null) {
                    if (view.selectedVertex.getID().equals(graph.vertex.get(i).getID())) {
                        graph.vertex.get(i).setX(e.getX());
                        graph.vertex.get(i).setY(e.getY());
                        repintar();
                        break;
                    }

                }
            }
        }

        public void criarLinha(MouseEvent e) {
            if (view.selectedVertex != null) {
                view.s.setLine(e.getX(), e.getY(), view.selectedVertex.getX(), view.selectedVertex.getY());
            }
            //System.out.println("X: " + e.getX() + "Y:" + e.getY());

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View(1).setVisible(true);
            }
        });
    }

    public class transicaoTableModel extends AbstractTableModel {

        List<TransicaoPorFita> dados_transicao;
        private String[] colunas = new String[]{"Leia", "Escreva", "Vá para"};

        public transicaoTableModel() {
            dados_transicao = new ArrayList();
        }

        public transicaoTableModel(List<TransicaoPorFita> dado) {
            dados_transicao = new ArrayList(dado);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public int getRowCount() {
            return dados_transicao.size();

        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        public String getColumnName(int indice) {
            return colunas[indice];
        }

        @Override
        public String getValueAt(int rowIndex, int columnIndex) {
            TransicaoPorFita dados = dados_transicao.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return dados.getValue();
                case 1:
                    return dados.getFita();
                case 2:
                    return Character.toString(dados.getSentido());
                default:
                    // Não deve ocorrer, pois só existem 2 colunas
                    throw new IndexOutOfBoundsException("columnIndex out of bounds");
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            TransicaoPorFita dado = dados_transicao.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    dado.setValue((String) aValue);
                    break;
                case 1:
                    dado.setFita((String) aValue);
                    break;
                case 2:
                    dado.setSentido(((String) aValue).charAt(0));
                    break;
                default:
                    // Não deve ocorrer, pois só existem 2 colunas
                    throw new IndexOutOfBoundsException("columnIndex out of bounds " + columnIndex);
            }

            fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
        }

        // Retorna o sócio referente a linha especificada
        public TransicaoPorFita get(int indiceLinha) {
            return dados_transicao.get(indiceLinha);
        }

        // Adiciona o sócio especificado ao modelo
        public void addTransicao(TransicaoPorFita transicao) {
            // Adiciona o registro.
            dados_transicao.add(transicao);

            // Pega a quantidade de registros e subtrai 1 para
            // achar o último índice. A subtração é necessária
            // porque os índices começam em zero.
            int ultimoIndice = getRowCount() - 1;

            // Notifica a mudança.
            fireTableRowsInserted(ultimoIndice, ultimoIndice);
        }

        // Remove a transicao da linha especificada.
        public void removeTransicao(int indiceLinha) {
            // Remove o registro.
            dados_transicao.remove(indiceLinha);

            // Notifica a mudança.
            fireTableRowsDeleted(indiceLinha, indiceLinha);
        }

        // Adiciona uma lista de sócios no final da lista.
        public void addListaDeTransicaos(List<TransicaoPorFita> transicao) {
            // Pega o tamanho antigo da tabela, que servirá
            // como índice para o primeiro dos novos registros
            int indice = getRowCount();

            // Adiciona os registros.
            dados_transicao.addAll(transicao);

            // Notifica a mudança.
            fireTableRowsInserted(indice, indice + transicao.size());
        }

        public void limparTransicoes() {
            Transicao e = new Transicao();
            for (int i = 0; i < dados_transicao.size(); i++) {

            }
        }

        public ArrayList<TransicaoPorFita> limpaTransicaoPorFita() {
            ArrayList<TransicaoPorFita> transicoes = new ArrayList<>();
            for (int i = 0; i < fitas.length; i++) {
                TransicaoPorFita t = new TransicaoPorFita("", "", 'S');
                transicoes.add(t);
            }
            return transicoes;
        }

        // Remove todos os registros.
        public void limpar() {
            // Remove todos os elementos da lista de sócios.
            dados_transicao.clear();
            dados_transicao = limpaTransicaoPorFita();
//            TransicaoPorFita e = new TransicaoPorFita("", "", 'S');
//            dados_transicao.add(e);

            // Notifica a mudança.
            fireTableDataChanged();
        }

    }

    public final class ViewPanel extends JPanel {

        private java.awt.Color color = java.awt.Color.RED;
        private Vertex markedVertex;
        private Vertex selectedVertex;
        private Edge selectedEdge;
        private Transicao selectedTransicao;
        private ArrayList<Vertex> selectedVertices;
        private BufferedImage imageBuffer;
        Vertex verticeSelecionado = null;
        private Line2D.Float s;
        private transicaoTableModel model;

        public ViewPanel() {

            this.setBackground(java.awt.Color.WHITE);
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            s = new Line2D.Float();

        }

        private JTable getTblTransicao() {
            if (transicao == null) {
                transicao = new JTable();

                transicao.setModel(getTableModel());
                transicao.addComponentListener(new ComponentListener() {

                    @Override
                    public void componentResized(ComponentEvent e) {

                    }

                    @Override
                    public void componentMoved(ComponentEvent e) {
                        if (transicao.getLocation().getX() == 5 && transicao.getLocation().getY() == 5) {
                            transicao.setLocation(xzin / 2, yzin);
                        }
                    }

                    @Override
                    public void componentShown(ComponentEvent e) {

                    }

                    @Override
                    public void componentHidden(ComponentEvent e) {

                    }
                });

                this.add(transicao);
                transicao.setBackground(new Color(230, 230, 255));
                transicao.setLocation(xzin, yzin);
                transicao.setVisible(false);

                System.out.println("Setei a table");
            }
            return transicao;
        }

        private JTable alterarTbl(TransicaoPorFita t) {
            // if (transicao == null) {
            //transicao = new JTable();
            model = getTableModel(t);
            transicao.setModel(model);
            transicao.addComponentListener(new ComponentListener() {

                @Override
                public void componentResized(ComponentEvent e) {

                }

                @Override
                public void componentMoved(ComponentEvent e) {
                    if (transicao.getLocation().getX() == 5 && transicao.getLocation().getY() == 5) {
                        transicao.setLocation(xzin / 2, yzin);
                    }
                }

                @Override
                public void componentShown(ComponentEvent e) {

                }

                @Override
                public void componentHidden(ComponentEvent e) {

                }
            });

            //this.add(transicao);
            transicao.setBackground(new Color(230, 230, 255));
            transicao.setVisible(true);
            //transicao.setLocation(t.getPoint());
            System.out.println("Table alterada");

            return transicao;
        }

        private transicaoTableModel getTableModel() {
            if (model == null) {
                model = new transicaoTableModel(criaTransicao());
                System.out.println("Criei o model\n");
            }
            return model;
        }

        private transicaoTableModel getTableModel(TransicaoPorFita t) {

            List<TransicaoPorFita> l = new ArrayList<>();
            l.add(t);
            if (t.getValue().equals("VAZIO")) {
                t.setValue("");
            }
            if (t.getFita().equals("VAZIO")) {
                t.setFita("");
            }
            model = new transicaoTableModel(l);
            System.out.println("Criei o model\n");

            return model;
        }

        // cria uma lista de transicao
        private List<TransicaoPorFita> criaTransicao() {
            List<TransicaoPorFita> dado = new ArrayList<>();
            for (int i = 0; i < fitas.length; i++) {
                TransicaoPorFita um = new TransicaoPorFita("", "", 'S');

                dado.add(um);

            }

            return dado;
        }

        public Edge getSelectedEdge() {
            return selectedEdge;
        }

        public void setSelectedEdge(Edge selectedEdge) {
            this.selectedEdge = selectedEdge;
        }

        @Override
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

            if (graph != null && this.imageBuffer == null) {
                this.imageBuffer = new BufferedImage(graph.getSize().width + 1,
                        graph.getSize().height + 1, BufferedImage.TYPE_INT_RGB);

                java.awt.Graphics2D g2Buffer = this.imageBuffer.createGraphics();
                g2Buffer.setColor(this.getBackground());
                g2Buffer.fillRect(0, 0, graph.getSize().width + 1, graph.getSize().height + 1);

                g2Buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//                if(view.getSelectedVertex() != null){
//                    int x1, y1, a;
//                    int x2, y2, x, y;
//                   x1 = Math.round(view.selectedVertex.getX()) ;
//                   y1 = Math.round(view.selectedVertex.getY());
//                   
//                   x2 = mouseX;
//                   y2 = mouseY;
//                   
//                   a = ((y2-y1)/(x2-x1));
//                  for(x = x1; x < x2; x++){
//                    y = a*x - a*x1 + y1;
//                    imageBuffer.setRGB(x, y, 100);
//                    imageBuffer.dr
//                  }
//                }             
                graph.draw(g2Buffer);
                if (view.s != null) {
                    g2Buffer.setStroke(new java.awt.BasicStroke(1.0f));
                    g2Buffer.draw(view.s);
                }
//                Shape line;
//                line = new Shape() {
//                    @Override
//                    public Rectangle getBounds() {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public Rectangle2D getBounds2D() {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public boolean contains(double x, double y) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public boolean contains(Point2D p) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public boolean intersects(double x, double y, double w, double h) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public boolean intersects(Rectangle2D r) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public boolean contains(double x, double y, double w, double h) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public boolean contains(Rectangle2D r) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public PathIterator getPathIterator(AffineTransform at) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public PathIterator getPathIterator(AffineTransform at, double flatness) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//                };

//                if(view.getSelectedVertex() != null )
//                g2Buffer.drawLine(Math.round(view.selectedVertex.getX()), Math.round(view.selectedVertex.getY()), mouseX, mouseY);
//                
            }

            if (this.imageBuffer != null) {
                g2.drawImage(this.imageBuffer, 0, 0, null);
            }
        }

        public void saveToPngImageFile(String filename) throws IOException {
            try {
                ImageIO.write(this.imageBuffer, "png", new File(filename));
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void setGraph(Graph graph) {
            if (graph != null) {
                this.setPreferredSize(new Dimension(graph.getSize().width * 2,
                        graph.getSize().height * 2));
                this.setSize(new Dimension(graph.getSize().width * 2,
                        graph.getSize().height * 2));

                this.cleanImage();
                this.repaint();
            }
        }

        public Vertex getMarkedVertex() {
            return markedVertex;
        }

        public void cleanImage() {
            this.imageBuffer = null;
        }

        public void adjustPanel() {
            float iniX = graph.getVertex().get(0).getX();
            float iniY = graph.getVertex().get(0).getY();
            float max_x = iniX, max_y = iniX;
            float min_x = iniY, min_y = iniY;
            int zero = graph.getVertex().get(0).getRay() * 5 + 10;

            for (int i = 1; i < graph.getVertex().size(); i++) {
                float x = graph.getVertex().get(i).getX();
                if (max_x < x) {
                    max_x = x;
                } else if (min_x > x) {
                    min_x = x;
                }

                float y = graph.getVertex().get(i).getY();
                if (max_y < y) {
                    max_y = y;
                } else if (min_y > y) {
                    min_y = y;
                }
            }

            for (Vertex v : graph.getVertex()) {
                v.setX(v.getX() + zero - min_x);
                v.setY(v.getY() + zero - min_y);
            }

            Dimension d = this.getSize();
            d.width = 2 * ((int) max_x + zero);
            d.height = 2 * ((int) max_y + zero);
            this.setSize(d);
            this.setPreferredSize(d);
        }

        public void markVertices(ArrayList<Vertex> vertices) {
            if (vertices != null) {
                this.cleanMarkedVertices(false);

                for (Vertex v : vertices) {
                    v.setSelected(true);
                }
                this.cleanImage();
                this.repaint();
            }
        }

        public void cleanMarkedVertices(boolean cleanVertex) {
            if (graph != null) {
                this.markedVertex = null;

                for (Vertex vertex : graph.getVertex()) {
                    vertex.setSelected(false);
                }
            }

            this.cleanImage();
            this.repaint();
        }

        @Override
        public void setFont(java.awt.Font font) {

        }

        @Override
        public java.awt.Font getFont() {
            return null;
        }

        @Override
        public void setBackground(Color bg) {
            super.setBackground(bg);

        }

        public Vertex getSelectedVertex() {
            return selectedVertex;
        }

        public void setSelectedVertex(Vertex selectedVertex) {
            this.selectedVertex = selectedVertex;
        }

        public Transicao getSelectedTransicao() {
            return selectedTransicao;
        }

        public void setSelectedTransicao(Transicao selectedTransicao) {
            this.selectedTransicao = selectedTransicao;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Inicial;
    private javax.swing.JTextPane fita0;
    private javax.swing.JTextPane fita1;
    private javax.swing.JTextPane fita2;
    private javax.swing.JTextPane fita3;
    private javax.swing.JTextPane fita4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane painel;
    private javax.swing.JTextField qtdeIteracoes;
    private javax.swing.JLabel respostaText;
    // End of variables declaration//GEN-END:variables
}
