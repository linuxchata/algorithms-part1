/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       20/01/2017
 *  Last updated:  23/01/2017
 *
 *  Compilation:  javac KdTree.java
 *  Execution:    java KdTree
 *  Dependencies: Point2D
 *
 *  Point data type
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    // Construct an empty set of points
    public KdTree() {
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
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }
    }

    // Does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }

        return false;
    }

    // Draw all points to standard draw
    public void draw() {
    }

    // All points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException("RectHV cannot be null");
        }

        List<Point2D> result = new ArrayList<Point2D>();
        return result;
    }

    // A nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }

        return new Point2D(0, 0);
    }

    // Unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}