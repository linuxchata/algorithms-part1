/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       20/01/2017
 *  Last updated:  24/01/2017
 *
 *  Compilation:  javac KdTree.java
 *  Execution:    java KdTree
 *  Dependencies: Point2D, RectHV
 *
 *  KD tree data type
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private List<Node> points;

    private Node root;

    private int xmin = 0;

    private int xmax = 1;

    private int ymin = 0;

    private int ymax = 1;

    private int size;

    // Construct an empty set of points
    public KdTree() {
        this.points = new ArrayList<Node>();
    }

    // Is the set empty?
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Number of points in the set
    public int size() {
        return this.size;
    }

    // Add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }

        this.root = this.insert(this.root, null, p, true);
    }

    // Add the point to the set (if it is not already in the set)
    private Node insert(Node node, Node parentNode, Point2D point, boolean isVertical) {
        if (node == null) {
            RectHV rect;
            if (isVertical) {
                if (parentNode == null) {
                    rect = new RectHV(point.x(), this.ymin, point.x(), this.ymax);
                } else {
                    int c = this.compareCoordinates(parentNode.p.y(), point.y());
                    if (c > 0) {
                        rect = new RectHV(point.x(), this.ymin, point.x(), parentNode.p.y());
                    } else {
                        rect = new RectHV(point.x(), parentNode.p.y(), point.x(), this.ymax);
                    }
                }
            } else {
                int c = this.compareCoordinates(parentNode.p.x(), point.x());
                if (c > 0) {
                    rect = new RectHV(this.xmin, point.y(), parentNode.p.x(), point.y());
                } else {
                    rect = new RectHV(parentNode.p.x(), point.y(), this.xmax, point.y());
                }
            }
            Node newNode = new Node(point, rect, isVertical);
            points.add(newNode);
            this.size++;
            return newNode;
        }

        if (node.p.equals(point)) {
            return null;
        }

        int c = this.compareCoordinates(node.p, point, isVertical);
        if (c > 0) {
            node.left = this.insert(node.left, node, point, !isVertical);
        } else {
            node.right = this.insert(node.right, node, point, !isVertical);
        }

        return node;
    }

    // Does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }

        Node currentNode = this.root;
        boolean isVertical = true;
        while (currentNode != null) {
            if (currentNode.p.equals(p)) {
                return true;
            }
            int c = this.compareCoordinates(currentNode.p, p, isVertical);
            if (c > 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }

            isVertical = !isVertical;
        }

        return false;
    }

    // Draw all points to standard draw
    public void draw() {
        for (Node node : this.points) {
            // Draw points
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(node.p.x(), node.p.y());

            // Draw lines
            if (node.isVertical) {
                StdDraw.setPenColor(StdDraw.RED);
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
            }
            StdDraw.setPenRadius();
            node.rect.draw();
        }
    }

    // All points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException("RectHV cannot be null");
        }

        List<Point2D> result = new ArrayList<Point2D>();

        this.search(this.root, true, rect, result);

        return result;
    }

    // A nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }

        double minDistance = this.root.p.distanceTo(p);

        return new Point2D(0, 0);
    }

    // Get orientation
    private int compareCoordinates(Point2D p1, Point2D p2, boolean isVertical) {
        return isVertical ? this.compareCoordinates(p1.x(), p2.x()) :
                this.compareCoordinates(p1.y(), p2.y());
    }

    // Compare coordinates
    private int compareCoordinates(double c1, double c2) {
        if (c1 > c2) {
            return 1;
        } else if (c1 < c2) {
            return -1;
        } else {
            return 0;
        }
    }

    // All points that are inside the rectangle
    private void search(Node node, boolean isVertical, RectHV rect, List<Point2D> result) {
        if (node == null) {
            return;
        }

        // Check if point in node lies in given rectangle.
        if (rect.contains(node.p)) {
            result.add(node.p);
        }

        if (rect.intersects(node.rect)) {
            // Recursively search left/bottom (if any could fall in rectangle).
            search(node.left, !isVertical, rect, result);

            // Recursively search right/top (if any could fall in rectangle).
            search(node.right, !isVertical, rect, result);
            return;
        }

        if (isVertical) {
            if (rect.xmax() < node.p.x()) {
                // Range of the left side
                search(node.left, !isVertical, rect, result);
            } else if (rect.xmin() > node.p.x()) {
                // Range of the right side
                search(node.right, !isVertical, rect, result);
            }
        } else {
            if (rect.ymax() < node.p.y()) {
                // Range of the bottom side
                search(node.left, !isVertical, rect, result);
            } else if (rect.ymin() > node.p.y()) {
                // Range of the top side
                search(node.right, !isVertical, rect, result);
            }
        }
    }

    private static class Node {

        // The point
        private Point2D p;

        // The axis-aligned rectangle corresponding to this node
        private RectHV rect;

        // The left/bottom subtree
        private Node left;

        // The right/top subtree
        private Node right;

        private boolean isVertical;

        private Node(Point2D p, RectHV rect, boolean isVertical) {
            this.p = p;
            this.rect = rect;
            this.isVertical = isVertical;
        }

        public String toString() {
            return Node.this.p.toString();
        }
    }

    // Unit testing of the methods (optional)
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);

        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }

        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        rect.draw();
        while (true) {
            StdDraw.clear();
            kdtree.draw();
            StdDraw.show();

            StdDraw.pause(100);
        }
    }
}