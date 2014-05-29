/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.editor;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import uml.diagram.AbstractRelation;
import uml.diagram.ClassElement;
import uml.diagram.Diagram;

/**
 *
 * @author anton
 */
public class CreateRelationMouseHandler implements MouseHandlerInterface, CreateOperaion {

    AbstractRelation relation;
    Diagram diagram;
    boolean isFirstClick = true;
    boolean isOvered = false;
    public CreateRelationMouseHandler(Diagram d, AbstractRelation r) {
        this.diagram = d;
        this.relation = r;
        for (ClassElement c : diagram.classes){
            c.setIsSelected(true);
        }
    }
    
    public boolean isOvered(){
        return isOvered;
    }

    public void click(MouseEvent e){
        if (isFirstClick) {
            for (ClassElement c : diagram.classes) {
                int pn = c.isInBorderPoint(e.getX(), Cheat.f(e.getY()));
                if (pn > 0) {
                    relation.setBorderPointFrom(pn);
                    relation.setClassFrom(c);
                    relation.setPoint(new Point(e.getX(), Cheat.f(e.getY())));
                    diagram.relations.add(relation);
                    isFirstClick = false;
                    return;
                }
            }
        } else {
            for (ClassElement c : diagram.classes) {
                int pn = c.isInBorderPoint(e.getX(), Cheat.f(e.getY()));
                if (pn > 0 && !(relation.getClassFrom() == c && relation.getBorderPointFrom() == pn)) {
                    relation.setBorderPointTo(pn);
                    relation.setClassTo(c);
                    isFirstClick = false;
                    isOvered = true;
                    return;
                }
                
            }
            isOvered = true;
                free();
        }
    }

    public void press(MouseEvent e) {
    }

    public void release(MouseEvent e) {
    }

    public void move(MouseEvent e) {
        if (!isFirstClick){
            relation.setPoint(new Point(e.getX(), Cheat.f(e.getY())));
        }
    }
    
    public void deletePressed(KeyEvent e){
        
    }
    
    //Удаление отношения из диаграммы если оно не было создано
    public void free(){
        diagram.relations.remove(relation);
    }
}
 