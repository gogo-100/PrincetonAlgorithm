import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    /**
     * finds all line segments containing 4 points
     *
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {
        if(points == null) throw new IllegalArgumentException("argument is null value");
        for (Point point:points) {
            if(point == null) throw new IllegalArgumentException("argument has null value");
        }

        Point[] clonePoints = points.clone(); // immutate
        Arrays.sort(clonePoints, 0, clonePoints.length); //Point class already have compareTo method
        for (int i = 0; i < points.length - 1; i++) {
            if (clonePoints[i].compareTo(clonePoints[i + 1]) == 0) {
                throw new IllegalArgumentException("duplicate points");
            }
        }
        for (int i = 0; i < clonePoints.length; i++) {
            for (int j = i + 1; j < clonePoints.length; j++) {
                double slope = clonePoints[i].slopeTo(clonePoints[j]);
                for (int k = j + 1; k < clonePoints.length; k++) {
                    if (slope != clonePoints[i].slopeTo(clonePoints[k])) continue;
                    for (int l = k + 1; l < clonePoints.length; l++) {
                        if (slope != clonePoints[i].slopeTo(clonePoints[l])) continue;
                        else result.add(new LineSegment(clonePoints[i], clonePoints[l]));
                    }
                }
            }
        }
    }

    private ArrayList<LineSegment> result = new ArrayList<>();

    /**
     * the number of line segments
     *
     * @return
     */
    public int numberOfSegments() {
        return result.size();
    }


    /**
     * The method segments() should include each line segment containing 4 points exactly once.
     * If 4 points appear on a line segment in the order p→q→r→s,
     * then you should include either the line segment p→s or s→p (but not both)
     * and you should not include subsegments such as p→r or q→r.
     * For simplicity, we will not supply any input to BruteCollinearPoints
     * that has 5 or more collinear points.
     *
     * @return all line segment containing 4 points exactly once
     */
    public LineSegment[] segments() {
        return result.toArray(result.toArray(new LineSegment[result.size()]));
    }

    public static void main(String[] args) {
        // read the n points from a file
        //In in = new In(args[0]);
        In in = new In("input8.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
