package Week2.Percolation;
import edu.princeton.cs.algs4.*;

public class Percolation {
    public static void main(String[] args) {
        StdOut.print("Side length: ");
        int n = StdIn.readInt();
        StdOut.print("Number of test run: ");
        int test = StdIn.readInt();
        double count = 0;
        for (int i = 1; i <= test; i++) {
            StdOut.println("Attemp " + i +"\n");
            Percolation3D per = new Percolation3D(n);
            count += per.run("side");
            StdOut.println("\n");
        }
        StdOut.println(count / (n * n * n * test));
    }
}
