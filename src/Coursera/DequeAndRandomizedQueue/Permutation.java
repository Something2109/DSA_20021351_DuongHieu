package Coursera.DequeAndRandomizedQueue;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int n = Integer.parseInt(args[0]);
        while(!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        for (int i = 0; i < n; i++) {
            System.out.println(rq.dequeue());
        }
    }
}