/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.harmony.pack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Attribute extends ClassFileEntry {
    protected final CPUTF8 attributeName;

    private int attributeNameIndex;

    public Attribute(String attributeName) {
        this.attributeName = new CPUTF8(attributeName, ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ);
    }

    protected void doWrite(DataOutputStream dos) throws IOException {
        dos.writeShort(attributeNameIndex);
        dos.writeInt(getLength());
        writeBody(dos);
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        final Attribute other = (Attribute) obj;
        if (attributeName == null) {
            if (other.attributeName != null)
                return false;
        } else if (!attributeName.equals(other.attributeName))
            return false;
        return true;
    }

    protected CPUTF8 getAttributeName() {
        return attributeName;
    }

    protected abstract int getLength();

    /**
     * Answer the length of the receiver including its header (the u2 for the
     * attribute name and the u4 for the attribute length). This is relevant
     * when attributes are nested within other attributes - the outer attribute
     * needs to take the inner attribute headers into account when calculating
     * its length.
     * 
     * @return int adjusted length
     */
    protected int getLengthIncludingHeader() {
        return getLength() + 2 + 4;
    }

    protected ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[] { getAttributeName() };
    }

    /**
     * Answer true if the receiver needs to have BCI renumbering applied to it;
     * otherwise answer false.
     * 
     * @return boolean BCI renumbering required
     */
    public boolean hasBCIRenumbering() {
        return false;
    }

    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((attributeName == null) ? 0 : attributeName.hashCode());
        return result;
    }

    protected void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        attributeNameIndex = pool.indexOf(attributeName);
    }

    protected abstract void writeBody(DataOutputStream dos) throws IOException;

}
