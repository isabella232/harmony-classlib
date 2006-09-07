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
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package javax.crypto;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.crypto.NullCipher;
import java.security.GeneralSecurityException;

/**
 * @com.intel.drl.spec_ref
 */
public class CipherInputStream extends FilterInputStream {

    private final Cipher cipher;
    private int I_BUFFER_SIZE = 20;
    private byte[] i_buffer = new byte[I_BUFFER_SIZE];
    private int index; // index of the butes to return from o_buffer
    private byte[] o_buffer;
    private boolean finished;

    /**
     * @com.intel.drl.spec_ref
     */
    public CipherInputStream(InputStream is, Cipher c) {
        super(is);
        this.cipher = c;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected CipherInputStream(InputStream is) {
        this(is, new NullCipher());
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int read() throws IOException {
        if (finished) {
            return ((o_buffer == null) || (index == o_buffer.length)) 
                            ? -1 
                            : o_buffer[index++] & 0xFF;
        }
        if ((o_buffer != null) && (index < o_buffer.length)) {
            return o_buffer[index++] & 0xFF;
        }
        index = 0;
        o_buffer = null;
        int num_read;
        while (o_buffer == null) {
            if ((num_read = in.read(i_buffer)) == -1) {
                try {
                    o_buffer = cipher.doFinal();
                } catch (Exception e) {
                    throw new IOException(e.getMessage());
                }
                finished = true;
                break;
            }
            o_buffer = cipher.update(i_buffer, 0, num_read);
        }
        return read();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int read(byte[] b, int off, int len) throws IOException {
        if (in == null) {
            throw new NullPointerException("Underlying input stream is null");
        }

        int read_b;
        int i;
        for (i=0; i<len; i++) {
            if ((read_b = read()) == -1) {
                return (i == 0) ? -1 : i; 
            }
            if (b != null) {
                b[off+i] = (byte) read_b;
            }
        }
        return i;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public long skip(long n) throws IOException {
        long i = 0;
        int available = available();
        if (available < n) {
            n = available;
        }
        while ((i < n) && (read() != -1)) {
            i++;
        }
        return i;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int available() throws IOException {
        return 0;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void close() throws IOException {
        in.close();
        try {
            cipher.doFinal();
        } catch (GeneralSecurityException e) {
            throw (IOException) new IOException(e.getMessage()).initCause(e);
        }

    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean markSupported() {
        return false;
    }
}

