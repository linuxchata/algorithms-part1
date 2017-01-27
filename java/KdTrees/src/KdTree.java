/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       20/01/2017
 *  Last updated:  27/01/2017
 *
 *  Compilation:  javac KdTree.java
 *  Execution:    java KdTree
 *  Dependencies: Point2D, RectHV
 *
 *  KD tree data type
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private List<Node> points;

    private Node root;

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
                int ymin = 0;
                int ymax = 1;
                if (parentNode == null) {
                    rect = new RectHV(point.x(), ymin, point.x(), ymax);
                } else {
                    int c = this.compareCoordinates(parentNode.p.y(), point.y());
                    if (c > 0) {
                        rect = new RectHV(point.x(), ymin, point.x(), parentNode.p.y());
                    } else {
                        rect = new RectHV(point.x(), parentNode.p.y(), point.x(), ymax);
                    }
                }
            } else {
                int c = this.compareCoordinates(parentNode.p.x(), point.x());
                if (c > 0) {
                    int xmin = 0;
                    rect = new RectHV(xmin, point.y(), parentNode.p.x(), point.y());
                } else {
                    int xmax = 1;
                    rect = new RectHV(parentNode.p.x(), point.y(), xmax, point.y());
                }
            }
            Node newNode = new Node(point, rect, isVertical);
            points.add(newNode);
            this.size++;
            return newNode;
        }

        if (node.p.equals(point)) {
            return node;
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

        this.searchRange(this.root, true, rect, result);

        return result;
    }

    // A nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException("Point cannot be null");
        }

        if (this.root == null) {
            return null;
        }

        NodeRef nearestNode = new NodeRef(this.root);
        this.searchNearest(this.root, nearestNode, p, true);

        return nearestNode.get().p;
    }

    // A nearest neighbor in the set to point p; null if the set is empty
    private void searchNearest(Node node, NodeRef nearestNode, Point2D point, boolean isVertical) {
        if (node == null) {
            return;
        }

        double nearestNodeDistanceToQueryPoint = this.distance(nearestNode.get(), point);
        if (this.distance(node, point) < nearestNodeDistanceToQueryPoint) {
            nearestNode.set(node);
        }

        if (isVertical) {
            if (point.x() < node.p.x()) {
                // Search on the left side first
                searchNearest(node.left, nearestNode, point, !isVertical);

                if (node.right != null) {
                    double d = Math.abs(node.right.p.x() - point.x());
                    if (nearestNodeDistanceToQueryPoint > d) {
                        searchNearest(node.right, nearestNode, point, !isVertical);
                    }
                }
            } else {
                // Search on the right side first
                searchNearest(node.right, nearestNode, point, !isVertical);

                if (node.left != null) {
                    double d = Math.abs(node.left.p.x() - point.x());
                    if (nearestNodeDistanceToQueryPoint > d) {
                        searchNearest(node.left, nearestNode, point, !isVertical);
                    }
                }
            }
        } else {
            if (point.y() < node.p.y()) {
                // Search on the bottom side first
                searchNearest(node.left, nearestNode, point, !isVertical);

                if (node.right != null) {
                    double d = Math.abs(node.right.p.y() - point.y());
                    if (nearestNodeDistanceToQueryPoint > d) {
                        searchNearest(node.right, nearestNode, point, !isVertical);
                    }
                }
            } else {
                // Search on the top side first
                searchNearest(node.right, nearestNode, point, !isVertical);

                if (node.left != null) {
                    double d = Math.abs(node.left.p.y() - point.y());
                    if (nearestNodeDistanceToQueryPoint > d) {
                        searchNearest(node.left, nearestNode, point, !isVertical);
                    }
                }
            }
        }
    }

    // Calculate distance from node point to query point
    private double distance(Node node, Point2D point) {
        return node.p.distanceTo(point);
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
    private void searchRange(Node node, boolean isVertical, RectHV rect, List<Point2D> result) {
        if (node == null) {
            return;
        }

        // Check if point in node lies in given rectangle.
        if (rect.contains(node.p)) {
            result.add(node.p);
        }

        if (rect.intersects(node.rect)) {
            // Recursively search left/bottom (if any could fall in rectangle).
            searchRange(node.left, !isVertical, rect, result);

            // Recursively search right/top (if any could fall in rectangle).
            searchRange(node.right, !isVertical, rect, result);
            return;
        }

        if (isVertical) {
            if (rect.xmax() < node.p.x()) {
                // Range on the left side
                searchRange(node.left, !isVertical, rect, result);
            } else if (rect.xmin() > node.p.x()) {
                // Range on the right side
                searchRange(node.right, !isVertical, rect, result);
            }
        } else {
            if (rect.ymax() < node.p.y()) {
                // Range on the bottom side
                searchRange(node.left, !isVertical, rect, result);
            } else if (rect.ymin() > node.p.y()) {
                // Range on the top side
                searchRange(node.right, !isVertical, rect, result);
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

    private class NodeRef {
        private Node internalNode;

        private NodeRef(Node node) {
            this.internalNode = node;
        }

        private Node get() {
            return this.internalNode;
        }

        private void set(Node node) {
            this.internalNode = node;
        }
    }

    // Unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree set = new KdTree();
        set.insert(new Point2D(0.70208, 0.94946));
        set.insert(new Point2D(0.78219, 0.66849));
        StdOut.println(set.nearest(new Point2D(0.33101, 0.05123))); // 0.78219, 0.66849

        KdTree set2 = new KdTree();
        set2.insert(new Point2D(0.8911, 0.2731));
        StdOut.println(set2.nearest(new Point2D(0.5076, 0.5486))); // 0.8911, 0.2731

        KdTree set3 = new KdTree();
        set3.insert(new Point2D(1.0, 0.445));
        set3.insert(new Point2D(0.873, 0.313));
        StdOut.println(set3.nearest(new Point2D(0.901, 0.132))); // 0.873, 0.313

        KdTree set4 = new KdTree();
        set4.insert(new Point2D(0.56, 0.04));
        StdOut.println(set4.nearest(new Point2D(0.6, 0.78))); // 0.56, 0.04

        KdTree set5 = new KdTree();
        set5.insert(new Point2D(0.0, 1.0));
        StdOut.println(set5.nearest(new Point2D(1.0, 1.0))); // (0.0, 1.0
        set5.insert(new Point2D(0.0, 1.0));
        set5.insert(new Point2D(0.0, 0.0));
        set5.insert(new Point2D(1.0, 1.0));
        StdOut.println(set5.nearest(new Point2D(0.0, 1.0))); // 0.0, 1.0

        KdTree set6 = new KdTree();
        set6.insert(new Point2D(0.13138, 0.85134));
        StdOut.println(set6.nearest(new Point2D(0.80075, 0.09889))); // 0.13138, 0.85134
        set6.insert(new Point2D(0.08922, 0.34278));
        StdOut.println(set6.nearest(new Point2D(0.69153, 0.44716))); // 0.08922, 0.34278

        KdTree set7 = new KdTree();
        set7.insert(new Point2D(0.1452, 0.7639));
        set7.insert(new Point2D(0.9156, 0.3374));
        StdOut.println(set7.nearest(new Point2D(0.7824, 0.1124))); // 0.9156, 0.3374
        StdOut.println(set7.nearest(new Point2D(0.6165, 0.9503))); // 0.1452, 0.7639
        set7.insert(new Point2D(0.6134, 0.3832));
        set7.insert(new Point2D(0.9547, 0.4637));
        set7.insert(new Point2D(0.0625, 0.2999));
        StdOut.println(set7.nearest(new Point2D(0.3119, 0.1303)));  // 0.0625, 0.2999

        KdTree set8 = new KdTree();
        set8.insert(new Point2D(0.0, 0.82));
        set8.insert(new Point2D(0.74, 0.89));
        set8.insert(new Point2D(0.97, 0.9));
        set8.insert(new Point2D(0.01, 0.25));
        StdOut.println(set8.nearest(new Point2D(0.31, 0.53)));  // 0.01, 0.25
        set8.insert(new Point2D(0.65, 0.51));
        set8.insert(new Point2D(0.41, 0.74));
        set8.insert(new Point2D(0.4, 0.66));
        StdOut.println(set8.nearest(new Point2D(0.92, 0.66)));  // 0.97, 0.9
    }
}