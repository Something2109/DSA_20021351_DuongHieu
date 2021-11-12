import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private final LineSegment[] line;

    private static Point maxPoint(Point p1, Point p2) {
        if (p1.compareTo(p2) > 0) {
            return p1;
        } else {
            return p2;
        }
    }

    private static Point minPoint(Point p1, Point p2) {
        if (p1.compareTo(p2) < 0) {
            return p1;
        } else {
            return p2;
        }
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        LineSegment[] tempLine = new LineSegment[points.length / 2];
        boolean[] inLine = new boolean[points.length];
        int index = 0;

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            if (!inLine[i]) { 
                Comparator<Point> iSlope = points[i].slopeOrder();
                Arrays.sort(points, i + 1, points.length, iSlope);
                int check = i + 1;
                while (check < points.length - 2) {
                    if (points[check] == null) {
                        throw new IllegalArgumentException();
                    }
                    if (iSlope.compare(points[check], points[check + 1]) == 0) {
                        if (iSlope.compare(points[check], points[check + 2]) == 0) {
                            Point minPoint = minPoint(points[i], points[check]);
                            Point maxPoint = maxPoint(points[i], points[check]);
                            inLine[check] = true;
                            while (check < points.length - 1 && 
                                    iSlope.compare(points[check], points[check + 1]) == 0) {
                                inLine[check + 1] = true;
                                minPoint = minPoint(minPoint, points[check + 1]);
                                maxPoint = maxPoint(maxPoint, points[check + 1]);
                                check++;
                            }
                            tempLine[index] = new LineSegment(minPoint, maxPoint);
                            index++;
                        } 
                    }
                    check++;
                }
            }
        }
        line = Arrays.copyOfRange(tempLine, 0, index);
    }

    // the number of line segments
    public int numberOfSegments() {
        return line.length;
    }      

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(line, line.length);
    }            

    public static void main(String[] args) {

        // read the n points from a file
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
