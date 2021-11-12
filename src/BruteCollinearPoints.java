import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

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

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        LineSegment[] tempLine = new LineSegment[points.length / 4];
        boolean[] inLine = new boolean[points.length];
        int index = 0;
        for (int i = 0; i < points.length - 3; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            if (!inLine[i]) {
                Comparator<Point> iSlope = points[i].slopeOrder();
                for (int j = i + 1; j < points.length; j++) {
                    if (points[j] == null) {
                        throw new IllegalArgumentException();
                    }
                    for (int k = j + 1; k < points.length; k++) {
                        if (points[k] == null) {
                            throw new IllegalArgumentException();
                        }
                        if (iSlope.compare(points[j], points[k]) == 0) {
                            boolean addLine = false;
                            Point minPoint = minPoint(points[i], minPoint(points[j], points[k]));
                            Point maxPoint = maxPoint(points[i], maxPoint(points[j], points[k]));
                            for (int m = k + 1; m < points.length; m++) {
                                if (points[m] == null) {
                                    throw new IllegalArgumentException();
                                }
                                if (iSlope.compare(points[j], points[m]) == 0) {
                                    addLine = true;
                                    inLine[m] = true;
                                    minPoint = minPoint(minPoint, points[m]);
                                    maxPoint = maxPoint(maxPoint, points[m]);
                                }
                            }
                            if (addLine) {
                                inLine[i] = true;
                                inLine[j] = true;
                                inLine[k] = true;
                                tempLine[index] = new LineSegment(minPoint, maxPoint);
                                index++;
                            }
                        }
                    }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
 }
