/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditorPanel.java
 *
 * Created on 22.12.2012, 1:39:10
 */
package uml.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import javax.swing.JFrame;
import uml.diagram.AbstractRelation;
import uml.diagram.AccessModificator;
import uml.diagram.ClassElement;
import uml.diagram.Diagram;
import uml.diagram.Generalization;

/**
 *
 * @author anton
 */
public class EditorPanel extends javax.swing.JPanel {

    Diagram diagram;
    MouseHandlerInterface mhandler;
    CreateOperaion createOperaion = null;
    JFrame parent;
    Thread backUpThread;
    public MouseHandlerInterface getMhandler() {
        return mhandler;
    }

    public void setMhandler(MouseHandlerInterface mhandler) {
        this.mhandler = mhandler;
    }
    
    public EditorPanel(){
        this(null);
    }
    /** Creates new form EditorPanel */
    public EditorPanel(JFrame p) {
        initComponents();
        this.parent = p;
        this.setBackground(Color.WHITE);
        diagram = new Diagram();
//        ClassElement c, c2;
//        AbstractRelation r;
//        c = new ClassElement("MyClass");
//        c2 = new ClassElement("Class2");
//        c.setxCoord(10);
//        c.setyCoord(10);
//        c2.setxCoord(150);
//        r = new Generalization(2, 4, c, c2);
//        c.addField(AccessModificator.PRIVATE, "int", "id");
//        
//        c.addMethod(AccessModificator.PUBLIC, "getId", "", "int");
//        c2.addMethod(AccessModificator.PUBLIC, "getIdasd", "", "int");
//        c2.addMethod(AccessModificator.PUBLIC, "getIdasdasdasd", "", "int");
//        diagram.classes.add(c);
//        diagram.classes.add(c2);
//        diagram.relations.add(r);
//        
//      //  saveToFile("diagram1");
//        diagram = null;
//        openFromFile("diagram1");
        
        mhandler = new StandartMouseHandler(diagram, parent);
        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                mhandler.deletePressed(e);
            }
        });
        
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                mhandler.click(e);

                if (createOperaion != null && createOperaion.isOvered()) {
                    createOperaion = null;
                    mhandler = new StandartMouseHandler(diagram, parent);
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mhandler.press(e);
                //  repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mhandler.release(e);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                mhandler.move(e);
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

                mhandler.move(e);
                repaint();
                // System.out.println("Inside");
                //repaint();
            }
        });
    }

    public CreateOperaion getCreateOperaion() {
        return createOperaion;
    }

    public void setCreateOperaion(CreateOperaion createOperaion) {
        this.createOperaion = createOperaion;
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        createBackupThread();
        this.diagram = diagram;
    }

    public JFrame getParent() {
        return parent;
    }

    public void setParent(JFrame parent) {
        this.parent = parent;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ClassElement c : diagram.classes) {
            c.draw(g);
        }
        for (AbstractRelation r : diagram.relations) {
            r.draw(g);
        }
    }

    public void saveToFile(String name) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(name));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(diagram);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void openFromFile(String name){
        try {
            FileInputStream fis = new FileInputStream(new File(name));
            ObjectInputStream ois = new ObjectInputStream(fis);
            setDiagram((Diagram)ois.readObject());
            mhandler = new StandartMouseHandler(diagram, parent);
            ois.close();
        } catch (Exception e) {
        }
    }
    
    public void createBackUp(String name){
        saveToFile(name);
    }
    
    public void createBackupThread(){
        if (backUpThread != null)
            backUpThread.interrupt();
        backUpThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try{
                    
                    String name = Integer.toString((int)(System.currentTimeMillis()% 10000))+".uml";
                    boolean b = true;
                    while (b){
                        Thread.sleep(1000);
                        createBackUp( name);
                    }
                        
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        backUpThread.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
