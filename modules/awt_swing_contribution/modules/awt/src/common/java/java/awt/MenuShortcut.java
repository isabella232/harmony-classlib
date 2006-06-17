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
 * @author Pavel Dolgov
 * @version $Revision$
 */
package java.awt;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import org.apache.harmony.misc.HashCode;


public class MenuShortcut implements Serializable {

    private static final long serialVersionUID = 143448358473180225L;

    private final int keyCode;
    private final boolean shiftModifier;
    public MenuShortcut(int key) {
        this(key, false);
    }

    public MenuShortcut(int key, boolean useShiftModifier) {
        keyCode = key;
        shiftModifier = useShiftModifier;
    }

    public int hashCode() {
        int hashCode = HashCode.EMPTY_HASH_CODE;
        hashCode = HashCode.combine(hashCode, keyCode);
        hashCode = HashCode.combine(hashCode, shiftModifier);
        return hashCode;
    }

    public boolean equals(MenuShortcut s) {
        return (s != null) && (s.keyCode == keyCode) &&
                (s.shiftModifier == shiftModifier);
    }

    public boolean equals(Object obj) {
        if (obj instanceof MenuShortcut) {
            MenuShortcut s = (MenuShortcut)obj;
            return (s.keyCode == keyCode) &&
                    (s.shiftModifier == shiftModifier);
        }
        return false;
    }

    public String toString() {
        int modifiers = KeyEvent.CTRL_DOWN_MASK |
                (shiftModifier ? KeyEvent.SHIFT_DOWN_MASK : 0);

        return KeyEvent.getKeyModifiersText(modifiers) +
                "+" + KeyEvent.getKeyText(keyCode);
    }

    public int getKey() {
        return keyCode;
    }

    protected String paramString() {
        /* The format of paramString is based on 1.5 release behavior 
         * which can be revealed using the following code:
         *
         * MenuShortcut obj = new MenuShortcut(KeyEvent.VK_A, true);
         * System.out.println(obj.toString());
         */

        String str = "key=" + keyCode;
        if (shiftModifier) {
            str += ",usesShiftModifier";
        }
        return str;
    }

    public boolean usesShiftModifier() {
        return shiftModifier;
    }

    static MenuShortcut lookup(KeyEvent ke) {
        if (ke.isControlDown()) {
            return new MenuShortcut(ke.getKeyCode(), ke.isShiftDown());
        }
        return null;
    }

    static boolean isShortcut(KeyEvent ke) {
        return ke.isControlDown();
    }

}

