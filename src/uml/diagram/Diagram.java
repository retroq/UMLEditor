/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.diagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public class Diagram implements Serializable {

    public List<ClassElement> classes;
    public List<AbstractRelation> relations;

    public Diagram() {
        classes = new ArrayList<ClassElement>();
        relations = new ArrayList<AbstractRelation>();
    }

    /**
     * Получает класс, который находится в заданных координатах
     * устанавливает остальные как невыделенные
     * @param x
     * @param y
     * @return 
     */
    public ClassElement getSelected(int x, int y) {
        throw new UnsupportedOperationException();
    }
    static volatile boolean deleting = false;
/**
     * @deprecated 
     * @param c 
     */
    public void deleteClassElement(ClassElement c) {
        if (!deleting) {
            deleting = true;
            for (ClassElement ce : classes) {
                if (c == ce) {
                    classes.remove(c);
                }
            }
            for (AbstractRelation r : relations) {
                if (r.classFrom == c || r.classTo == c) {
                    relations.remove(r);
                }
            }
            deleting = false;
        }
    }
}
