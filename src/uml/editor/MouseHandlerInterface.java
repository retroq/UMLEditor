/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uml.editor;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author anton
 */
public interface MouseHandlerInterface {
    public void click(MouseEvent e);
    public void press(MouseEvent e);
    public void release(MouseEvent e);
    public void move(MouseEvent e);
    public void deletePressed(KeyEvent e);
}
