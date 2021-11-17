package Coursera.CollinearPoints;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private final ArrayDeque<LineSegment> line;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        validate(points);

        line = new ArrayDeque<>();
        Point[] sortedPoint;

        for (int i = 0; i < points.length; i++) {
            sortedPoint = switchToTop(points, i);
            Comparator<Point> iSlope = sortedPoint[0].slopeOrder();
            Arrays.sort(sortedPoint, 1, sortedPoint.length, iSlope);
            int check = 1;
            while (check < sortedPoint.length - 2) {
                if (iSlope.compare(sortedPoint[check], sortedPoint[check + 1]) == 0 &&
                    iSlope.compare(sortedPoint[check], sortedPoint[check + 2]) == 0) {
                    Point minPoint = minPoint(sortedPoint[0], sortedPoint[check]);
                    Point maxPoint = maxPoint(sortedPoint[0], sortedPoint[check]);
                    while (check < sortedPoint.length - 1 && 
                            iSlope.compare(sortedPoint[check], sortedPoint[check + 1]) == 0) {
                        minPoint = minPoint(minPoint, sortedPoint[check + 1]);
                        maxPoint = maxPoint(maxPoint, sortedPoint[check + 1]);
                        check++;
                    }
                    LineSegment newLine = new LineSegment(minPoint, maxPoint);
                    
                    if (checkDuplicate(newLine)) {
                        line.add(newLine);
                    }
                }
                check++;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return line.size();
    }      

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] arr = new LineSegment[line.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = line.remove();
            line.add(arr[i]);
        }
        return arr;
    }
    
    private static void validate(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] arr = Arrays.copyOf(points, points.length);
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == null || arr[i] == null ||
                 (arr[i].compareTo(arr[i - 1]) == 0)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private boolean checkDuplicate(LineSegment newLine) {
        boolean addLine = true;
        for (int j = 0; j < line.size(); j++) {
            LineSegment query = line.remove();
            line.add(query);
            if (query.toString().equals(newLine.toString())) {
                addLine = false;
                break;
            }
        }
        return addLine;
    }

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

    private static Point[] switchToTop(Point[] points, int index) {
        Point[] newPoints = Arrays.copyOf(points, points.length);
        Point temp = newPoints[0];
        newPoints[0] = newPoints[index];
        newPoints[index] = temp;
        return newPoints;
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
