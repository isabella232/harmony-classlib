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
 * @author Igor V. Stolyarov
 * @version $Revision$
 */
package java.awt.image;

public final class DataBufferFloat extends DataBuffer {

    float data[][];

    public DataBufferFloat(float dataArrays[][], int size, int offsets[]) {
        super(TYPE_FLOAT, size, dataArrays.length, offsets);
        data = (float[][]) dataArrays.clone();
    }

    public DataBufferFloat(float dataArrays[][], int size) {
        super(TYPE_FLOAT, size, dataArrays.length);
        data = (float[][]) dataArrays.clone();
    }

    public DataBufferFloat(float dataArray[], int size, int offset) {
        super(TYPE_FLOAT, size, 1, offset);
        data = new float[1][];
        data[0] = dataArray;
    }

    public DataBufferFloat(float dataArray[], int size) {
        super(TYPE_FLOAT, size);
        data = new float[1][];
        data[0] = dataArray;
    }

    public DataBufferFloat(int size, int numBanks) {
        super(TYPE_FLOAT, size, numBanks);
        data = new float[numBanks][];
        int i = 0;
        while (i < numBanks) {
            data[i++] = new float[size];
        }
    }

    public DataBufferFloat(int size) {
        super(TYPE_FLOAT, size);
        data = new float[1][];
        data[0] = new float[size];
    }

    public void setElem(int bank, int i, int val) {
        data[bank][offsets[bank] + i] = (float) val;
    }

    public void setElemFloat(int bank, int i, float val) {
        data[bank][offsets[bank] + i] = val;
    }

    public void setElemDouble(int bank, int i, double val) {
        data[bank][offsets[bank] + i] = (float) val;
    }

    public void setElem(int i, int val) {
        data[0][offset + i] = (float) val;
    }

    public int getElem(int bank, int i) {
        return (int) (data[bank][offsets[bank] + i]);
    }

    public float getElemFloat(int bank, int i) {
        return data[bank][offsets[bank] + i];
    }

    public double getElemDouble(int bank, int i) {
        return (double) data[bank][offsets[bank] + i];
    }

    public void setElemFloat(int i, float val) {
        data[0][offset + i] = val;
    }

    public void setElemDouble(int i, double val) {
        data[0][offset + i] = (float) val;
    }

    public float[] getData(int bank) {
        return data[bank];
    }

    public int getElem(int i) {
        return (int) (data[0][offset + i]);
    }

    public float getElemFloat(int i) {
        return data[0][offset + i];
    }

    public double getElemDouble(int i) {
        return (double) data[0][offset + i];
    }

    public float[][] getBankData() {
        return (float[][]) data.clone();
    }

    public float[] getData() {
        return data[0];
    }
}

