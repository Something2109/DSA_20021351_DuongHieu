package Coursera.Percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF site;
    private final int side;
    private boolean[] open;
    private int openSite;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        site = new WeightedQuickUnionUF(n * n + 2);
        side = n;
        open = new boolean[n * n];
        openSite = 0;
    }

    // check if the row and col is valid
    private void validate(int row, int col) {
        if (row <= 0 || row > side) {
            throw new IllegalArgumentException();
        }
        if (col <= 0 || col > side) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int index = (row - 1) * side + col;
        if (!isOpen(row, col)) {
            open[index - 1] = true;
            openSite++;
        }
        if (row == 1) {
            site.union(index, 0);
        } else {
            if (isOpen(row - 1, col)) {
                site.union(index, index - side);
            }
        } 
        if (row == side) {
            site.union(index, side * side + 1);
        } else {
            if (isOpen(row + 1, col)) {
                site.union(index, index + side);
            }
        }
        if (col > 1 && isOpen(row, col - 1)) {
            site.union(index, index - 1);
        }
        if (col < side && isOpen(row, col + 1)) {
            site.union(index, index + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int index = (row - 1) * side + col;
        return open[index - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = (row - 1) * side + col;
        boolean connected = isOpen(row, col);
        connected = connected && site.find(0) == site.find(index); 
        return connected;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSite;
    }

    // does the system percolate?
    public boolean percolates() {
        return (site.find(0) == site.find(side * side + 1));
    }

    public static void main(String[] args) {
        int n = 10000;
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int row = StdRandom.uniform(n) + 1;
            int col = StdRandom.uniform(n) + 1;
            p.open(row, col);
        }
        System.out.print(p.numberOfOpenSites());
    }
}
