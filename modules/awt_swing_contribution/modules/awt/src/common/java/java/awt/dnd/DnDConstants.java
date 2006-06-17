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
 * @author Michael Danilov
 * @version $Revision$
 */
package java.awt.dnd;

public final class DnDConstants {

    static boolean isValidAction(int action) {
        switch (action) {
        case ACTION_NONE:
        case ACTION_COPY:
        case ACTION_MOVE:
        case ACTION_COPY_OR_MOVE:
        case ACTION_LINK:
            return true;
        default:
            return false;
        }
    }

    private DnDConstants() {
    }

    public static final int ACTION_NONE = 0;

    public static final int ACTION_COPY = 1;

    public static final int ACTION_MOVE = 2;

    public static final int ACTION_COPY_OR_MOVE = 3;

    public static final int ACTION_LINK = 1073741824;

    public static final int ACTION_REFERENCE = 1073741824;

}
