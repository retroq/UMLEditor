/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.diagram;

import java.io.Serializable;

/**
 * Модификаторы доступа
 * @author anton
 */
public enum AccessModificator implements Serializable{
    PRIVATE('-'),
    PUBLIC('+'),
    PROTECTED('#');
    private char symbol;

    private AccessModificator(char symbol) {
        this.symbol = symbol;
    }
    
    public char getSymbol() {
        return symbol;
    }
    
     
}
