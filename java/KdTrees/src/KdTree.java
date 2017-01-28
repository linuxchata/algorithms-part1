/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       20/01/2017
 *  Last updated:  28/01/2017
 *
 *  Compilation:  javac KdTree.java
 *  Execution:    java KdTree
 *  Dependencies: Point2D, RectHV
 *
 *  Kd tree data type
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    // List of the points in the Kd tree (flatten)
    private List<Node> points;

    // Root node of the Kd tree
    private Node root;

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
        return this.points.size();
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
                double ymin = 0.0;
                double ymax = 1.0;
                if (parentNode == null) {
                    rect = new RectHV(point.x(), ymin, point.x(), ymax);
                } else {
                    int c = this.compareCoordinates(parentNode.p.y(), point.y());
                    if (c > 0) {
                        ymin = this.calculateYMin(parentNode, ymin);
                        rect = new RectHV(point.x(), ymin, point.x(), parentNode.p.y());
                    } else {
                        ymax = this.calculateYMax(parentNode, ymax);
                        rect = new RectHV(point.x(), parentNode.p.y(), point.x(), ymax);
                    }
                }
            } else {
                int c = this.compareCoordinates(parentNode.p.x(), point.x());
                if (c > 0) {
                    double xmin = calculateXMin(parentNode);
                    rect = new RectHV(xmin, point.y(), parentNode.p.x(), point.y());
                } else {
                    double xmax = calculateXMax(parentNode);
                    rect = new RectHV(parentNode.p.x(), point.y(), xmax, point.y());
                }
            }
            Node newNode = new Node(parentNode, point, rect, isVertical);
            points.add(newNode);
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

    private boolean hasGrandGrandParent(Node parentNode) {
        return parentNode.parentNode != null && parentNode.parentNode.parentNode != null;
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
                this.leftBottomSearch(node, nearestNode, nearestNodeDistanceToQueryPoint, point, isVertical, true);

            } else {
                // Search on the right side first
                this.rightTopSearch(node, nearestNode, nearestNodeDistanceToQueryPoint, point, isVertical, true);
            }
        } else {
            if (point.y() < node.p.y()) {
                // Search on the bottom side first
                this.leftBottomSearch(node, nearestNode, nearestNodeDistanceToQueryPoint, point, isVertical, false);
            } else {
                // Search on the top side first
                this.rightTopSearch(node, nearestNode, nearestNodeDistanceToQueryPoint, point, isVertical, false);
            }
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

    // Left or bottom search
    private void leftBottomSearch(
            Node node,
            NodeRef nearestNode,
            double nearestNodeDistanceToQueryPoint,
            Point2D point,
            boolean isVertical,
            boolean isLeft) {
        // Search on the left or bottom side first
        searchNearest(node.left, nearestNode, point, !isVertical);

        if (node.right != null) {
            double d = node.rect.distanceTo(point);
            // As well handle corner case when points are on the same line.
            if (nearestNodeDistanceToQueryPoint > d || (isLeft ? Double.compare(node.p.x(), node.right.p.x()) == 0 :
                    Double.compare(node.p.y(), node.right.p.y()) == 0)) {
                searchNearest(node.right, nearestNode, point, !isVertical);
            }
        }
    }

    // Right or top search
    private void rightTopSearch(
            Node node,
            NodeRef nearestNode,
            double nearestNodeDistanceToQueryPoint,
            Point2D point,
            boolean isVertical,
            boolean isRight) {
        // Search on the right or top side first
        searchNearest(node.right, nearestNode, point, !isVertical);

        if (node.left != null) {
            double d = node.rect.distanceTo(point);
            // As well handle corner case when points are on the same line.
            if (nearestNodeDistanceToQueryPoint > d || (isRight ? Double.compare(node.p.x(), node.left.p.x()) == 0 :
                    Double.compare(node.p.y(), node.left.p.y()) == 0)) {
                searchNearest(node.left, nearestNode, point, !isVertical);
            }
        }
    }

    // Calculate distance from node point to query point
    private double distance(Node node, Point2D point) {
        return node.p.distanceTo(point);
    }

    // Compare coordinates of the points based on the orientation
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

    // Calculate x min
    private double calculateXMin(Node parentNode) {
        double x = 0.0;
        if (this.hasGrandGrandParent(parentNode)) {
            double px = parentNode.parentNode.parentNode.p.x();
            if (px > x && px < parentNode.p.x()) {
                x = px;
            }
        }

        return x;
    }

    // Calculate x max
    private double calculateXMax(Node parentNode) {
        double x = 1.0;
        if (this.hasGrandGrandParent(parentNode)) {
            double px = parentNode.parentNode.parentNode.p.x();
            if (px < x && px > parentNode.p.x()) {
                x = px;
            }
        }

        return x;
    }

    // Calculate y min
    private double calculateYMin(Node parentNode, double ymin) {
        if (this.hasGrandGrandParent(parentNode)) {
            double py = parentNode.parentNode.parentNode.p.y();
            if (py > ymin && py < parentNode.p.y()) {
                ymin = py;
            }
        }

        return ymin;
    }

    // Calculate y max
    private double calculateYMax(Node parentNode, double ymax) {
        if (this.hasGrandGrandParent(parentNode)) {
            double py = parentNode.parentNode.parentNode.p.y();
            if (py < ymax && py > parentNode.p.y()) {
                ymax = py;
            }
        }

        return ymax;
    }

    // Represents Kd tree node
    private static class Node {

        // Parent node
        private Node parentNode;

        // The point
        private Point2D p;

        // The axis-aligned rectangle corresponding to this node
        private RectHV rect;

        // The left/bottom subtree
        private Node left;

        // The right/top subtree
        private Node right;

        // Orientation of the node
        private boolean isVertical;

        // Construct node
        private Node(Node parentNode, Point2D p, RectHV rect, boolean isVertical) {
            this.parentNode = parentNode;
            this.p = p;
            this.rect = rect;
            this.isVertical = isVertical;
        }

        public String toString() {
            return Node.this.p.toString();
        }
    }

    // Represents node reference (to pass Node as ref param to methods.)
    private class NodeRef {

        // The node
        private Node internalNode;

        // Construct node reference
        private NodeRef(Node node) {
            this.internalNode = node;
        }

        // Get node
        private Node get() {
            return this.internalNode;
        }

        // Set node
        private void set(Node node) {
            this.internalNode = node;
        }
    }

    // Unit testing of the methods (optional)
    public static void main(String[] args) {
        /*
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
        StdOut.println(set5.nearest(new Point2D(1.0, 1.0))); // 0.0, 1.0
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

        KdTree set9 = new KdTree();
        set9.insert(new Point2D(0.702, 0.799));
        set9.insert(new Point2D(0.537, 0.807));
        set9.insert(new Point2D(0.245, 0.41));
        StdOut.println(set9.nearest(new Point2D(0.838, 0.174)));  // 0.245, 0.41 */
    }
}