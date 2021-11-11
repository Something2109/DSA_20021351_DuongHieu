package Coursera.HelloWord;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = null;
        double i = 1;
        do {
            String input = StdIn.readString();
            if (StdRandom.bernoulli(1 / i)) {
                champion = input;
            }
            i++;
        } while (!StdIn.isEmpty());
        StdOut.println(champion);
    }
}
