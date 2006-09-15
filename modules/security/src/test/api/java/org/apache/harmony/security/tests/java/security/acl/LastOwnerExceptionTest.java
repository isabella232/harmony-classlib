/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
* @author Aleksei Y. Semenov
* @version $Revision$
*/

package org.apache.harmony.security.tests.java.security.acl;

import java.security.acl.LastOwnerException;

import junit.framework.TestCase;

/**
 * Unit test for LastOwnerException.
 * 
 */

public class LastOwnerExceptionTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(LastOwnerExceptionTest.class);
    }

    public void testLastOwnerException() {
        assertNotNull(new LastOwnerException());
        assertNull(new LastOwnerException().getMessage());
        assertNull(new LastOwnerException().getCause());
    }

}
