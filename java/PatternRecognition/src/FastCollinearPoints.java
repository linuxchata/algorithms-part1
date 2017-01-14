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

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> segments;

    private List<Point> startExtremePoints;
    private List<Point> endExtremePoints;

    /**
     * Finds all line segments containing 4 or more points
     *
     * @param points the other point
     */
    public FastCollinearPoints(Point[] points) {
        this.validateInputArrayOfPoints(points);

        // Copy array of point to another array to make sure that initial array won't be changed.
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        this.validateForDuplicates(pointsCopy);

        this.segments = new ArrayList<LineSegment>();
        this.startExtremePoints = new ArrayList<Point>();

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
                if (Double.compare(currentSlope, nextSlope) == 0) {
                    slopePoints.add(pointsCopy[i]);
                } else if (Double.compare(currentSlope, slope) == 0) {
                    // Ensures that last point with the same slope is added to the collection.
                    slopePoints.add(pointsCopy[i]);
                    this.addLineSegment(slopePoints, currentPoint);

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

    private void validateInputArrayOfPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.NullPointerException("Points must be populated");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("Point cannot be null");
            }
        }
    }

    private void validateForDuplicates(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            int nextElementIndex = i + 1;
            if (nextElementIndex < points.length &&
                    points[i].compareTo(points[nextElementIndex]) == 0) {
                throw new java.lang.IllegalArgumentException("Duplicates are not allowed");
            }
        }

    }

    private void addLineSegment(List<Point> slopePoints, Point currentPoint) {
        if (slopePoints.size() > 3) {
            Collections.sort(slopePoints);

            if (slopePoints.get(0).compareTo(currentPoint) == 0) {
                LineSegment line = new LineSegment(slopePoints.get(0), slopePoints.get(slopePoints.size() - 1));
                this.segments.add(line);
            }
        }
    }
}
