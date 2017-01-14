/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       08/01/2017
 *  Last updated:  14/01/2017
 *
 *  Compilation:  javac FastCollinearPoints.java
 *  Execution:    java FastCollinearPoints
 *  Dependencies: Point.java, LineSegment.java
 *
 *  Fast algorithms
 *
 ******************************************************************************/

import java.util.*;

public class FastCollinearPoints {

    private List<LineSegment> segments;

    /**
     * Finds all line segments containing 4 or more points
     *
     * @param points the other point
     */
    public FastCollinearPoints(Point[] points) {
        this.ValidateInputArrayOfPoints(points);

        // Copy array of point to another array to make sure that initial array won't be changed.
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        this.ValidateForDuplicates(points);

        this.segments = new ArrayList<LineSegment>();

        for (int p = 0; p < pointsCopy.length; p++) {
            Point currentPoint = points[p];
            Comparator<Point> comp = currentPoint.slopeOrder();
            Arrays.sort(pointsCopy, comp);

            double slope = Double.NEGATIVE_INFINITY;
            ArrayList<Point> slopePoints = new ArrayList<Point>();
            slopePoints.add(currentPoint);
            for (int i = 1; i < pointsCopy.length; i++) {
                double currentSlope = currentPoint.slopeTo(pointsCopy[i]);
                double nextSlope = Double.NEGATIVE_INFINITY;
                if (i + 1 < pointsCopy.length) {
                    nextSlope = currentPoint.slopeTo(pointsCopy[i + 1]);
                }
                if (currentSlope == nextSlope) {
                    slopePoints.add(pointsCopy[i]);
                } else if (currentSlope == slope) {
                    // Ensures that last point with the same slope is added to the collection.
                    slopePoints.add(pointsCopy[i]);
                    this.AddLineSegment(slopePoints);

                    // Clear slope points and add current point again. To handle case when
                    // the current point might be collinear with other set of points.
                    slopePoints.clear();
                    slopePoints.add(currentPoint);
                }

                slope = currentSlope;
            }
        }
    }

    /**
     * The number of line segments
     */
    public int numberOfSegments() {
        return this.segments.size();
    }

    /**
     * The line segments
     */
    public LineSegment[] segments() {
        LineSegment[] lines = new LineSegment[this.numberOfSegments()];
        return this.segments.toArray(lines);
    }

    private void ValidateInputArrayOfPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.NullPointerException("Points must be populated");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("Point cannot be null");
            }
        }
    }

    private void ValidateForDuplicates(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            int nextElementIndex = i + 1;
            if (nextElementIndex < points.length &&
                    points[i].compareTo(points[nextElementIndex]) == 0) {
                throw new java.lang.IllegalArgumentException("Duplicates are not allowed");
            }
        }

    }

    private void AddLineSegment(List<Point> slopePoints) {
        if (slopePoints.size() > 3) {
            Collections.sort(slopePoints);

            LineSegment line = new LineSegment(slopePoints.get(0), slopePoints.get(slopePoints.size() - 1));
            boolean found = false;
            for (LineSegment segment : this.segments) {
                if (segment.toString().equals(line.toString())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                this.segments.add(line);
            }
        }
    }
}
