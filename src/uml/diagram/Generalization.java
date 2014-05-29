/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.diagram;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author anton
 */
public class Generalization extends AbstractRelation {

    public Generalization() {
    }

    public Generalization(int borderPointFrom, int borderPointTo, ClassElement classFrom, ClassElement classTo) {
        super(borderPointFrom, borderPointTo, classFrom, classTo);
    }

    public void draw(Graphics g) {
        Point p1, p2;
        p1 = classFrom.getBorderPoint(borderPointFrom);
        if (classTo == null) {
            p2 = point;
        } else {
            p2 = classTo.getBorderPoint(borderPointTo);
        }
        
        double al = Math.PI/6;
        double [][] rotateMatr = {
            {Math.cos(al), -Math.sin(al)},
            {Math.sin(al), Math.cos(al)}
        };
        
        int len = (int)Math.sqrt((p2.x - p1.x)*(p2.x - p1.x)+(p2.y - p1.y)*(p2.y - p1.y));
        if (len == 0) len = 1;
        int vectorLen = 15;
        Point p = new Point(vectorLen*(p1.x - p2.x)/len, vectorLen*(p1.y - p2.y)/len);
        g.drawLine(p1.x, p1.y, p2.x+p.x, p2.y+p.y);
        double x1 = rotateMatr[0][0]*p.x+rotateMatr[0][1]*p.y;
        double y1 = rotateMatr[1][0]*p.x+rotateMatr[1][1]*p.y;
        
        g.drawLine((int)(x1+p2.x), (int)(y1+p2.y), p2.x, p2.y);
        
        double   x2 = rotateMatr[0][0]*p.x-rotateMatr[0][1]*p.y;
        double  y2 = -rotateMatr[1][0]*p.x+rotateMatr[1][1]*p.y;
          g.drawLine((int)(x2+p2.x), (int)(y2+p2.y), p2.x, p2.y);
        g.drawLine((int)(x2+p2.x), (int)(y2+p2.y), (int)(x1+p2.x), (int)(y1+p2.y));
    }
}
