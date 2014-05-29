/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.diagram;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author anton
 */
public abstract class AbstractRelation implements Serializable{
    protected int borderPointFrom, borderPointTo;
    public AbstractRelation(){
        
    }

    public int getBorderPointFrom() {
        return borderPointFrom;
    }

    public void setBorderPointFrom(int borderPointFrom) {
        this.borderPointFrom = borderPointFrom;
    }

    public int getBorderPointTo() {
        return borderPointTo;
    }

    public void setBorderPointTo(int borderPointTo) {
        this.borderPointTo = borderPointTo;
    }

    public ClassElement getClassFrom() {
        return classFrom;
    }

    public void setClassFrom(ClassElement classFrom) {
        this.classFrom = classFrom;
    }

    public ClassElement getClassTo() {
        return classTo;
    }

    public void setClassTo(ClassElement classTo) {
        this.classTo = classTo;
    }
    protected ClassElement classFrom = null, classTo = null;
    
    protected Point point;//Точка для прорисовки связи с неизвестным вторым элементом

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    
    public AbstractRelation(int borderPointFrom, int borderPointTo, ClassElement classFrom, ClassElement classTo) {
        this.borderPointFrom = borderPointFrom;
        this.borderPointTo = borderPointTo;
        this.classFrom = classFrom;
        this.classTo = classTo;
    }
    
    public abstract void draw(Graphics g);
}
