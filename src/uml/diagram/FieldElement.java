/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.diagram;

import java.io.Serializable;


/**
 * Поле класса
 * @author anton
 */
public class FieldElement implements Serializable{
    private AccessModificator accessModificator;
    private String type;

    public AccessModificator getAccessModificator() {
        return accessModificator;
    }

    public void setAccessModificator(AccessModificator accessModificator) {
        this.accessModificator = accessModificator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private String name;

    public FieldElement(AccessModificator accessModificator, String type, String name) {
        super();
        this.accessModificator = accessModificator;
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return  accessModificator.getSymbol() + " " + type + " " + name;
    }    
}

