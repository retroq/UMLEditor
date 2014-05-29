/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.editor;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import uml.diagram.ClassElement;
import uml.diagram.Diagram;

/**
 *
 * @author anton
 */
public class CreateClassMouseHandler implements CreateOperaion, MouseHandlerInterface{
    private boolean isOvered = false;
    private Diagram diagram;
    public CreateClassMouseHandler(Diagram d){
        diagram = d;
    }
    
    public boolean isOvered(){
        return isOvered;
    }
    
    public void click(MouseEvent e){
        ClassElement c = new ClassElement("MyClass");
        c.setxCoord(e.getX());
        c.setyCoord(Cheat.f(e.getY()));
        diagram.classes.add(c);
        isOvered = true;
    }
    
    public void press(MouseEvent e){
        
    }
    public void release(MouseEvent e){
        
    }
    public void move(MouseEvent e){
        
    }
    public void deletePressed(KeyEvent e){
        
    }
}
