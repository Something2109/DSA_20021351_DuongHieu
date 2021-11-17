package Coursera.CollinearPoints;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private final ArrayDeque<LineSegment> line;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validate(points);

        line = new ArrayDeque<>();

        for (int i = 0; i < points.length - 3; i++) {
            Comparator<Point> iSlope = points[i].slopeOrder();
            boolean[] pointCheck = new boolean[points.length];
            for (int j = 0; j < points.length; j++) {
                if (pointCheck[j] || i == j) {
                    continue;
                }
                for (int k = j + 1; k < points.length; k++) {
                    if (pointCheck[k] || k == i) {
                        continue;
                    }
                    if (iSlope.compare(points[j], points[k]) == 0) {
                        pointCheck[i] = true;
                        pointCheck[j] = true;
                        pointCheck[k] = true;
                        Point minPoint = minPoint(points[i], minPoint(points[j], points[k]));
                        Point maxPoint = maxPoint(points[i], maxPoint(points[j], points[k]));
                        boolean addLine = false;
                        for (int m = k + 1; m < points.length; m++) {
                            if (pointCheck[m] || m == i) {
                                continue;
                            }
                            if (iSlope.compare(points[j], points[m]) == 0) {
                                pointCheck[m] = true;
                                addLine = true;
                                minPoint = minPoint(minPoint, points[m]);
                                maxPoint = maxPoint(maxPoint, points[m]);
                            }
                        }
                        if (addLine) {
                            LineSegment newLine = new LineSegment(minPoint, maxPoint);
                            if (checkDuplicate(newLine)) {
                                line.add(newLine);
                            }
                        }
                    }
                }
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
