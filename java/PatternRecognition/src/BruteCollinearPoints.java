/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       08/01/2017
 *  Last updated:  08/01/2017
 *
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: Point.java, LineSegment.java
 *
 *  Brute force algorithms
 *
 ******************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private List<LineSegment> segments;

    /**
     * Finds all line segments containing 4 points
     *
     * @param points the other point
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.NullPointerException("Points must be populated");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException("Point cannot be null");
            }
        }

        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pointsCopy[i] = points[i];
        }

        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length; i++) {
            int nextElementIndex = i + 1;
            if (nextElementIndex < pointsCopy.length &&
                    pointsCopy[i].compareTo(pointsCopy[nextElementIndex]) == 0) {
                throw new java.lang.IllegalArgumentException("Duplicates are not allowed");
            }
        }

        this.segments = new ArrayList<LineSegment>();

        for (int p = 0; p < pointsCopy.length; p++) {
            for (int q = p + 1; q < pointsCopy.length; q++) {
                if (q != p) {
                    for (int r = q + 1; r < pointsCopy.length; r++) {
                        if (r != p && r != q) {
                            for (int s = r + 1; s < pointsCopy.length; s++) {
                                if (s != p && s != q && s != r) {
                                    double slopeQ = pointsCopy[p].slopeTo(pointsCopy[q]);
                                    double slopeR = pointsCopy[p].slopeTo(pointsCopy[r]);
                                    double slopeS = pointsCopy[p].slopeTo(pointsCopy[s]);
                                    if (Double.compare(slopeQ, slopeR) == 0 && Double.compare(slopeR, slopeS) == 0) {
                                        LineSegment line = new LineSegment(pointsCopy[p], pointsCopy[s]);
                                        this.segments.add(line);
                                    }
                                }
                            }
                        }
                    }
                }
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
}
