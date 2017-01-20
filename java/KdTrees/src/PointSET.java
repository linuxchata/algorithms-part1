/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       20/01/2017
 *  Last updated:  20/01/2017
 *
 *  Compilation:  javac PointSET.java
 *  Execution:    java PointSET
 *  Dependencies: Point2D
 *
 *  Point data type
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {

    // Construct an empty set of points
    public PointSET() {
    }

    // Is the set empty?
    public boolean isEmpty() {
        return false;
    }

    // Number of points in the set
    public int size() {
        return 0;
    }

    // Add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
    }

    // Does the set contain point p?
    public boolean contains(Point2D p) {
        return false;
    }

    // Draw all points to standard draw
    public void draw() {
    }

    // All points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    // A nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        return new Point2D(0, 0);
    }

    // Unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}