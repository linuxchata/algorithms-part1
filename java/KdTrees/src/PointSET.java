/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       20/01/2017
 *  Last updated:  24/01/2017
 *
 *  Compilation:  javac PointSET.java
 *  Execution:    java PointSET
 *  Dependencies: Point2D, RectHV
 *
 *  Point set data type
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {

    private TreeSet<Point2D> tree;

    // Construct an empty set of points
    public PointSET() {
        tree = new TreeSet<Point2D>();
    }

    // Is the set empty?
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Number of points in the set
    public int size() {
        return tree.size();
    }

    // Add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }

        this.tree.add(p);
    }

    // Does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }

        return this.tree.contains(p);
    }

    // Draw all points to standard draw
    public void draw() {
        for (Point2D point : this.tree) {
            StdDraw.point(point.x(), point.y());
        }
    }

    // All points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException("Rect cannot be null");
        }

        List<Point2D> result = new ArrayList<Point2D>();
        for (Point2D point : this.tree) {
            if (rect.contains(point)) {
                result.add(point);
            }
        }

        return result;
    }

    // A nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }

        Point2D nearestPoint = null;
        double minDistance = Math.sqrt(2);
        for (Point2D point : this.tree) {
            double distance = this.distance(p, point);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            }
        }

        return nearestPoint;
    }

    private double distance(Point2D p1, Point2D p2) {
        double dx = Math.abs(p1.x() - p2.x());
        double dy = Math.abs(p1.y() - p2.y());
        double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return distance;
    }

    // Unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}