/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.diagram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 

/**
 *  Элемент диаграммы - класс.
 *  Содержит закрытые и публичные методы и поля класса.
 * @author anton
 */
public class ClassElement implements Serializable{

    private String className = "";

    public String getClassName() {
        return className;
    }
    private List<MethodElement> methods;

    public List<FieldElement> getFields() {
        return fields;
    }

    public void setFields(List<FieldElement> fields) {
        this.fields = fields;
    }

    public List<MethodElement> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodElement> methods) {
        this.methods = methods;
    }
    private List<FieldElement> fields;

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    //Параметры отрисовки
    private Color backColor = Color.YELLOW,
            borderColor = Color.BLACK,
            fontColor = Color.BLACK,
            borderPointColor = Color.RED;
    private int width = 90, height = lineHeight * 2;
    //Координаты элемента
    int xCoord = 0, yCoord = 0;

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    private int charWidth = 7; //Ширина символа для перерасчёта ширины элемента при вставке/удалении поля или метода
    private static final int lineHeight = 15,
            indent = 15,
            borderPointsNum = 4,
            borderPointHeight = 4;
    //Выделен ли элемент?
    boolean isSelected = false;

    public ClassElement(String name) {
        this.className = name;
        methods = new ArrayList<MethodElement>();
        fields = new ArrayList<FieldElement>();
    }

    //Корректирует ширину элемента при добавлении поля
    public void correctWidthAfterInsert(String newString) {
        int len = indent + newString.length() * charWidth;
        if (width < len) {
            width = len;
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Корректирует ширину элемента при удалении поля
     */
    private void correctWidthAfterDelete() {
        String newString = "";
        for (FieldElement f : fields) {
            String s = f.toString();
            if (s.length() > newString.length()) {
                newString = s;
            }
        }
        for (MethodElement f : methods) {
            String s = f.toString();
            if (s.length() > newString.length()) {
                newString = s;
            }
        }
        int len = indent + newString.length() * charWidth;
        if (width > len) {
            width = len;
        }
    }

    public void addMethod(AccessModificator accessModificator, String methodName, String parametrs, String returnType) {
        MethodElement m = new MethodElement(accessModificator, methodName, parametrs, returnType);
        correctWidthAfterInsert(m.toString());
        height += lineHeight;
        methods.add(m);
    }

    public void addMethod(MethodElement m) {
        correctWidthAfterInsert(m.toString());
        height += lineHeight;
        methods.add(m);
    }

    public void addField(AccessModificator accessModificator, String type, String name) {
        FieldElement f = new FieldElement(accessModificator, type, name);
        correctWidthAfterInsert(f.toString());
        height += lineHeight;
        fields.add(f);
    }
    
     public void addField(FieldElement f) {
         
        correctWidthAfterInsert(f.toString());
        height += lineHeight;
        fields.add(f);
    }

    public void deleteMethod(int i) {
        if (i < methods.size()) {
            methods.remove(i);
        }
        correctWidthAfterDelete();
    }

    public void deleteMethod(MethodElement m) {
        methods.remove(m);
        height -= lineHeight;
        correctWidthAfterDelete();
    }

    public void deleteField(int i) {
        if (i < fields.size()) {
            fields.remove(i);
        }
        correctWidthAfterDelete();
    }

    public void deleteField(FieldElement f) {
        fields.remove(f);
        height -= lineHeight;
        correctWidthAfterDelete();
    }

    void draw(Graphics g, int x, int y) {
        xCoord = x;
        yCoord = y;
        g.drawRect(x, y, width, height);
        g.drawString(className, x + indent, y + lineHeight);
        int i = 2;
        for (MethodElement m : methods) {
            g.drawString(m.toString(), x + indent, y + lineHeight * i);
            g.drawLine(x, y + lineHeight * i + 1, x + width, y + lineHeight * i + 1);
            i++;
        }
        for (FieldElement f : fields) {
            g.drawString(f.toString(), x + indent, y + lineHeight * i);
            g.drawLine(x, y + lineHeight * i + 1, x + width, y + lineHeight * i + 1);
            i++;
        }
        if (isSelected) {
            drawBorderPoints(g);
        }
    }

    public void draw(Graphics g) {
        int x, y;
        x = xCoord;
        y = yCoord;
        g.drawRect(x, y, width, height);
        g.drawString(className, x + indent, y + lineHeight);
        int i = 2;
        for (MethodElement m : methods) {
            g.drawString(m.toString(), x + indent, y + lineHeight * i);
            g.drawLine(x, y + lineHeight * i + 1, x + width, y + lineHeight * i + 1);
            i++;
        }
        for (FieldElement f : fields) {
            g.drawString(f.toString(), x + indent, y + lineHeight * i);
            g.drawLine(x, y + lineHeight * i + 1, x + width, y + lineHeight * i + 1);
            i++;
        }
        if (isSelected) {
            drawBorderPoints(g);
        }
    }

    private void drawBorderPoints(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(borderPointColor);
        for (int i = 1; i <= borderPointsNum; i++) {
            Point p = getBorderPoint(i);
            g.fillRect(p.x - borderPointHeight / 2, p.y - borderPointHeight / 2, borderPointHeight, borderPointHeight);
        }
        g.setColor(oldColor);
    }

    /**
     * Точки на границе (для сцепки связей и обозначения выделения)
     * @param n
     * @return 
     */
    public Point getBorderPoint(int n) {
        Point p = null;
        switch (n) {
            case 1:
                p = new Point(xCoord + width / 2, yCoord);
                break;
            case 2:
                p = new Point(xCoord + width, yCoord + height / 2);
                break;
            case 3:
                p = new Point(xCoord + width / 2, yCoord + height);
                break;
            case 4:
                p = new Point(xCoord, yCoord + height / 2);
                break;
            default:
                p = new Point(-10, -10);
        }
        return p;
    }

    /**
     * Проверяет, находятся ли координаты внутри текущего элемента
     * @param x
     * @param y
     * @return true если внутри
     */
    public boolean isInside(int x, int y) {
        return (x >= xCoord && x <= xCoord + width && y >= yCoord && y <= yCoord + height);
    }

    /**
     * Проверяет, находятся ли координаты рядом с точками границ
     * @param x
     * @param y
     * @return 0 если не находится ни в одной, иначе номер точки
     */
    public int isInBorderPoint(int x, int y) {
        for (int i = 1; i <= borderPointsNum; i++) {
            Point p = getBorderPoint(i);
            if (Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y)) < 20.) {
                return i;
            }
        }
        return 0;
    }

    public void move(int dx, int dy) {
        xCoord += dx;
        yCoord += dy;
    }
}
