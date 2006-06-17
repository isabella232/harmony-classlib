/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Dmitry A. Durnev
 * @version $Revision$
 */
package org.apache.harmony.awt;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * ButtonStateController.
 * Changes Component state and fires [action] events in response to
 * user input(key, mouse, focus events) and current state.
 * Repaints component when necessary.
 * Is typically used by Components such as Button, Checkbox, etc
 * as input event listener. Such components also query their state
 * properties, which are not stored in the Component-derived class,
 * for example isPressed(), with state controller.
 */
public abstract class ButtonStateController implements MouseListener, FocusListener, KeyListener {

    private boolean mousePressed = false;
    private boolean keyPressed = false;
    private boolean mouseInside = false;
    private boolean focused = false;
    private final Component component;
    
    /**
     * Store input event properties
     * to be able to retrieve them later
     * inside fireEvent() implementation
     * which typically uses them to 
     * fire action/item event
     */
    private long when;
    private int mod;

    public ButtonStateController(Component comp) {
        component = comp;
    }

    public boolean isPressed() {
        return ((mousePressed && mouseInside) || keyPressed);
    }

    public void mousePressed(MouseEvent me) {
        if (mousePressed || keyPressed ||
            (me.getButton() != MouseEvent.BUTTON1)) {

            return;
        }

        mousePressed = true;
        if (mouseInside) {
            component.repaint();
        }

        if (component.isFocusable()) {
            component.requestFocus();
        }
    }

    public void mouseReleased(MouseEvent me) {
        if (!mousePressed || (me.getButton() != MouseEvent.BUTTON1)) {
            return;
        }

        mousePressed = false;
        component.repaint();
        if (mouseInside) {
            when = me.getWhen();
            mod = me.getModifiers();
            fireEvent();
        }
    }

    public void keyPressed(KeyEvent ke) {
        assert focused == true : "Key event for unfocused component";

        if (mousePressed || keyPressed || (ke.getKeyCode() != KeyEvent.VK_SPACE)) {
            return;
        }

        keyPressed = true;
        component.repaint();
    }

    public void keyReleased(KeyEvent ke) {
        assert focused == true : "Key event for unfocused component";

        if (!keyPressed || (ke.getKeyCode() != KeyEvent.VK_SPACE)) {
            return;
        }

        keyPressed = false;
        component.repaint();
        when = ke.getWhen();
        mod = ke.getModifiers();
        fireEvent();
    }

    public void mouseEntered(MouseEvent me) {
        assert mouseInside == false : "Double mouse enter event for component";
        mouseCrossed(true);
    }

    public void mouseExited(MouseEvent me) {
        assert mouseInside == true : "Double mouse exit event for component";
        mouseCrossed(false);
    }

    public void focusGained(FocusEvent fe) {
        assert focused == false : "Double focus gained event for component";

        focused = true;
        component.repaint();
    }

    public void focusLost(FocusEvent fe) {
        assert focused == true : "Double focus lost event for component";

        focused = false;
        keyPressed = false;
        mousePressed = false;
        component.repaint();
    }

    //Ignored
    public void keyTyped(KeyEvent ke) {
    }
    public void mouseClicked(MouseEvent me) {
    }

    private void mouseCrossed(boolean inside) {
        mouseInside = inside;
        if (mousePressed && focused) {
            component.repaint();
        }
    }

    public final long getWhen() {
        return when;
    }
    public final int getMod() {
        return mod;
    }

    protected abstract void fireEvent();

}
