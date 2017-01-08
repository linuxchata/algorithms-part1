/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       08/01/2017
 *  Last updated:  08/01/2017
 *
 *  Compilation:  javac FastCollinearPoints.java
 *  Execution:    java FastCollinearPoints
 *  Dependencies: Point.java, LineSegment.java
 *
 *  Fast algorithms
 *
 ******************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> segments;

    /**
     * Finds all line segments containing 4 or more points
     *
     * @param points the other point
     */
    public FastCollinearPoints(Point[] points) {
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
