/******************************************************************************
 *  Author:        Pylyp Lebediev
 *  Written:       08/01/2017
 *  Last updated:  08/01/2017
 *
 *  Compilation:  javac Client .java
 *  Execution:    java Client
 *  Dependencies: BruteCollinearPoints.java, FastCollinearPoints.java
 *
 *  Fast algorithms
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Client {
    public static void main(String[] args) {

        // Read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // Draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // Print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        // Print and draw the line segments
        FastCollinearPoints collinear2 = new FastCollinearPoints(points);
        for (LineSegment segment : collinear2.segments()) {
            //StdOut.println(segment);
            //segment.draw();
        }
        StdDraw.show();
    }
}
