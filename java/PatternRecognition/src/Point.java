/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       08/01/2017
 *  Last updated:  08/01/2017
 *
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // x-coordinate of this point
    private final int x;

    // y-coordinate of this point
    private final int y;

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that == null) {
            throw new java.lang.NullPointerException("Point cannot be null");
        }

        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }

        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }

        if (this.y == that.y) {
            return +0.0;
        }

        double m = (double) (that.y - this.y) / (double) (that.x - this.x);
        return m;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (that == null) {
            throw new java.lang.NullPointerException("Point cannot be null");
        }

        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        }

        if (this.x < that.x) {
            return -1;
        } else if (this.x > that.x) {
            return 1;
        }

        return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new SlopeComparator();
    }

    private class SlopeComparator implements Comparator<Point> {
        public int compare(Point q1, Point q2) {
            if (q1 == null || q2 == null) {
                throw new java.lang.NullPointerException("Point cannot be null");
            }

            double q1Slope = Point.this.slopeTo(q1);
            double q2Slope = Point.this.slopeTo(q2);

            if (q1Slope > q2Slope) {
                return 1;
            } else if (q1Slope < q2Slope) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
    }
}