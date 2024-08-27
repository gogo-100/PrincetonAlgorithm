/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class BruteCollinearPoints {
    /**
     * finds all line segments containing 4 points
     * @param points
     */
    public BruteCollinearPoints(Point[] points){
        Arrays.sort(points, 0, points.length, new Point.slopeOrder());
    }

    public int numberOfSegments()        // the number of line segments


    /**
     * The method segments() should include each line segment containing 4 points exactly once.
     * If 4 points appear on a line segment in the order p→q→r→s,
     * then you should include either the line segment p→s or s→p (but not both)
     * and you should not include subsegments such as p→r or q→r.
     * For simplicity, we will not supply any input to BruteCollinearPoints
     *  that has 5 or more collinear points.
     * @return all line segment containing 4 points exactly once
     */
    public LineSegment[] segments(){

    }

    public static void main(String[] args) {

    }
}
