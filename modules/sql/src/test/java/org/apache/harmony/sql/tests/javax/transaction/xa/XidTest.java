/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.sql.tests.javax.transaction.xa;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import javax.transaction.xa.Xid;

import junit.framework.TestCase;

public class XidTest extends TestCase {

	/*
	 * Public statics test
	 */
	public void testPublicStatics() {

		HashMap thePublicStatics = new HashMap();
		thePublicStatics.put("MAXBQUALSIZE", new Integer(64));
		thePublicStatics.put("MAXGTRIDSIZE", new Integer(64));

		Class xidClass;
		try {
			xidClass = Class.forName("javax.transaction.xa.Xid");
		} catch (ClassNotFoundException e) {
			fail("javax.transaction.xa.Xid class not found!");
			return;
		} // end try

		Field[] theFields = xidClass.getDeclaredFields();
		int requiredModifier = Modifier.PUBLIC + Modifier.STATIC
				+ Modifier.FINAL;

		int countPublicStatics = 0;
		for (int i = 0; i < theFields.length; i++) {
			String fieldName = theFields[i].getName();
			int theMods = theFields[i].getModifiers();
			if (Modifier.isPublic(theMods) && Modifier.isStatic(theMods)) {
				try {
					Object fieldValue = theFields[i].get(null);
					Object expectedValue = thePublicStatics.get(fieldName);
					if (expectedValue == null) {
						fail("Field " + fieldName + " missing!");
					} // end
					assertEquals("Field " + fieldName + " value mismatch: ",
							expectedValue, fieldValue);
					assertEquals("Field " + fieldName + " modifier mismatch: ",
							requiredModifier, theMods);
					countPublicStatics++;
				} catch (IllegalAccessException e) {
					fail("Illegal access to Field " + fieldName);
				} // end try
			} // end if
		} // end for

	} // end method testPublicStatics

} // end class XidTest

