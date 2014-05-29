/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.editor;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import uml.diagram.ClassElement;
import uml.diagram.Diagram;
 
/**
 *
 * @author anton
 */
public class StandartMouseHandler implements MouseHandlerInterface{
    Diagram diagram;
    boolean smthSelected = false, isMoving = false;
    ClassElement selectetClass;
    
    int oldX, oldY;
    JFrame parent;
    
    public StandartMouseHandler(Diagram d, JFrame f){
        diagram = d;
        parent = f;
    }
    public void click(MouseEvent e){
        if (e.getClickCount() == 1){
            smthSelected = false;
            for (ClassElement c : diagram.classes){
                if (!smthSelected && c.isInside(e.getX(), Cheat.f(e.getY()))){
                    c.setIsSelected(true);
                    smthSelected = true;
                    selectetClass = c;
                }
                else c.setIsSelected(false);
            }
        }
        else if (e.getClickCount() == 2){
            //Редактировать класс при двойном клике
            new EditClassElementDialog(parent, isMoving, selectetClass).setVisible(true);
        }
    }
    public void press(MouseEvent e){
        
            oldX = e.getX();
            oldY = Cheat.f(e.getY());
            isMoving = true;
    }
    
    public void move(MouseEvent e){
         
        if (smthSelected && isMoving &&  selectetClass.isInside(e.getX(), Cheat.f(e.getY()))){
            selectetClass.move(e.getX() -oldX, Cheat.f(e.getY()) - oldY );
            oldX = e.getX();
            oldY = Cheat.f(e.getY());
        }
    }
    public void release(MouseEvent e){
        isMoving = false;
    }
    
    public void deletePressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_DELETE && selectetClass != null){
            synchronized(diagram.classes){
            diagram.deleteClassElement(selectetClass);
            }
        }
    }
}


