import java.lang.reflect.Array;

/******************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: none
 *
 *  Brute force algorithms
 *
 ******************************************************************************/

public class BruteCollinearPoints {

    private Point[] points;

    /**
     * Finds all line segments containing 4 points
     *
     * @param points the other point
     */
    public BruteCollinearPoints(Point[] points) {
        if(points == null) {
            throw new java.lang.NullPointerException("Points must be populated");
        }

        for(int i = 0; i < points.length; i++){
            if (points[i] == null) {
                throw new java.lang.NullPointerException("Point cannot be null");
            }
        }

        // TODO: Looking for duplicates
        // Arrays.sort(points);
        // throw new java.lang.IllegalArgumentException ("Duplicates are not allowed");

        this.points = points;
    }

    /**
     * The number of line segments
     */
    public int numberOfSegments() {
        return 0;
    }

    /**
     * The line segments
     */
    public LineSegment[] segments() {
        return null;
    }
}
