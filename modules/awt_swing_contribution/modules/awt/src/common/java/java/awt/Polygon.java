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
 * @author Denis M. Kishenko
 * @version $Revision$
 */
package java.awt;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.NoSuchElementException;

import org.apache.harmony.awt.gl.*;


public class Polygon implements Shape, Serializable {

    private static final long serialVersionUID = -6460061437900069969L;

    /**
     * The points buffer capacity
     */
    private static final int BUFFER_CAPACITY = 4;
    
    public int npoints;
    public int[] xpoints;
    public int[] ypoints;
    protected Rectangle bounds;

    /*
     * Polygon path iterator  
     */
    class Iterator implements PathIterator {

        /**
         * The source Polygon object
         */
        public Polygon p;
        
        /**
         * The path iterator transformation
         */
        public AffineTransform t;
        
        /**
         * The current segmenet index
         */
        public int index;

        /**
         * Constructs a new Polygon.Iterator for given polygon and transformation
         * @param l - the source Line2D object
         * @param at - the AffineTransform object to apply rectangle path
         */
        public Iterator(AffineTransform at, Polygon p) {
            this.p = p;
            this.t = at;
        }

        public int getWindingRule() {
            return WIND_EVEN_ODD;
        }

        public boolean isDone() {
            return index > p.npoints;
        }

        public void next() {
            index++;
        }

        public int currentSegment(double[] coords) {
            if (isDone()) {
                throw new NoSuchElementException("Iterator out of bounds");
            }
            if (index == p.npoints) {
                return SEG_CLOSE;
            }
            coords[0] = p.xpoints[index];
            coords[1] = p.ypoints[index];
            if (t != null) {
                t.transform(coords, 0, coords, 0, 1);
            }
            return index == 0 ? SEG_MOVETO : SEG_LINETO;
        }

        public int currentSegment(float[] coords) {
            if (isDone()) {
                throw new NoSuchElementException("Iterator out of bounds");
            }
            if (index == p.npoints) {
                return SEG_CLOSE;
            }
            coords[0] = p.xpoints[index];
            coords[1] = p.ypoints[index];
            if (t != null) {
                t.transform(coords, 0, coords, 0, 1);
            }
            return index == 0 ? SEG_MOVETO : SEG_LINETO;
        }
    }

    public Polygon() {
        xpoints = new int[BUFFER_CAPACITY];
        ypoints = new int[BUFFER_CAPACITY];
    }

    public Polygon(int[] xpoints, int[] ypoints, int npoints) {
        if (npoints > xpoints.length || npoints > ypoints.length) {
            throw new IndexOutOfBoundsException("Parameter npoints is greater than array length");
        }
        if (npoints < 0) {
            throw new NegativeArraySizeException("Negative number of points");
        }
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.npoints = npoints;
    }

    public void reset() {
        npoints = 0;
        bounds = null;
    }

    public void invalidate() {
        bounds = null;
    }

    public void addPoint(int px, int py) {
        if (npoints == xpoints.length) {
            int[] tmp;

            tmp = new int[xpoints.length + BUFFER_CAPACITY];
            System.arraycopy(xpoints, 0, tmp, 0, xpoints.length);
            xpoints = tmp;

            tmp = new int[ypoints.length + BUFFER_CAPACITY];
            System.arraycopy(ypoints, 0, tmp, 0, ypoints.length);
            ypoints = tmp;
        }

        xpoints[npoints] = px;
        ypoints[npoints] = py;
        npoints++;

        if (bounds != null) {
            bounds.setFrameFromDiagonal(
                    Math.min(bounds.getMinX(), px),
                    Math.min(bounds.getMinY(), py),
                    Math.max(bounds.getMaxX(), px),
                    Math.max(bounds.getMaxY(), py));
        }
    }

    public Rectangle getBounds() {
        if (bounds != null) {
            return bounds;
        }
        if (npoints == 0) {
            return new Rectangle();
        }

        int bx1 = xpoints[0];
        int by1 = ypoints[0];
        int bx2 = bx1;
        int by2 = by1;

        for (int i = 1; i < npoints; i++) {
            int x = xpoints[i];
            int y = ypoints[i];
            if (x < bx1) {
                bx1 = x;
            } else if (x > bx2) {
                bx2 = x;
            }
            if (y < by1) {
                by1 = y;
            } else if (y > by2) {
                by2 = y;
            }
        }

        return bounds = new Rectangle(bx1, by1, bx2 - bx1, by2 - by1);
    }

    public Rectangle getBoundingBox() {
        return getBounds();
    }

    public Rectangle2D getBounds2D() {
        return getBounds().getBounds2D();
    }

    public void translate(int mx, int my) {
        for (int i = 0; i < npoints; i++) {
            xpoints[i] += mx;
            ypoints[i] += my;
        }
        if (bounds != null) {
            bounds.translate(mx, my);
        }
    }

    public boolean inside(int x, int y) {
        return contains((double) x, (double) y);
    }

    public boolean contains(int x, int y) {
        return contains((double) x, (double) y);
    }

    public boolean contains(double x, double y) {
        return Crossing.isInsideEvenOdd(Crossing.crossShape(this, x, y));
    }

    public boolean contains(double x, double y, double width, double height) {
        int cross = Crossing.intersectShape(this, x, y, width, height);
        return cross != Crossing.CROSSING && Crossing.isInsideEvenOdd(cross);
    }

    public boolean intersects(double x, double y, double width, double height) {
        int cross = Crossing.intersectShape(this, x, y, width, height);
        return cross == Crossing.CROSSING || Crossing.isInsideEvenOdd(cross);
    }

    public boolean contains(Rectangle2D rect) {
        return contains(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public boolean contains(Point point) {
        return contains(point.getX(), point.getY());
    }

    public boolean contains(Point2D point) {
        return contains(point.getX(), point.getY());
    }

    public boolean intersects(Rectangle2D rect) {
        return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public PathIterator getPathIterator(AffineTransform t) {
        return new Iterator(t, this);
    }

    public PathIterator getPathIterator(AffineTransform t, double flatness) {
        return new Iterator(t, this);
    }

}

