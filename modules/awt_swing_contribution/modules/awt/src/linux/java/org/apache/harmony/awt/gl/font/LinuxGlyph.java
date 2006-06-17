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
 * @author Ilya S. Okomin
 * @version $Revision$
 */
package org.apache.harmony.awt.gl.font;

import java.awt.font.GlyphMetrics;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import java.awt.Rectangle;
import java.awt.Shape;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.lang.Math;

import org.apache.harmony.awt.ContextStorage;
import org.apache.harmony.awt.nativebridge.linux.*;
import org.apache.harmony.awt.nativebridge.Int16Pointer;
import org.apache.harmony.awt.nativebridge.Int8Pointer;
import org.apache.harmony.awt.gl.font.Glyph;
import org.apache.harmony.awt.gl.font.LinuxNativeFont;
import org.apache.harmony.awt.wtk.linux.LinuxWindowFactory;
import org.apache.harmony.awt.nativebridge.linux.LinuxNativeFontWrapper;

/**
 * Linux implementation of the Glyph class
 */
public class LinuxGlyph extends Glyph{

    // Xft instanse
    private final Xft xft = Xft.getInstance();
    
    // LinuxNativeFontWrapper instanse
    private final LinuxNativeFontWrapper lnfw = LinuxNativeFontWrapper.getInstance();

    // GlyphBitmap structure that stores bitmap of the glyph. 
    LinuxNativeFontWrapper.GlyphBitmap gBmp = null;


    /** 
     * Constructor
     */
    public LinuxGlyph(long pFnt, int fntSize, char c, int glyphIndex) {
        int[] pxlMetrics;

        this.pFont = pFnt;
        this.fontSize = fntSize;
        long display = ((LinuxWindowFactory)ContextStorage.getWindowFactory()).getDisplay();

        switch (c){
        case '\t':
        case '\r':
        case '\n':
            pxlMetrics = new int[6];
            break;
        default:
            pxlMetrics = LinuxNativeFont.getGlyphPxlInfoNative(display, this.pFont, c);
            break;

    }
//      LinuxNativeFont.getGlyphInfoNative(this.pFont, c, fntSize);

/*      FIXME: The same results as pxl metrics
        metrics = LinuxNativeFont.getGlyphInfoNative(this.pFont, c, fntSize);

        Rectangle2D.Float rect  = new Rectangle2D.Float(metrics[0],
                                                        -metrics[1],
                                                        metrics[4],
                                                        metrics[5]);
        this.glMetrics = new GlyphMetrics((float)Math.ceil(metrics[2]), rect, (byte)1);
*/
        this.glCode = glyphIndex;
        this.glChar = c;

        Rectangle rct  = new Rectangle(pxlMetrics[0],
                                                        -pxlMetrics[1],
                                                        pxlMetrics[4],
                                                        pxlMetrics[5]);
        this.glPointMetrics = new GlyphMetrics(pxlMetrics[2], rct, (byte)1);
        this.glMetrics = new GlyphMetrics((float)Math.ceil(pxlMetrics[2]), rct, (byte)0);

    }

    /**
     * Default Glyph constructor
     */
    public LinuxGlyph(char c, int glyphIndex) {
        float metrics[] = new float[6];
        int[] pxlMetrics = new int[6];

        this.pFont = 0;
        this.fontSize = 0;

        Rectangle2D.Float rect  = new Rectangle2D.Float(metrics[0],
                                                        -metrics[1],
                                                        metrics[4],
                                                        metrics[5]);
        this.glMetrics = new GlyphMetrics((float)Math.ceil(metrics[2]), rect, (byte)0);

        this.glCode = glyphIndex;
        this.glChar = c;

        Rectangle rct  = new Rectangle(pxlMetrics[0],
                                                        -pxlMetrics[1],
                                                        pxlMetrics[4],
                                                        pxlMetrics[5]);
        this.glPointMetrics = new GlyphMetrics(pxlMetrics[2], rct, (byte)1);
    }

    /**
     * Returns cached bitmap of the glyph's bitmap. Returns null if this
     * Glyph object has height or width equal to zero.  
     */
    public byte[] getBitmap(){
        if ((this.getWidth()==0) || (this.getHeight()==0)){
            return null;
        }

        if (this.bitmap == null){
            getFTBitmap();
        }

        return this.bitmap;
    }


    /**
     * Returns array of bytes, representing image of this glyph from native code.
     */
    public LinuxNativeFontWrapper.GlyphBitmap getFTBitmap(){
        if (this.gBmp == null){
            long ptr = LinuxNativeFont.NativeInitGlyphBitmap(this.pFont, this.glChar);
            if (ptr != 0)
                this.gBmp = lnfw.createGlyphBitmap(ptr);

                Xft.FT_Bitmap ft_bitmap = gBmp.get_bitmap();
                Int8Pointer buffer = ft_bitmap.get_buffer();
                this.bmp_left = gBmp.get_left();
                this.bmp_top = gBmp.get_top();
                this.bmp_pitch = ft_bitmap.get_pitch();
                this.bmp_rows = ft_bitmap.get_rows();
                this.bmp_width = ft_bitmap.get_width();
                int bufSize = bmp_pitch * bmp_rows; // size of buffer

                bitmap = new byte[bufSize];
                buffer.get(bitmap, 0, bufSize);
        }

        return this.gBmp;
    }

    public BufferedImage getImage(){
        if ((this.getWidth()==0) || (this.getHeight()==0)){
            return null;
        }

        byte[] pixels;
        int alignedWidth;
        int width;
        int height;
        if (this.image == null) {
            pixels = getBitmap();

            DataBufferByte dataBuffer = new DataBufferByte(pixels, pixels.length);
            /* Work around:
             *
             * Because of inability to create IndexedColorModel with data, represented as DataBuffer.TYPE_INT
             * Raster with additional width is created to cover all bits, which are extending meaningful bits
             * to the DWORD-aligning. When we want to take an image of the glyhp - we have to copy only rectangle
             * that encloses the Glyph from the whole raster.
             *
             * */
            height = (int)this.glPointMetrics.getBounds2D().getHeight();
            alignedWidth = (pixels.length / height) << 3;
            width = (int)this.glPointMetrics.getBounds2D().getWidth();

            WritableRaster wr = Raster.createPackedRaster(dataBuffer, alignedWidth, height, 1, null);

            byte[] blackWhite = new byte[]{0, (byte)0xff};
            IndexColorModel colorModel = new IndexColorModel(1, 2, blackWhite, blackWhite, blackWhite);

            this.image = new BufferedImage(colorModel, wr.createWritableChild(0, 0, width, height, 0, 0, null), false, null);
        }

        return this.image;
    }

    public Shape initOutline(char c){
        if ((this.getWidth()==0) || (this.getHeight()==0)){
            return new GeneralPath();
        }

        GeneralPath gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        Shape shape = null;
        long ptr;

        ptr = LinuxNativeFont.getGlyphOutline(this.pFont, c);
        if (ptr == 0){
            return null;
        }

        Xft.FT_Outline outline = xft.createFT_Outline(ptr);

        int n_contours = outline.get_n_contours(); /* number of contours in the glyph */
        Xft.FT_Vector pPoints = outline.get_points(); /* array of outline points */
        long pPointsPtr = pPoints.lock();
        pPoints.unlock();

        Int16Pointer pContours = outline.get_contours(); /* array of contour end points */
        Int8Pointer pTags = outline.get_tags(); /* an array of point tags */

        int index = 0;  /* current point's index */
        int tag;        /* current tag */
        float x_start;
        float y_start;
        float x_finish;
        float y_finish;
        for (int i=0; i < n_contours; i++){

            short end = pContours.get(i);// index of the last point
            Xft.FT_Vector point;

            // get start position
            point = xft.createFT_Vector(pPointsPtr + index * (Xft.FT_Vector.sizeof));
            x_start = (float)((int)point.get_x() + 32)/64;
            y_start = (float)(-(int)point.get_y() + 32)/64;
//          System.out.println("AddPoint [" + x_start + "," + y_start + "]");

            // get finish position
            point = xft.createFT_Vector(pPointsPtr + (end - 1) * Xft.FT_Vector.sizeof);
            x_finish = (float)((int)point.get_x() + 32)/64;
            y_finish = (float)(-(int)point.get_y() + 32)/64;

            tag = pTags.get(index);// tag of the current point

            if (tag == LinuxNativeFontWrapper.FT_CURVE_TAG_CONIC){
                tag = pTags.get(end - 1);// tag of the last point
                if ((tag & LinuxNativeFontWrapper.FT_CURVE_TAG_ON)==0){
                    x_start = x_finish;
                    y_start = y_finish;
                    end--;
                } else {
                    x_start = (x_start + x_finish)/2;

                    y_start = (y_start + y_finish)/2;
                    x_finish = x_start;
                    y_finish = y_start;
                    index --;
                }
            }

            gp.moveTo(x_start, y_start);

            while(index < end){
                index++;

                tag = pTags.get(index);// tag of the current point
                switch((tag & 3)){
                    case(LinuxNativeFontWrapper.FT_CURVE_TAG_ON):
                        point = xft.createFT_Vector(pPointsPtr + index*Xft.FT_Vector.sizeof);
                        float x = (float)((int)point.get_x() + 32)/64;
                        float y = (float)(-(int)point.get_y() + 32)/64;
                        gp.lineTo(x, y);
//                      System.out.println("AddPoint [" + x + "," + y + "]");
                        break;
                    case(LinuxNativeFontWrapper.FT_CURVE_TAG_CONIC):
                        point = xft.createFT_Vector(pPointsPtr + index*Xft.FT_Vector.sizeof);
                        float x1 = (float)((int)point.get_x() + 32)/64;
                        float y1 = (float)(-(int)point.get_y() + 32)/64;

                        float x2;
                        float y2;
                        while (index < end){
                            index++;
                            tag = pTags.get(index);// tag of the current point
                            point = xft.createFT_Vector(pPointsPtr + index*Xft.FT_Vector.sizeof);
                            x2 = (float)((int)point.get_x() + 32)/64;
                            y2 = (float)(-(int)point.get_y() + 32)/64;
                            if ((tag & LinuxNativeFontWrapper.FT_CURVE_TAG_ON) != 0){
                                gp.quadTo(x1, y1, x2, y2);
//                              System.out.println("AddQSpline 1[" + x1 + "," + y1 + "][" + x2 + "," + y2 + "]");
                                break;
                            } else {
                                gp.quadTo(x1, y1, (x1 + x2) / 2, (y1 + y2) / 2);
//                              System.out.println("AddQSpline 2[" + x1 + "," + y1 + "][" + (x1 + x2)/2 + "," + (y1 + y2)/2 + "]");
                                x1 = x2;
                                y1 = y2;
                            }
                        }
                        if ((index == end) && ((tag & LinuxNativeFontWrapper.FT_CURVE_TAG_ON) == 0)){
                            gp.quadTo(x1, y1, x_start, y_start);
//                          System.out.println("AddQSpline 3[" + x1 + "," + y1 + "][" + x_start + "," + y_start + "]");
                        }
                        break;
                    case(LinuxNativeFontWrapper.FT_CURVE_TAG_CUBIC):
                        point = xft.createFT_Vector(pPointsPtr + index*Xft.FT_Vector.sizeof);
                        x1 = (float)((int)point.get_x()+ 32)/64;
                        y1 = (float)(-(int)point.get_y()+ 32)/64;
                        point = xft.createFT_Vector(pPointsPtr + index*Xft.FT_Vector.sizeof);
                        index++;

                        x2 = (float)((int)point.get_x()+ 32)/64;
                        y2 = (float)(-(int)point.get_y()+ 32)/64;

                        index ++;

                        if (index <= end){
                            point = xft.createFT_Vector(pPointsPtr + index * Xft.FT_Vector.sizeof);
                            float x3 = (float)((int)point.get_x()+ 32)/64;
                            float y3 = (float)(-(int)point.get_y()+ 32)/64;
                            gp.curveTo(x1, y1, x2, y2, x3, y3);
//                          System.out.println("AddCSpline 1[" + x1 + "," + y1 + "][" + x2 + "," + y2 + "][" + x3 + "," + y3 + "]");
                        } else {
                            gp.curveTo(x1, y1, x2, y2, x_start, y_start);
//                          System.out.println("AddCSpline 2[" + x1 + "," + y1 + "][" + x2 + "," + y2 + "][" + x_start + "," + y_start + "]");
                        }
                        break;
                    default:
                        return null;
                }

            }
            gp.lineTo(x_start, y_start);
            index++;
        }

        shape = gp;
        return shape;
    }
}


