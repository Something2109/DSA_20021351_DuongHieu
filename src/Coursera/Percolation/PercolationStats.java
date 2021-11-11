package Coursera.Percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] trialsSide;
    private final int n;
    private final int trials;
    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;
        trialsSide = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                p.open(row, col);
            }
            trialsSide[i] = p.numberOfOpenSites();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialsSide) / Math.pow(n, 2);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialsSide) / Math.pow(n, 2);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double confidence = CONFIDENCE_95 * this.stddev() / Math.sqrt(trials);
        return this.mean() - confidence;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double confidence = CONFIDENCE_95 * this.stddev() / Math.sqrt(trials);
        return this.mean() + confidence;
    }

   // test client (see below)
   public static void main(String[] args) {
       PercolationStats ps = new PercolationStats(1000, 100);
       System.out.println(ps.mean());
       System.out.println(ps.stddev());
       System.out.println(ps.confidenceLo());
       System.out.println(ps.confidenceHi());
   }

}