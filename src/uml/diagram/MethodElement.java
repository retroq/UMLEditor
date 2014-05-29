/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.diagram;

import java.io.Serializable;

/**
 * Хранит информацию о методе (тип доступа, параметры, возвращаемые результат)
 * @author anton
 */
public class MethodElement implements Serializable{
    private AccessModificator accessModificator;
    private String methodName;

    public void setAccessModificator(AccessModificator accessModificator) {
        this.accessModificator = accessModificator;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParametrs(String parametrs) {
        this.parametrs = parametrs;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
    private String parametrs;

    public AccessModificator getAccessModificator() {
        return accessModificator;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getParametrs() {
        return parametrs;
    }

    public String getReturnType() {
        return returnType;
    }
    private String returnType;

    public MethodElement(AccessModificator accessModificator, String methodName, String parametrs, String returnType) {
        this.accessModificator = accessModificator;
        this.methodName = methodName;
        this.parametrs = parametrs;
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return   accessModificator.getSymbol() + " "
                + methodName + "(" + parametrs + ") : " + returnType;
    }
}
