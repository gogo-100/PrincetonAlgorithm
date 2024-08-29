/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> result = new ArrayList<>();

    // finds all line segments containing 4 or more points
    // 1. Think of p as the origin.
    // 2. For each other point q, determine the slope it makes with p.
    // 3. Sort the points according to the slopes they makes with p.
    // 4. Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
    // If so, these points, together with p, are collinear.
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("argument is null value");
        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException("argument has null value");
        }
        boolean checkFlag = false;
        Point[] cPoints = points.clone();
        //check duplicate line
        //one used origin will never be found in an unknown line later
        ArrayList<Point> usedPoint = new ArrayList<>();
        for (int i = 0; i < cPoints.length; i++) {
            Point origin = points[i];   //because clonePoints will change
            //check check duplicate points (problem description say can't use hash)
            Arrays.sort(cPoints, 0, cPoints.length, origin.slopeOrder());
            if (!checkFlag) {
                for (int j = 0; j < points.length - 1; j++) {
                    if (cPoints[j].compareTo(cPoints[j + 1]) == 0) {
                        throw new IllegalArgumentException("duplicate points");
                    }
                }
                checkFlag = true;
            }
            int k = 0, j = 0;
            while (j < cPoints.length - 2) {
                if (origin.slopeTo(cPoints[j]) != origin.slopeTo(cPoints[j + 2])) {
                    j++;
                    continue;
                }
                k = j + 3;
                //find more points and check duplicate line segment
                boolean hasDuplicate = false;
                for (; k < cPoints.length && origin.slopeTo(cPoints[j]) == origin
                        .slopeTo(cPoints[k]); k++){
                    if(usedPoint.contains(cPoints[k])) hasDuplicate = true;
                }
                if(usedPoint.contains(cPoints[j+1])||usedPoint.contains(cPoints[j+2])) hasDuplicate = true;
                if(!hasDuplicate){
                    //consider origin and find two ends of line segment
                    Arrays.sort(cPoints, j, k);
                    if (origin.compareTo(cPoints[k - 1]) < 0
                            && origin.compareTo(cPoints[j]) > 0)
                        result.add(new LineSegment(cPoints[j],cPoints[k - 1]));
                    else if (origin.compareTo(cPoints[k - 1]) > 0) result.add(new LineSegment(cPoints[j],origin));
                    else result.add(new LineSegment(origin,cPoints[k - 1]));
                    usedPoint.add(origin);
                }
                j = k;
            }
        }

    }


    public int numberOfSegments() {
        return result.size();
    }

    public LineSegment[] segments() {
        return result.toArray(result.toArray(new LineSegment[result.size()]));
    }

    public static void main(String[] args) {

        // read the n points from a file
        //In in = new In(args[0]);
        In in = new In("input80.txt");
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.println(collinear.numberOfSegments());
    }
}
